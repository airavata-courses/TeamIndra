package edu.iu.indra.scigw.connectionhandler;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.Session;

import edu.iu.indra.scigw.exceptions.ConnectionFailedException;
import edu.iu.indra.scigw.exceptions.ExecutionFailedException;

public interface ConnectionHandler
{
	public ChannelSftp getSftpChannel() throws ConnectionFailedException;

	public ChannelExec getExecChannel() throws ConnectionFailedException;

	public Session getSession() throws ConnectionFailedException;

	public void executeCommand(String command) throws ExecutionFailedException;
	
	public void executeCommand(String [] commands) throws ExecutionFailedException;
	
	public String executeCommandGetResult (String command) throws ExecutionFailedException; 
}
