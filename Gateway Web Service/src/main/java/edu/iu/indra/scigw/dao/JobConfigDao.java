package edu.iu.indra.scigw.dao;

import java.util.List;

import edu.iu.indra.scigw.config.JobConfig;

public interface JobConfigDao
{
	public List<JobConfig> getJobList();

	public void insertJobDetails(JobConfig jobRow);

	public void deleteJob(String jobID);

	public JobConfig getJobDetails(String jobName);

}
