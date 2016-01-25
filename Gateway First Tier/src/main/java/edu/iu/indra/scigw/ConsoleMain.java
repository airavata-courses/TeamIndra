package edu.iu.indra.scigw;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.iu.indra.scigw.applications.ApplicationHandler;
import edu.iu.indra.scigw.applications.ApplicationHandlerFactory;
import edu.iu.indra.scigw.input.UserInput;
import edu.iu.indra.scigw.util.Constants;

public class ConsoleMain
{
	final static Logger logger = Logger.getLogger(ConsoleMain.class);

	@Autowired
	ApplicationHandlerFactory applicationHandlerFactory;

	private Scanner scanner;

	public static void main(String[] args)
	{
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"application-context.xml");

		// required parameters from user for authentication with server from
		// config.properties file
		final UserInput userConfig = context.getBean("userConfig", UserInput.class);

		context.close();

		// set username in constants for dynamic directory path formation
		Constants.setUserNane(userConfig.getUsername());

		ConsoleMain main = new ConsoleMain();

		main.placeJob(userConfig);
	}

	public void placeJob(UserInput userInput)
	{
		try
		{
			scanner = new Scanner(System.in);
			
			// Ask user to select application to run
			System.out.println("Please select application to run from following(Enter Id)");

			// TODO: fetch from separate listing
			List<String> applications = new ArrayList<String>();

			applications.add("1. Hello MPI world");
			applications.add("2. Large number Sorter");
			applications.add("3. MPI Hello World");

			for (String application : applications)
			{
				System.out.println(application);
			}

			int pid = 0;

			pid = scanner.nextInt();

			ApplicationHandler applicationHandler = applicationHandlerFactory
					.getApplicationHandler(pid);

			applicationHandler.submitJob();

		} catch (Exception e)
		{
			logger.error("Exception in connecting to host", e);
		}
	}
}
