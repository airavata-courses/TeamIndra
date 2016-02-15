package edu.iu.indra.scigw.applications;

import org.springframework.stereotype.Component;

import edu.iu.indra.scigw.config.JobConfig;

@Component
public class MpiHelloApplicationHandler extends ApplicationHandler
{
	@Override
	protected void getInputForJob(JobConfig jobConfig)
	{
		// no input required for mpi hello as of now
	}

	@Override
	protected String generatePBSCommandList(JobConfig jobConfig)
	{
		// append exe file path to script
		String exeCommand = constants.getMpiHelloRunCommand(jobConfig);
		return exeCommand;
	}

}
