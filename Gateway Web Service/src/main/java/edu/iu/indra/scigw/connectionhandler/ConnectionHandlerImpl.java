package edu.iu.indra.scigw.connectionhandler;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import edu.iu.indra.scigw.exceptions.ConnectionFailedException;
import edu.iu.indra.scigw.exceptions.ExecutionFailedException;
import edu.iu.indra.scigw.input.UserInput;

@Service("connectionHandler")
public class ConnectionHandlerImpl implements ConnectionHandler
{
	final static Logger logger = Logger.getLogger(ConnectionHandlerImpl.class);

	@Autowired
	UserInput userInput;

	JSch jsch;

	Map<String, Session> sessions = new HashMap<String, Session>();

	private Session mGetSession(String hostname) throws ConnectionFailedException
	{
		Session session = sessions.get(hostname);

		if (session == null || !session.isConnected())
		{
			try
			{
				jsch = new JSch();
				jsch.addIdentity(userInput.getPathToSSHKey());
				session = jsch.getSession(userInput.getUsername(), hostname, 22);
				session.setConfig("StrictHostKeyChecking", "no");
				session.setUserInfo(userInput);
				session.connect();
				sessions.put(hostname, session);
			} catch (JSchException e)
			{
				logger.error("Error in creating session", e);
				throw new ConnectionFailedException();
			}
		} else
		{

		}

		return session;
	}

	@Override
	public ChannelSftp getSftpChannel(String hostname) throws ConnectionFailedException
	{
		Session session = mGetSession(hostname);
		try
		{
			return (ChannelSftp) session.openChannel("sftp");
		} catch (JSchException e)
		{
			logger.error("Error in creating channel", e);
			throw new ConnectionFailedException();
		}
	}

	@Override
	public ChannelExec getExecChannel(String hostname) throws ConnectionFailedException
	{
		Session session = mGetSession(hostname);
		try
		{
			return (ChannelExec) session.openChannel("exec");
		} catch (JSchException e)
		{
			logger.error("Error in creating channel", e);
			throw new ConnectionFailedException();
		}
	}

	@Override
	public Session getSession(String hostname) throws ConnectionFailedException
	{
		return mGetSession(hostname);
	}

	@Override
	public void executeCommand(String command, String hostname) throws ExecutionFailedException
	{
		try
		{
			ChannelExec exec = getExecChannel(hostname);
			exec.setCommand(command);
			exec.connect();
			Thread.sleep(1000);
		} catch (ConnectionFailedException e)
		{
			logger.error("Error in executing command : " + command, e);
			throw new ExecutionFailedException();
		} catch (InterruptedException e)
		{
			logger.error("Error in executing command : " + command, e);
			throw new ExecutionFailedException();
		} catch (JSchException e)
		{
			logger.error("Error in executing command : " + command, e);
			throw new ExecutionFailedException();
		}
	}

	@Override
	public void executeCommand(String[] commands, String hostname) throws ExecutionFailedException
	{
		for (String command : commands)
		{
			executeCommand(command, hostname);
		}
	}

	@Override
	public String executeCommandGetResult(String command, String hostname)
			throws ExecutionFailedException
	{
		// TODO Auto-generated method stub
		StringBuilder out = new StringBuilder();

		try
		{
			ChannelExec exec = getExecChannel(hostname);
			exec.setCommand(command);
			exec.setInputStream(null);
			InputStream in = exec.getInputStream();
			exec.connect();
			String temp = null;
			byte[] tmp = new byte[1024];
			while (true)
			{
				while (in.available() > 0)
				{
					int i = in.read(tmp, 0, 1024);
					if (i < 0)
						break;
					out.append(new String(tmp, 0, i));

				}
				if (exec.isClosed())
				{
					if (in.available() > 0)
						continue;
					System.out.println("exit-status: " + exec.getExitStatus());
					break;
				}
			}
			try
			{
				Thread.sleep(1000);
			} catch (Exception ee)
			{
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			throw new ExecutionFailedException();
		}
		return out.toString();
	}

	public UserInput getUserInput()
	{
		return this.userInput;
	}

	public void setUserInput(UserInput userInput)
	{
		this.userInput = userInput;
	}

}
