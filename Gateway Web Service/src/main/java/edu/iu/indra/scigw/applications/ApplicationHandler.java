package edu.iu.indra.scigw.applications;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.iu.indra.scigw.config.JobConfig;
import edu.iu.indra.scigw.connectionhandler.ConnectionHandler;
import edu.iu.indra.scigw.exceptions.SciGwException;
import edu.iu.indra.scigw.filehandler.FileHandler;
import edu.iu.indra.scigw.util.CommandHelper;
import edu.iu.indra.scigw.util.Constants;
import edu.iu.indra.scigw.util.PbsScriptUtil;

@Component
public abstract class ApplicationHandler
{
	final static Logger logger = Logger.getLogger(ApplicationHandler.class);

	@Autowired
	Constants constants;

	@Autowired
	protected ConnectionHandler connectionHandler;

	@Autowired
	protected FileHandler fileHandler;

	protected Scanner scanner = null;

	protected void readJobConfig(JobConfig jobConfig)
	{
		scanner = new Scanner(System.in);

		logger.info("Reading application specific configuration from user");

		getInputForJob(jobConfig);
	}

	public String getJobStatus()
	{

		return null;

	}

	public void submitJob(JobConfig jobConfig) throws SciGwException
	{
		try
		{
			// get required configuration from user
			readJobConfig(jobConfig);

			logger.info("Reading config complete");
			// create PBS script based on input and add job specific execution
			// line
			// to script
			logger.info("Generating PBS script");
			String pbsFilePath = generatePbsScriptFile(jobConfig);

			jobConfig.setPbsScriptPath(pbsFilePath);

			// transfer pbs script and input files to server
			String destPbsScriptPath = fileHandler.transferApplicationFiles(jobConfig);

			connectionHandler.executeCommand(CommandHelper.getQsubCommand(destPbsScriptPath));

		} catch (SciGwException e)
		{
			logger.error("Error in submitting job", e);
			throw e;
		}

		logger.info("Scheduled execution for job ID: " + jobConfig.getUid().toString());

	}

	protected abstract void getInputForJob(JobConfig jobConfig);

	/**
	 * generates PBS script file
	 * 
	 * @return PBS script file path
	 */
	protected abstract String generatePBSCommandList(JobConfig jobConfig);

	protected String generatePbsScriptFile(JobConfig jobConfig)
	{
		StringBuilder script = PbsScriptUtil.createPbsScript(jobConfig);

		String getOutpath = CommandHelper
				.getOutputFilePathCommand(constants.getJobDirPath(jobConfig.getUid().toString()));

		script.append(getOutpath);
		script.append("\n");

		String getErrorpath = CommandHelper
				.getErrorFilePathCommand(constants.getJobDirPath(jobConfig.getUid().toString()));

		script.append(getErrorpath);
		script.append("\n");

		String commandList = generatePBSCommandList(jobConfig);

		if (commandList != null && commandList.length() > 0)
		{
			script.append(commandList);
		} else
		{
			// TODO Auto-generated catch block
			logger.warn(
					"Application didnt return any executable commands.Continuing with no execution command");

		}

		script.append(constants.sendMailwithAttachment(jobConfig));

		// write script to file
		File bashScript = new File("pbs.sh");

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

	public Constants getConstants()
	{
		return this.constants;
	}

	public void setConstants(Constants constants)
	{
		this.constants = constants;
	}

	public ConnectionHandler getConnectionHandler()
	{
		return this.connectionHandler;
	}

	public void setConnectionHandler(ConnectionHandler connectionHandler)
	{
		this.connectionHandler = connectionHandler;
	}

	public FileHandler getFileHandler()
	{
		return this.fileHandler;
	}

	public void setFileHandler(FileHandler fileHandler)
	{
		this.fileHandler = fileHandler;
	}

}
