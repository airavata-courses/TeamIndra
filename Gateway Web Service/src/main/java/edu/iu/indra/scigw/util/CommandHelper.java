package edu.iu.indra.scigw.util;

import org.springframework.stereotype.Component;
@Component
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
	public static String getJobWallTime(String wallTime)
	{
		return "#PBS -l walltime="+wallTime;
	}
	public static String getQueueNameCommand(String qname)
	{
		return "#PBS -q " + qname;
	}
	public static String getOutputFilePathCommand(String outpath)
	{
		return "#PBS -o " + outpath ;
	}
	public static String getErrorFilePathCommand(String errorpath)
	{
		return "#PBS -e " + errorpath ;
	}

	public static String getJobNameCommand(String name)
	{
		return "#PBS -N " + name;
	}

	public static String getQsubCommand(String exePath)
	{
		return "qsub " + exePath;
	}

	public static String getJobStatusCommand(String userName )
	{
		return "qstat " + "-u " + userName;
	}
	public static String getJobStatusByjobIdCommand(String jobId )
	{
		return "qstat " + jobId;
	}
}
