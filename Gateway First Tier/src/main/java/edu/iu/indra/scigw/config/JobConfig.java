package edu.iu.indra.scigw.config;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class JobConfig
{
	private final UUID uid = UUID.randomUUID();
	private String name;
	private int nodes = 0;
	private int maxMemory = 0;
	private String walltme;
	private int cput = 0;
	private int cores = 0;
	private boolean sendMail = false;
	private String email;
	private String qname = "normal";
	private Map<String, String> inputFiles;
	private String pbsScriptPath;

	public String getName()
	{
		return this.name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getNodes()
	{
		return this.nodes;
	}

	public void setNodes(int nodes)
	{
		this.nodes = nodes;
	}

	public int getMaxMemory()
	{
		return this.maxMemory;
	}

	public void setMaxMemory(int maxMemory)
	{
		this.maxMemory = maxMemory;
	}

	public String getWalltme()
	{
		return this.walltme;
	}

	public void setWalltme(String walltme)
	{
		this.walltme = walltme;
	}

	public int getCput()
	{
		return this.cput;
	}

	public void setCput(int cput)
	{
		this.cput = cput;
	}

	public boolean isSendMail()
	{
		return this.sendMail;
	}

	public void setSendMail(boolean sendMail)
	{
		this.sendMail = sendMail;
	}

	public String getEmail()
	{
		return this.email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getQname()
	{
		return this.qname;
	}

	public void setQname(String qname)
	{
		this.qname = qname;
	}

	public int getCores()
	{
		return this.cores;
	}

	public void setCores(int cores)
	{
		this.cores = cores;
	}

	public Map<String, String> getInputFiles()
	{
		return this.inputFiles;
	}

	public void setInputFiles(Map<String, String> inputFiles)
	{
		this.inputFiles = inputFiles;
	}

	public void addInputFile(String inputFile, String destPath)
	{
		if (this.inputFiles == null)
		{
			this.inputFiles = new HashMap<String, String>();
		}
		this.inputFiles.put(inputFile, destPath);
	}

	public UUID getUid()
	{
		return this.uid;
	}

	public String getPbsScriptPath()
	{
		return this.pbsScriptPath;
	}

	public void setPbsScriptPath(String pbsScriptPath)
	{
		this.pbsScriptPath = pbsScriptPath;
	}

}
