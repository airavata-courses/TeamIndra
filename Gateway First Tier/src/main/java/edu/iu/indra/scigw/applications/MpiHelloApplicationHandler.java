package edu.iu.indra.scigw.applications;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

import edu.iu.indra.scigw.util.Constants;
import edu.iu.indra.scigw.util.PbsScriptUtil;

public class MpiHelloApplicationHandler extends ApplicationHandler{
	@Override
	protected void getInputForJob()
	{
		// get path of file having comma separated numbers
//		System.out.println("Please enter path of file c");
		String filePath = scanner.nextLine();
//		String filePath = "C:\\Users\\Pratish\\Documents\\Assignments\\SG\\Input File for Portal\\job-submission\\job-submission\\test.txt";
		UUID jobId = jobConfig.getUid();
		String jobDirPath = Constants.getJobDirPath(jobId.toString());
		jobConfig.addInputFile(filePath, jobDirPath + "input.txt");
	}



	@Override
	protected String generatePbsScriptFile()
	{
		StringBuilder script = PbsScriptUtil.createPbsScript(jobConfig);
	
//		UUID jobId = jobConfig.getUid();
//		String inputFile = Constants.getJobDirPath(jobId.toString()) ;
//		System.out.println("Input File : "+inputFile);
	
		// append exe file path to script
		String exeCommand = Constants.getMpiAppNodes(Constants.getMpiAppExeCommand(), jobConfig)+" "+Constants.getMpiAppExePath();
	
		script.append("\n"+exeCommand);
	
		// write script to file
		File bashScript = new File("mpi.sh");
	
		try
		{
			BufferedWriter writer = new BufferedWriter(new FileWriter(bashScript));
			writer.write(script.toString());
			writer.flush();
			writer.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	
		return bashScript.getAbsolutePath();
	}

}
