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

		String name = config.getName();
		appendWithNewLine(script, CommandHelper.getJobNameCommand(name));

		int nodes = config.getNodes();
		int cores = config.getCores();
		cores = cores / nodes < 1 ? 8 : cores / nodes;

		if (nodes > 0)
		{
			appendWithNewLine(script, CommandHelper.getNodeCountCommand(nodes, cores));
		}

		if (config.isSendMail())
		{
			appendWithNewLine(script, CommandHelper.getEmailCommand());
			appendWithNewLine(script, CommandHelper.getAddEmailCommand(config.getEmail()));
		}

		String qname = config.getQname();

		if (qname != null && qname.length() > 0)
		{
			appendWithNewLine(script, CommandHelper.getQueueNameCommand(qname));
		}

		return script;
	}

	public static void appendWithNewLine(StringBuilder script, String newText)
	{
		script.append(newText);
		script.append("\n");
	}

}
