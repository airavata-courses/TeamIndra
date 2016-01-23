package edu.iu.indra.scigw.util;

public class Constants
{
	public static String username;
	public static final String scratch_dir_path = "//N//dc2//scratch//";
	public static final String default_filename = "job.tar";
	public static final String sortAppExe = "sortApp";

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

}
