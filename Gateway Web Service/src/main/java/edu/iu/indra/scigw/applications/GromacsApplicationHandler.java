package edu.iu.indra.scigw.applications;

import java.io.File;
import java.util.UUID;

import org.springframework.stereotype.Component;

import edu.iu.indra.scigw.config.JobConfig;
import edu.iu.indra.scigw.exceptions.SciGwWebException;
import edu.iu.indra.scigw.util.Constants;

@Component
public class GromacsApplicationHandler extends ApplicationHandler {

	@Override
	protected void getInputForJob(JobConfig jobConfig) {
		UUID jobId = jobConfig.getUid();
		String jobDirPath = constants.getJobDirPath(jobId.toString());

		String inputFilePath = constants.getGromacsUSerInputFilePath();

		File userInput = new File(inputFilePath);

		if (!userInput.exists()) {
			throw new SciGwWebException("User input not found for GrowMacs application");
		}

		jobConfig.addInputFile(userInput.getAbsolutePath(), jobDirPath + "input.tpr");
	}

	@Override
	protected String generatePBSCommandList(JobConfig jobConfig) {
		// append exe file path to script
		
		String exeCommand;
		if (jobConfig.getHostname().equals("karst.uits.iu.edu")) {
			exeCommand = "\nmodule unload openmpi/gnu\n";
			exeCommand += "module load openmpi/intel/1.6.3\n";
			exeCommand += "module load gromacs\n";
			exeCommand += "module load fftw\n";
			exeCommand += "cd " + constants.getJobDirPath(jobConfig.getUid().toString()) + "\n";
			exeCommand += constants.getGromacsKarstRunCommand(jobConfig);
		}else if (jobConfig.getHostname().equals("bigred2.uits.iu.edu")){
			exeCommand = "\nmodule swap PrgEnv-cray PrgEnv-gnu\n";
			exeCommand += "module load fftw\n";
			exeCommand += "module load gromacs\n";
			exeCommand += "cd " + constants.getJobDirPath(jobConfig.getUid().toString()) + "\n";
			exeCommand += constants.getGromacsBR2RunCommand(jobConfig);
			System.out.println("Coming here : " + exeCommand);
		}
		else exeCommand = "";
		System.out.println(exeCommand);
		return exeCommand;
	}
}
