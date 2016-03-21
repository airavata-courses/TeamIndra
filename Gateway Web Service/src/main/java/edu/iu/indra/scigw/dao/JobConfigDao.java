package edu.iu.indra.scigw.dao;

import java.util.List;

import edu.iu.indra.scigw.config.JobConfig;
import edu.iu.indra.scigw.config.JobStatus;

public interface JobConfigDao
{

	public List<JobStatus> getJobStatusForUser(String userName);

	public void insertJobDetails(JobConfig jobRow);

	public void deleteJob(String jobID);

	public JobStatus getJobDetailsByJobID(String jobID);

	public void updateJobStatus(List<JobStatus> jobs);
	
	public List<JobStatus> getJobsForOutputSync();

	public List<JobStatus> getAllQuedJobs();
}
