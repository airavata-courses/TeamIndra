package edu.iu.indra.scigw.applications;

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

@Component
public abstract class ApplicationHandler
{
	final static Logger logger = Logger.getLogger(ApplicationHandler.class);

	@Autowired
	protected JobConfig jobConfig;

	@Autowired
	Constants constants;

	@Autowired
	protected ConnectionHandler connectionHandler;

	@Autowired
	protected FileHandler fileHandler;

	protected Scanner scanner = null;

	protected void readJobConfig()
	{
		scanner = new Scanner(System.in);

		logger.info("Reading application specific configuration from user");

		getInputForJob();
	}

	public void submitJob() throws SciGwException
	{
		try
		{
			// get required configuration from user
			readJobConfig();

			logger.info("Reading config complete");
			// create PBS script based on input and add job specific execution
			// line
			// to script
			logger.info("Generating PBS script");
			String pbsFilePath = generatePbsScriptFile();

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

	protected abstract void getInputForJob();

	/**
	 * generates PBS script file
	 * 
	 * @return PBS script file path
	 */
	protected abstract String generatePbsScriptFile();

	public JobConfig getJobConfig()
	{
		return this.jobConfig;
	}

	public void setJobConfig(JobConfig jobConfig)
	{
		this.jobConfig = jobConfig;
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
