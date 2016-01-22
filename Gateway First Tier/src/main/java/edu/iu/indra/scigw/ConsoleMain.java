package edu.iu.indra.scigw;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.apache.log4j.Logger;

import edu.iu.indra.scigw.input.UserInput;
import edu.iu.indra.scigw.util.CommandHelper;
import edu.iu.indra.scigw.util.Constants;

public class ConsoleMain
{
	final static Logger logger = Logger.getLogger(ConsoleMain.class);

	public static void main(String[] args)
	{
		if (args.length != 4)
		{
			System.err.println("Usage java scigw username passphrase host path-to-private-key");
			System.exit(-1);
		}

		String username = args[0];
		String passphrase = args[1];
		String host = args[2];
		String keyFile = args[3];

		// required parameters from user for authentication with server
		final UserInput userInput = new UserInput(host, passphrase, keyFile, username);

		ConsoleMain main = new ConsoleMain();
		main.placeJob(userInput);
	}

	public void placeJob(UserInput userInput)
	{
		Scanner scanner = null;
		Connector connector = null;

		try
		{
			connector = Connector.createInstance(userInput);

			// get job file tar from user and the command for scheduling
			logger.info("Please provide path of schdule job tar file");

			scanner = new Scanner(System.in);
			String jobFile = scanner.nextLine();

			File job = new File(jobFile);
			if (!job.exists())
			{
				logger.error("File does not exists");
				scanner.close();
				System.exit(-1);
			}

			logger.info("Thank you! copying files to server ... ");

			String destFile = Constants.scratch_dir_path + userInput.getUsername() + "//"
					+ Constants.default_filename;

			ScpHandler scpHandler = new ScpHandler();

			scpHandler.copyJobFilesToHost(destFile, jobFile);

			logger.info("File copied to server, unzipping the file");

			String commands[] = {
					CommandHelper.getChangeDirectoryCommand(
							Constants.scratch_dir_path + userInput.getUsername()),
					CommandHelper.getUntarCommand(Constants.default_filename) };

			logger.info("File extracted, following files exists in directory");

			connector.executeCommands(commands);

			connector.disconnect();

		} catch (Exception e)
		{
			logger.error("Exception in connecting to host", e);
		} finally
		{
			try
			{
				connector.disconnect();
				if (scanner != null)
				{
					scanner.close();
				}
			} catch (IOException e)
			{
			}
		}
	}

}
