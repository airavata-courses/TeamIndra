package edu.iu.indra.scigw;

import org.apache.log4j.Logger;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import edu.iu.indra.scigw.input.UserInput;

/**
 * Handles connection to the given host
 * 
 * @author sagar
 *
 */
public class Connector
{
	final static Logger logger = Logger.getLogger(Connector.class);

	public Session getSessionToHost(UserInput userInput)
	{
		try
		{
			JSch jsch = new JSch();

			// authenticate
			jsch.addIdentity(userInput.getPathToFile());
			Session session = jsch.getSession(userInput.getUsername(), userInput.getHost(), 22);
			session.setUserInfo(userInput);
			session.connect();
			// successfully authenticated with the host
			return session;

		} catch (Exception e)
		{
			logger.error("Exception in connecting to host");
		}

		return null;
	}
}
