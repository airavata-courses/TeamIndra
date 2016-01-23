package edu.iu.indra.scigw;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import edu.iu.indra.scigw.config.ApplicationHandler;
import edu.iu.indra.scigw.config.SortingApplicationHandler;
import edu.iu.indra.scigw.input.UserInput;
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
		Constants.setUserNane(username);

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

			// Ask user to select application to run
			System.out.println("Please select application to run from following(Enter Id)\n");

			// TODO: fetch from separate listing
			List<String> applications = new ArrayList<String>();

			applications.add("1. Hello MPI world");
			applications.add("2. Large number Sorter");

			for (String application : applications)
			{
				System.out.println(application);
			}
			
			scanner = new Scanner(System.in);

			int pid = scanner.nextInt();

			if (pid == 2)
			{
				ApplicationHandler applicationHandler = new SortingApplicationHandler();
				applicationHandler.submitJob();
			} else
			{
				System.out.println("Try something else :P ");
			}

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
