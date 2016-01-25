package edu.iu.indra.scigw.config;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class JobConfig
{
	private final UUID uid = UUID.randomUUID();
	private String jobName;
	private int nodes = 0;
	private int maxMemory = 0;
	private String wallTime;
	private int cput = 0;
	private int cores = 0;
	private boolean sendMail = true;
	private String email;
	private Map<String, String> inputFiles;
	private String pbsScriptPath;

	public String getJobName()
	{
		return this.jobName;
	}

	public void setJobName(String name)
	{
		this.jobName = name;
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

	public String getWallTime()
	{
		return wallTime;
	}

	public void setWallTime(String wallTime)
	{
		this.wallTime = wallTime;
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

	@Override
	public String toString()
	{
		return "JobConfig [uid=" + this.uid + ", jobName=" + this.jobName + ", nodes=" + this.nodes
				+ ", maxMemory=" + this.maxMemory + ", wallTime=" + this.wallTime + ", cput="
				+ this.cput + ", cores=" + this.cores + ", sendMail=" + this.sendMail + ", email="
				+ this.email + ", inputFiles=" + this.inputFiles + ", pbsScriptPath="
				+ this.pbsScriptPath + "]";
	}

}
