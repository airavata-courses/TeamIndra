package edu.iu.indra.scigw.applications;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

import edu.iu.indra.scigw.util.PbsScriptUtil;

public class SortingApplicationHandler extends ApplicationHandler
{

	@Override
	protected void getInputForJob()
	{
		// get path of file having comma separated numbers
		System.out.println("Please enter path of file containing comma separated numbers to sort");
		String filePath = scanner.nextLine();
		UUID jobId = jobConfig.getUid();
		String jobDirPath = constants.getJobDirPath(jobId.toString());
		jobConfig.addInputFile(filePath, jobDirPath + "input.txt");
	}

	@Override
	protected String generatePBSCommandList()
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
