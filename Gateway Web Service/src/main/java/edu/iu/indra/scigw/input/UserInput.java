package edu.iu.indra.scigw.input;

import com.jcraft.jsch.UserInfo;

public class UserInput implements UserInfo
{

	private String passphrase;
	private String pathToSSHKey;
	private String username;
	private String bigredkey;
	private String bigredpass;

	public UserInput(String host, String passphrase, String pathToFile, String username,
			String bigredkey, String bigredpass)
	{
		super();
		this.passphrase = passphrase;
		this.pathToSSHKey = pathToFile;
		this.username = username;
		this.bigredkey = bigredkey;
		this.bigredpass = bigredpass;
	}

	public UserInput()
	{

	}

	public String getBigredpass()
	{
		return this.bigredpass;
	}

	public void setBigredpass(String bigredpass)
	{
		this.bigredpass = bigredpass;
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

	public String getPathToSSHKey()
	{
		return pathToSSHKey;
	}

	public void setPathToSSHKey(String pathToSSHKey)
	{
		this.pathToSSHKey = pathToSSHKey;
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

	public String getBigredkey()
	{
		return this.bigredkey;
	}

	public void setBigredkey(String bigredkey)
	{
		this.bigredkey = bigredkey;
	}

}
