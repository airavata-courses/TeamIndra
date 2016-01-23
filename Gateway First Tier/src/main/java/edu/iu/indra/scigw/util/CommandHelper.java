package edu.iu.indra.scigw.util;

public class CommandHelper
{
	public static String change_dir_cmd = "cd ";
	public static String untar_cmd = "tar -xf ";

	public static String getBashScriptStart()
	{
		return "#!/bin/bash";
	}

	public static String getChangeDirectoryCommand(String newDirPath)
	{
		return change_dir_cmd + newDirPath;
	}

	public static String getUntarCommand(String tarFileName)
	{
		return untar_cmd + tarFileName;
	}

	public static String getNodeCountCommand(int nodeCount, int coreCount)
	{
		return "#PBS -l nodes=" + nodeCount + ":ppn=" + coreCount;
	}

	public static String getEmailCommand()
	{
		return "#PBS -m abe";
	}

	public static String getAddEmailCommand(String email)
	{
		return "#PBS -M " + email;
	}

	public static String getQueueNameCommand(String qname)
	{
		return "#PBS -q " + qname;
	}

	public static String getJobNameCommand(String name)
	{
		return "#PBS -N " + name;
	}
}
