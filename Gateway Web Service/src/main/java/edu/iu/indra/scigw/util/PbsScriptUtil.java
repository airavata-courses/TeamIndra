package edu.iu.indra.scigw.util;

import edu.iu.indra.scigw.config.JobConfig;

public class PbsScriptUtil
{
	public static StringBuilder createPbsScript(JobConfig config)
	{
		if (config == null)
		{
			throw new RuntimeException("Job config should not be null");
		}

		StringBuilder script = new StringBuilder();

		appendWithNewLine(script, CommandHelper.getBashScriptStart());

		String name = config.getJobName();
		appendWithNewLine(script, CommandHelper.getJobNameCommand(name));

		int nodes = config.getNodes();
		int cores = config.getCores();
		String wallTime = config.getWallTime();
		if (nodes > 0)
		{
			appendWithNewLine(script, CommandHelper.getNodeCountCommand(nodes, cores));
		}
		if (!wallTime.isEmpty())
		{
			appendWithNewLine(script, CommandHelper.getJobWallTime(wallTime));
		}

		if (config.getSendMail())
		{
			appendWithNewLine(script, CommandHelper.getEmailCommand());
			appendWithNewLine(script, CommandHelper.getAddEmailCommand(config.getEmail()));
		}
		

		return script;
	}

	public static void appendWithNewLine(StringBuilder script, String newText)
	{
		script.append(newText);
		script.append("\n");
	}

}
