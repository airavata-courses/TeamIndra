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
}
