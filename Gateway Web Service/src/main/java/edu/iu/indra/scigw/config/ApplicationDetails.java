package edu.iu.indra.scigw.config;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ApplicationDetails
{
	Integer appID;
	WebJobConfig jobConfig;

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

	public void setJobConfig(WebJobConfig jobConfig)
	{
		this.jobConfig = jobConfig;
	}

	public ApplicationDetails(Integer appID, WebJobConfig jobConfig)
	{
		super();
		this.appID = appID;
		this.jobConfig = jobConfig;
	}

	@JsonIgnore
	public JobConfig getBasicJobConfig()
	{
		if (jobConfig != null)
		{
			return jobConfig.getJobPopulatedJobConfig();
		}

		return null;
	}

	public ApplicationDetails()
	{
		super();
	}

	public static ApplicationDetails getSampleJobConfig()
	{
		return new ApplicationDetails(0, new WebJobConfig());
	}

}
