package edu.iu.indra.scigw;

import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.hibernate.bytecode.buildtime.spi.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.iu.indra.scigw.applications.ApplicationHandlerFactory;
import edu.iu.indra.scigw.applications.ApplicationManager;
import edu.iu.indra.scigw.config.JobConfig;

public class ConsoleMain
{
	final static Logger logger = Logger.getLogger(ConsoleMain.class);

	@Autowired(required = true)
	static ApplicationHandlerFactory applicationHandlerFactory;

	public static void main(String[] args)
	{
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"application-context.xml");

		try
		{
			Scanner scanner = new Scanner(System.in);

			ApplicationManager applicationManager = context.getBean("applicationManager",
					ApplicationManager.class);

			JobConfig jobConfig = context.getBean("jobConfig", JobConfig.class);

			List<String> applications = applicationManager.getAvailableApplications();

			// Ask user to select application to run
			System.out.println("Please select application to run from following(Enter Id)");

			for (String application : applications)
			{
				System.out.println(application);
			}

			int pid = 0;

			pid = scanner.nextInt();

			applicationManager.runApplication(jobConfig, pid);

			context.close();
			
		} catch (Exception e)
		{
			throw new ExecutionException("Failed to run the application", e);
		}
	}

}
