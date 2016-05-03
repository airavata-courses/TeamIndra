package edu.iu.indra.scigw.connectionhandler;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.Session;

import edu.iu.indra.scigw.exceptions.ConnectionFailedException;
import edu.iu.indra.scigw.exceptions.ExecutionFailedException;

public interface ConnectionHandler
{
	public ChannelSftp getSftpChannel(String hostname) throws ConnectionFailedException;

	public ChannelExec getExecChannel(String hostname) throws ConnectionFailedException;

	public Session getSession(String hostname) throws ConnectionFailedException;

	public void executeCommand(String command, String hostname) throws ExecutionFailedException;
	
	public void executeCommand(String [] commands, String hostname) throws ExecutionFailedException;
	
	public String executeCommandGetResult (String command, String hostname) throws ExecutionFailedException; 
}
