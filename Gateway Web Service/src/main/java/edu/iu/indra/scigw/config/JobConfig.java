package edu.iu.indra.scigw.config;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.codehaus.jackson.annotate.JsonIgnore;

public class JobConfig
{
	@JsonIgnore
	private final UUID uid = UUID.randomUUID();
	private String jobName;
	private Integer nodes = 0;
	private Integer maxMemory = 0;
	private String wallTime;
	private Integer cput = 0;
	private Integer cores = 0;
	private Boolean sendMail = true;
	private String email;

	@JsonIgnore
	private Map<String, String> inputFiles;

	@JsonIgnore
	private String pbsScriptPath;

	public String getJobName()
	{
		return this.jobName;
	}
	
	public void setJobName(String jobName)
	{
		this.jobName = jobName;
	}

	public Integer getNodes()
	{
		return this.nodes;
	}

	public void setNodes(Integer nodes)
	{
		this.nodes = nodes;
	}

	public Integer getMaxMemory()
	{
		return this.maxMemory;
	}

	public void setMaxMemory(Integer maxMemory)
	{
		this.maxMemory = maxMemory;
	}

	public String getWallTime()
	{
		return this.wallTime;
	}

	public void setWallTime(String wallTime)
	{
		this.wallTime = wallTime;
	}

	public Integer getCput()
	{
		return this.cput;
	}

	public void setCput(Integer cput)
	{
		this.cput = cput;
	}

	public Integer getCores()
	{
		return this.cores;
	}

	public void setCores(Integer cores)
	{
		this.cores = cores;
	}

	public Boolean getSendMail()
	{
		return this.sendMail;
	}

	public void setSendMail(Boolean sendMail)
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
