package edu.iu.indra.scigw.util;

import edu.iu.indra.scigw.config.JobConfig;

public class Constants
{
	public static String username;
	public static final String scratch_dir_path = "//N//dc2//scratch//";
	public static final String default_filename = "job.tar";
	public static final String sortAppExe = "sortApp";
	public static final String mpiAppExe = "mpirun";
	public static final String mpiAppExePath = scratch_dir_path+username+"//mpiHelloApp";

	public static void setUserNane(String username)
	{
		Constants.username = username;
	}

	public static String getScratchDirPath()
	{
		return scratch_dir_path + username + "//";
	}

	public static String getSortAppDirPath()
	{
		return scratch_dir_path + username + "//" + "sortapp" + "//";
	}

	public static String getSortAppExeCommand(String inputFile)
	{
		return getSortAppDirPath() + sortAppExe + " " + inputFile;
	}

	public static String getJobDirPath(String uuid)
	{
		return getScratchDirPath() + uuid + "//";
	}
	
	public static String getMpiAppExePath()
	{
		return mpiAppExePath;
	}

	public static String getMpiAppExeCommand()
	{
		return scratch_dir_path + username+ "//" + mpiAppExe ;
	}
	
	public static String getMpiAppNodes(String command,JobConfig jobconfig)
	{
		return command + " -n " + (jobconfig.getNodes()* jobconfig.getCores());
	}
	
	public static String sendMailwithAttachment(String pbsout,JobConfig jc){
		StringBuilder out =  new StringBuilder();
		
		out.append("if [ -f "+scratch_dir_path+username+"/" + jc.getJobName()+ "e" + pbsout+" && -f " + scratch_dir_path+username+"/" + jc.getJobName()+ "o" + pbsout+ "] \n");
		out.append("then \n");
		out.append("\techo \"The job has been executed . The output files are attached\" | mailx -r \"TeamIndra\" -s \"[Attention] : Job Completion Mail(Attachments)\" -a \""+scratch_dir_path+username+"/"+jc.getJobName()+"o"+pbsout +" \"" + jc.getEmail()+"\"\n");     
		out.append("fi");
		
		
		return out.toString();
	}
	

}
