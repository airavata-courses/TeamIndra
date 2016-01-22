package edu.iu.indra.scigw.util;

public class CommandHelper
{
	public static String change_dir_cmd = "cd ";
	public static String untar_cmd = "tar -xf ";

	public static String getChangeDirectoryCommand(String newDirPath)
	{
		return change_dir_cmd + newDirPath;
	}

	public static String getUntarCommand(String tarFileName)
	{
		return untar_cmd + tarFileName;
	}
}
