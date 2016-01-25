package edu.iu.indra.scigw.connectionhandler;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import edu.iu.indra.scigw.exceptions.ConnectionFaliedException;
import edu.iu.indra.scigw.exceptions.ExecutionFailedException;
import edu.iu.indra.scigw.input.UserInput;

@Service
public class ConnectionHandlerImpl implements ConnectionHandler
{
	final static Logger logger = Logger.getLogger(ConnectionHandlerImpl.class);

	@Autowired
	UserInput userInput;

	JSch jsch;

	Session session;

	private Session mGetSession() throws ConnectionFaliedException
	{
		if (session == null || !session.isConnected())
		{
			try
			{
				jsch = new JSch();
				jsch.addIdentity(userInput.getPathToSSHKey());
				session = jsch.getSession(userInput.getUsername(), userInput.getHost(), 22);
				session.setConfig("StrictHostKeyChecking", "no");
				session.setUserInfo(userInput);
				session.connect();
			} catch (JSchException e)
			{
				logger.error("Error in creating session", e);
				throw new ConnectionFaliedException();
			}
		}

		return session;
	}

	@Override
	public ChannelSftp getSftpChannel() throws ConnectionFaliedException
	{
		Session session = mGetSession();
		try
		{
			return (ChannelSftp) session.openChannel("sftp");
		} catch (JSchException e)
		{
			logger.error("Error in creating channel", e);
			throw new ConnectionFaliedException();
		}
	}

	@Override
	public ChannelExec getExecChannel() throws ConnectionFaliedException
	{
		Session session = mGetSession();
		try
		{
			return (ChannelExec) session.openChannel("exec");
		} catch (JSchException e)
		{
			logger.error("Error in creating channel", e);
			throw new ConnectionFaliedException();
		}
	}

	@Override
	public Session getSession() throws ConnectionFaliedException
	{
		return mGetSession();
	}

	@Override
	public void executeCommand(String command) throws ExecutionFailedException
	{
		try
		{
			ChannelExec exec = getExecChannel();

			OutputStream outputStream = exec.getOutputStream();
			PrintStream printStream = new PrintStream(outputStream);

			printStream.println(command);
			printStream.flush();
			Thread.sleep(1000);

		} catch (ConnectionFaliedException e)
		{
			logger.error("Error in executing command : " + command, e);
			throw new ExecutionFailedException();
		} catch (IOException e)
		{
			logger.error("Error in executing command : " + command, e);
			throw new ExecutionFailedException();
		} catch (InterruptedException e)
		{
			logger.error("Error in executing command : " + command, e);
			throw new ExecutionFailedException();
		}
	}

	@Override
	public void executeCommand(String[] commands) throws ExecutionFailedException
	{
		for (String command : commands)
		{
			executeCommand(command);
		}
	}

}
