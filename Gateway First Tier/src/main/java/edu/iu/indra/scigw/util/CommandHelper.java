package edu.iu.indra.scigw.util;

public class CommandHelper
{
	public static String change_dir_cmd = "cd ";
	public static String untar_cmd = "tar -xf ";
	public static String qsubString="qsub ";
	public static String email_selection = " -M ";
	public static String email_options = " -m bae ";

	public static String getChangeDirectoryCommand(String newDirPath)
	{
		return change_dir_cmd + newDirPath;
	}

	public static String getUntarCommand(String tarFileName)
	{
		return untar_cmd + tarFileName;
	}
	public static String getQueueCommand(String shellScriptFileName)
	{
		return qsubString+ shellScriptFileName;
	}
	public static String getEmailSelection(String qsubCommand)
	{
		return qsubCommand + email_selection;
	}
	public static String getEmailOptions(String qsubCommand)
	{
		return qsubCommand + email_options;
	}

	
}
