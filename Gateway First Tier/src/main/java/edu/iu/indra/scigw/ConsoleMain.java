package edu.iu.indra.scigw;

import java.io.File;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import edu.iu.indra.scigw.input.UserInput;

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

	public Channel getExecToHost(Session session) throws JSchException
	{
		return session.openChannel("exec");
	}

	public void placeJob(UserInput userInput)
	{
		Scanner scanner = null;
		Session session = null;
		Channel channel = null;
		try
		{
			Connector connector = new Connector();
			session = connector.getSessionToHost(userInput);

			channel = getExecToHost(session);

			// get job file tar from user and the command for scheduling
			System.out.println("Please provide path of schdule job tar file");

			scanner = new Scanner(System.in);
			String jobFile = scanner.nextLine();

			File job = new File(jobFile);
			if (!job.exists())
			{
				logger.error("File does not exists");
				scanner.close();
				System.exit(-1);
			}

			System.out.println("Thank you! copying files to server ... ");

			String destFile = "//N//dc2//scratch//" + userInput.getUsername() + "//job.tar";
			
			ScpHandler scpHandler = new ScpHandler();
			
			scpHandler.copyJobFilesToHost(channel, destFile, jobFile);
			
		} catch (Exception e)
		{
			logger.error("Exception in connecting to host", e);
		} finally
		{
			channel.disconnect();
			session.disconnect();

			if (scanner != null)
			{
				scanner.close();
			}
		}
	}

}
