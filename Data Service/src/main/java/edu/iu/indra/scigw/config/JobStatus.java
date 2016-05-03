package edu.iu.indra.scigw.config;

import java.util.UUID;

public class JobStatus
{
	public enum JOB_STATUS
	{
		Q, C, CL, E, H, S, NA;
	}

	private UUID jobUID;
	private String jobId;
	private String jobName;
	private String status;
	private long jobSubmitTime;
	private String localPath;
	private String hostname;

	public String getHostname()
	{
		return this.hostname;
	}

	public void setHostname(String hostname)
	{
		this.hostname = hostname;
	}

	public UUID getJobUID()
	{
		return jobUID;
	}

	public void setJobUID(UUID jobUID)
	{
		this.jobUID = jobUID;
	}

	public String getJobId()
	{
		return jobId;
	}

	public void setJobId(String jobId)
	{
		this.jobId = jobId;
	}

	public String getJobName()
	{
		return jobName;
	}

	public void setJobName(String jobName)
	{
		this.jobName = jobName;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public long getJobSubmitTime()
	{
		return jobSubmitTime;
	}

	public void setJobSubmitTime(long jobSubmitTime)
	{
		this.jobSubmitTime = jobSubmitTime;
	}

	public String getLocalPath()
	{
		return this.localPath;
	}

	public void setLocalPath(String localPath)
	{
		this.localPath = localPath;
	}

	@Override
	public String toString()
	{
		return "JobStatus [jobUID=" + this.jobUID + ", jobId=" + this.jobId + ", jobName="
				+ this.jobName + ", status=" + this.status + ", jobSubmitTime=" + this.jobSubmitTime
				+ ", localPath=" + this.localPath + "]";
	}
}
