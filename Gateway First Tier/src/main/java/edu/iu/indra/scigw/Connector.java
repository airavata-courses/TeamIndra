package edu.iu.indra.scigw;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import org.apache.log4j.Logger;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import edu.iu.indra.scigw.input.UserInput;

/**
 * Handles connection to the given host
 * 
 * @author sagar
 *
 */
public class Connector {
	final static Logger logger = Logger.getLogger(Connector.class);
	private static Connector connector;

	private Session session;
	private JSch jsch;
	private Channel shell;
	private UserInput userInfo;
	private InputStream inputStream;
	private OutputStream outputStream;
	private PrintStream printStream;

	private Connector(UserInput userInput) throws JSchException {
		// singleton
		this.userInfo = userInput;
		init();
	}

	private void init() throws JSchException {
		jsch = new JSch();
		jsch.addIdentity(userInfo.getPathToFile());
		session = jsch.getSession(userInfo.getUsername(), userInfo.getHost(), 22);
		session.setUserInfo(userInfo);
		session.connect();
		shell = session.openChannel("shell");

		try {
			inputStream = shell.getInputStream();
			outputStream = shell.getOutputStream();
			printStream = new PrintStream(outputStream, true);

		} catch (IOException e) {
			logger.error("Streams not created", e);
		}

		shell.connect();
	}

	public static Connector createInstance(UserInput userInput) throws JSchException {
		connector = new Connector(userInput);
		return connector;
	}

	public static Connector getInstance() throws Exception {
		if (connector == null) {
			throw new Exception("Userinfo not set");
		}

		return connector;
	}

	public void executeCommands(String command) {
		try {
			printStream.println(command);
			printStream.flush();
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void executeCommands(String[] commands) {
		for (String command : commands) {
			try {
				executeCommands(command);
			} catch (Exception e) {
				logger.error("Error in executing command: " + command);
			}
		}
	}

	public void disconnect() throws IOException {
		printStream.close();
		inputStream.close();
		shell.disconnect();
		session.disconnect();
	}

	public Channel getShell() {
		return this.shell;
	}

	public UserInput getUserInfo() {
		return this.userInfo;
	}

	public InputStream getInputStream() {
		return this.inputStream;
	}

	public PrintStream getPrintStream() {
		return this.printStream;
	}

	public Session getSession() {
		return this.session;
	}

}
