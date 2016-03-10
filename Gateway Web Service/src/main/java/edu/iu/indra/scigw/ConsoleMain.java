package edu.iu.indra.scigw;

import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jcraft.jsch.Session;

import edu.iu.indra.scigw.applications.ApplicationHandlerFactory;
import edu.iu.indra.scigw.applications.ApplicationManager;
import edu.iu.indra.scigw.config.JobConfig;
import edu.iu.indra.scigw.connectionhandler.ConnectionHandler;
import edu.iu.indra.scigw.exceptions.ConnectionFailedException;
import edu.iu.indra.scigw.exceptions.ExecutionFailedException;
import edu.iu.indra.scigw.exceptions.SciGwException;
import edu.iu.indra.scigw.jobhandler.JobHandler;

public class ConsoleMain
{
	final static Logger logger = Logger.getLogger(ConsoleMain.class);

	@Autowired(required = true)
	static ApplicationHandlerFactory applicationHandlerFactory;

	public static void main(String[] args) throws SciGwException
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

			JobHandler jobObj = context.getBean(JobHandler.class);

			while (true)
			{
				String inp = "";
				System.out.println("Press 1 to get job status and 2 to quit the application:");
				inp = scanner.nextLine();

				if (inp.trim().equals("2"))
				{
					break;
				}
				System.out.println(jobObj.getAllJobsFromServer());
			}

			scanner.close();

		} catch (Exception e)
		{
			throw new ExecutionFailedException("Failed to run the application");
		} finally
		{
			// destroy session on exit
			ConnectionHandler con = context.getBean(ConnectionHandler.class);
			context.close();
			Session session;
			try
			{
				session = con.getSession();
				session.disconnect();
			} catch (ConnectionFailedException e)
			{
				logger.error("Failed to close the session");
			}
		}
	}
}
