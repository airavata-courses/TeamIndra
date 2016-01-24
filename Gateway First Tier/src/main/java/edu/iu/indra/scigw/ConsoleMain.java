package edu.iu.indra.scigw;

import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.iu.indra.scigw.config.ApplicationHandler;
import edu.iu.indra.scigw.config.JobConfig;
import edu.iu.indra.scigw.config.SortingApplicationHandler;
import edu.iu.indra.scigw.input.UserInput;
import edu.iu.indra.scigw.util.Constants;

public class ConsoleMain
{
	final static Logger logger = Logger.getLogger(ConsoleMain.class);

	public static void main(String[] args)
	{
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"application-context.xml");

		// required parameters from user for authentication with server from
		// config.properties file
		final UserInput userConfig = context.getBean("userConfig", UserInput.class);

		// input passphrase for the key from the user
		String password = "";
		Console console = System.console();

		if (console != null)
		{
			password = new String(
					console.readPassword("Please enter the passphrase for your SSH key :"));
		} else
		{
			Scanner scanner = new Scanner(System.in);
			System.out.println("Please enter the passphrase for your SSH key :");
			password = scanner.nextLine();
		}

		userConfig.setPassphrase(password);

		// set username in constants for dynamic directory path formation
		Constants.setUserNane(userConfig.getUsername());

		ConsoleMain main = new ConsoleMain();
		main.placeJob(userConfig);
	}

	public void placeJob(UserInput userInput)
	{
		Connector connector = null;
		Scanner scanner = new Scanner(System.in);
		
		try
		{
			connector = Connector.createInstance(userInput);

			// Ask user to select application to run
			System.out.println("Please select application to run from following(Enter Id)");

			// TODO: fetch from separate listing
			List<String> applications = new ArrayList<String>();

			applications.add("1. Hello MPI world");
			applications.add("2. Large number Sorter");

			for (String application : applications)
			{
				System.out.println(application);
			}

			scanner = new Scanner(System.in);

			int pid = 0;

			pid = scanner.nextInt();

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
