package edu.iu.indra.scigw.config;

import java.util.Scanner;

import org.apache.log4j.Logger;

import edu.iu.indra.scigw.Connector;

public abstract class ApplicationHandler
{
	final static Logger logger = Logger.getLogger(ApplicationHandler.class);
	protected JobConfig jobConfig;
	protected Scanner scanner = null;

	protected void readJobConfig()
	{
		scanner = new Scanner(System.in);
		jobConfig = new JobConfig();

		System.out.println("Please enter name for the job: ");
		String name = scanner.nextLine();
		jobConfig.setName(name);

		System.out.println("Please enter node count(Max 72): ");
		int nodes = scanner.nextInt();
		jobConfig.setNodes(nodes);

		System.out.println("Please enter total memory: ");
		int memory = scanner.nextInt();
		jobConfig.setMaxMemory(memory);

		System.out.println("Please enter total core count: ");
		int cores = scanner.nextInt();
		jobConfig.setCores(cores);
		
		// flush additional input
		scanner.nextLine();

		System.out.println("Please enter queue name: ");
		String qname = scanner.nextLine();
		jobConfig.setQname(qname);

		System.out.println("Please enter wall time in HH:MM:SS ");
		String wallTime = scanner.nextLine();
		jobConfig.setWalltme(wallTime);

		// flush additional input
		scanner.nextLine();

		System.out.println("Do you want this job monitored by email? y/N");
		String monitor = scanner.nextLine();

		if (monitor.matches("y") || monitor.matches("Y"))
		{
			jobConfig.setSendMail(true);
			
			System.out.println("Please enter email id: ");
			String email = scanner.nextLine();
			jobConfig.setEmail(email);
		}

		getInputForJob();
	}

	public void submitJob()
	{
		// get required configuration from user
		readJobConfig();

		logger.info("Reading config complete");
		// create PBS script based on input and add job specific execution line
		// to script
		logger.info("Generating PBS script");
		String pbsFilePath = generatePbsScriptFile();

		jobConfig.setPbsScriptPath(pbsFilePath);

		// transfer pbs script and input files to server
		String destPbsScriptPath = ApplicationFileTransferHelper
				.transferApplicationFiles(jobConfig);

		// TODO: fire schedule command
		try
		{
			Connector connector = Connector.getInstance();
			System.out.println("qsub " + destPbsScriptPath);
			connector.executeCommands("qsub " + destPbsScriptPath);
		} catch (Exception e)
		{
			e.printStackTrace();
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
}
