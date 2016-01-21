package edu.iu.indra.scigw.input;

import com.jcraft.jsch.UserInfo;

public class UserInput implements UserInfo
{

	private String host;
	private String passphrase;
	private String pathToFile;
	private String username;

	public UserInput(String host, String passphrase, String pathToFile, String username)
	{
		super();
		this.host = host;
		this.passphrase = passphrase;
		this.pathToFile = pathToFile;
		this.username = username;
	}

	public UserInput()
	{

	}

	// TODO: implement when needed
	@Override
	public String getPassword()
	{
		return null;
	}

	@Override
	public boolean promptPassword(String message)
	{
		return true;
	}

	@Override
	public boolean promptPassphrase(String message)
	{
		return true;
	}

	@Override
	public boolean promptYesNo(String message)
	{
		return true;
	}

	@Override
	public void showMessage(String message)
	{

	}

	@Override
	public String getPassphrase()
	{
		return this.passphrase;
	}

	public String getHost()
	{
		return this.host;
	}

	public void setHost(String host)
	{
		this.host = host;
	}

	public String getPathToFile()
	{
		return this.pathToFile;
	}

	public void setPathToFile(String pathToFile)
	{
		this.pathToFile = pathToFile;
	}

	public String getUsername()
	{
		return this.username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public void setPassphrase(String passphrase)
	{
		this.passphrase = passphrase;
	}

}
