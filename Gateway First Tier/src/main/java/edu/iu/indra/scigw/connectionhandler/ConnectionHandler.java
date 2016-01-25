package edu.iu.indra.scigw.connectionhandler;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.Session;

import edu.iu.indra.scigw.exceptions.ConnectionFaliedException;
import edu.iu.indra.scigw.exceptions.ExecutionFailedException;

public interface ConnectionHandler
{
	public ChannelSftp getSftpChannel() throws ConnectionFaliedException;

	public ChannelExec getExecChannel() throws ConnectionFaliedException;

	public Session getSession() throws ConnectionFaliedException;

	public void executeCommand(String command) throws ExecutionFailedException;
	
	public void executeCommand(String [] commands) throws ExecutionFailedException;
	
	public String executeCommandGetResult (String command) throws ExecutionFailedException; 
}
