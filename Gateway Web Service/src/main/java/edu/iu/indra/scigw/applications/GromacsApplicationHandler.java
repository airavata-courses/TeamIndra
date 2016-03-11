package edu.iu.indra.scigw.applications;

import java.util.UUID;

import org.springframework.stereotype.Component;
import edu.iu.indra.scigw.config.JobConfig;
import edu.iu.indra.scigw.util.Constants;

@Component
public class GromacsApplicationHandler extends ApplicationHandler{
	
	@Override
	protected void getInputForJob(JobConfig jobConfig)
	{
		UUID jobId = jobConfig.getUid();
		String jobDirPath = constants.getJobDirPath(jobId.toString());
		System.err.println(Constants.inputFilePath+"input.tpr");
		jobConfig.addInputFile(Constants.inputFilePath+"input.tpr", jobDirPath + "input.tpr");

	}

	@Override
	protected String generatePBSCommandList(JobConfig jobConfig)
	{
		// append exe file path to script
		
		String exeCommand;
		exeCommand = "\nmodule unload openmpi/gnu\n";
		exeCommand += "module load openmpi/intel/1.6.3\n";
		exeCommand += "module load gromacs\n";
		exeCommand += "module load fftw\n";
		exeCommand += "cd " + constants.getJobDirPath(jobConfig.getUid().toString()) +"\n";
		
		
		exeCommand += constants.getGromacsRunCommand(jobConfig);
		System.out.println(exeCommand);
		return exeCommand;
	}
}
