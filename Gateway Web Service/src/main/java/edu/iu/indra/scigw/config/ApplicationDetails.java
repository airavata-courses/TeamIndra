package edu.iu.indra.scigw.config;

public class ApplicationDetails
{
	Integer appID;
	JobConfig jobConfig;

	public Integer getAppID()
	{
		return this.appID;
	}

	public void setAppID(Integer appID)
	{
		this.appID = appID;
	}

	public JobConfig getJobConfig()
	{
		return this.jobConfig;
	}

	public void setJobConfig(JobConfig jobConfig)
	{
		this.jobConfig = jobConfig;
	}

	@Override
	public String toString()
	{
		return "ApplicationDetails [appID=" + this.appID + ", jobConfig=" + this.jobConfig + "]";
	}

	public ApplicationDetails(Integer appID, JobConfig jobConfig)
	{
		super();
		this.appID = appID;
		this.jobConfig = jobConfig;
	}

}
