package edu.iu.indra.scigw.applications;

import java.util.UUID;

import org.springframework.stereotype.Component;

import edu.iu.indra.scigw.config.JobConfig;

@Component
public class SortingApplicationHandler extends ApplicationHandler
{

	@Override
	protected void getInputForJob(JobConfig jobConfig)
	{
	}

	@Override
	protected String generatePBSCommandList(JobConfig jobConfig)
	{
		StringBuilder script = new StringBuilder();
		UUID jobId = jobConfig.getUid();
		String inputFile = constants.getJobDirPath(jobId.toString()) + "input.txt";
		// append exe file path to script
		String exeCommand = constants.getSortAppExeCommand(inputFile);
		script.append(exeCommand);
		return script.toString();
	}




}
