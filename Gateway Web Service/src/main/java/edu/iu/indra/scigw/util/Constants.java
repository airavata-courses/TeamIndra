package edu.iu.indra.scigw.util;

import java.io.File;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import edu.iu.indra.scigw.config.JobConfig;
import edu.iu.indra.scigw.input.UserInput;

@Component
public class Constants
{
	@Autowired
	ServletContext servletContext;

	@Autowired
	public UserInput userConfig;

	public static final String scratch_dir_path = "//N//dc2//scratch//";
	public static final String default_filename = "job.tar";
	public static final String sortAppExe = "sortApp";
	public static final String mpiAppExe = "mpirun";
	public static final String aprunAppExe = "aprun";
	public static final String mdAppExe = "mdrun";
	public static final String hostname = "--hostname";
	public static final String gromacsUserInput = "";
	public static final String karst = "karst.uits.iu.edu";
	public static final String bigred = "bigred2.uits.iu.edu";
	

	public String getGromacsUSerInputFilePath()
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String tmpDirPath = servletContext.getRealPath("/WEB-INF/temp/");
		File rootDir = new File(tmpDirPath);

		if (!rootDir.exists())
		{
			rootDir.mkdir();
		}

		String inputFilePath = rootDir.getAbsolutePath() + "\\" + "user_input_" + auth.getName()
				+ ".tpr";
		return inputFilePath;
	}

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

	public String getMdrunCommand(JobConfig jobconfig)
	{
		return mdAppExe + " -s " + getJobDirPath(jobconfig.getUid().toString())
				+ "input.tpr" + " -o " + getJobDirPath(jobconfig.getUid().toString())
				+ "jobOutput.trr";
	}

	public String getGromacsKarstRunCommand(JobConfig jobconfig)
	{
		return mpiAppExe + " -np " + (jobconfig.getNodes() * jobconfig.getCores()) + " "
				+ getMdrunCommand(jobconfig);
	}
	
	public String getGromacsBR2RunCommand(JobConfig jobconfig)
	{
		return aprunAppExe + " -n "   + (jobconfig.getNodes() * jobconfig.getCores())+ " gmx_mpi" + " "
				+ getMdrunCommand(jobconfig);
	}

	public String getMpiAppExePath()
	{
		return scratch_dir_path + getUsername() + "//helloapp//hello";
	}

	public String sendMailwithAttachment(JobConfig jc)
	{
		StringBuilder out = new StringBuilder();
		out.append("\n");
		out.append("if ls " + getJobDirPath(jc.getUid().toString()) + jc.getJobName() + ".o* "
				+ "1> /dev/null 2>&1; then\n"
				+ "\techo \"The job has been executed . The output files are attached\" | mailx -r \"TeamIndra\" -s \"[Attention] : Job Completion Mail(Attachments)\" -a "
				+ getJobDirPath(jc.getUid().toString()) + jc.getJobName() + ".o* \"" + jc.getEmail()
				+ "\" \nfi");
		return out.toString();
	}

	public UserInput getUserConfig()
	{
		return this.userConfig;
	}

	public void setUserConfig(UserInput userConfig)
	{
		this.userConfig = userConfig;
	}

}