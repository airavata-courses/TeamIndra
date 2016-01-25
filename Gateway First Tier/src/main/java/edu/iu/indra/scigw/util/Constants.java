package edu.iu.indra.scigw.util;

import edu.iu.indra.scigw.config.JobConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.iu.indra.scigw.input.UserInput;

@Component
public class Constants
{
	@Autowired
	public UserInput userConfig;

	public static final String scratch_dir_path = "//N//dc2//scratch//";
	public static final String default_filename = "job.tar";
	public static final String sortAppExe = "sortApp";
	public static final String mpiAppExe = "mpirun";

	public String getUsername()
	{
		return this.userConfig.getUsername();
	}

	public String getScratchDirPath()
	{
		return scratch_dir_path + getUsername() + "//";
	}

	public String getSortAppDirPath()
	{
		return scratch_dir_path + getUsername() + "//" + "sortapp" + "//";
	}

	public String getSortAppExeCommand(String inputFile)
	{
		return getSortAppDirPath() + sortAppExe + " " + inputFile;
	}

	public String getJobDirPath(String uuid)
	{
		return getScratchDirPath() + uuid + "//";
	}

	public String getMpiHelloRunCommand(JobConfig jobconfig)
	{
		return mpiAppExe + " -n " + (jobconfig.getNodes() * jobconfig.getCores()) + " "
				+ getMpiAppExePath();
	}

	public String getMpiAppExePath()
	{
		return scratch_dir_path + getUsername() + "//helloapp//hello";
	}

	public String sendMailwithAttachment(String pbsout, JobConfig jc)
	{
		StringBuilder out = new StringBuilder();

		out.append("if [ -f " + scratch_dir_path + getUsername() + "/" + jc.getJobName() + "e"
				+ pbsout + " && -f " + scratch_dir_path + getUsername() + "/" + jc.getJobName()
				+ "o" + pbsout + "] \n");
		out.append("then \n");
		out.append(
				"\techo \"The job has been executed . The output files are attached\" | mailx -r \"TeamIndra\" -s \"[Attention] : Job Completion Mail(Attachments)\" -a \""
						+ scratch_dir_path + getUsername() + "/" + jc.getJobName() + "o" + pbsout
						+ " \"" + jc.getEmail() + "\"\n");
		out.append("fi");

		return out.toString();
	}

}
