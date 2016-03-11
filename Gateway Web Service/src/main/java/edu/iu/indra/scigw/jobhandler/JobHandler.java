package edu.iu.indra.scigw.jobhandler;

import java.util.List;
import java.util.zip.ZipFile;

import edu.iu.indra.scigw.config.JobConfig;
import edu.iu.indra.scigw.config.JobStatus;

public interface JobHandler
{
	/**
	 * Submits job to server, adds it to database and returns unique job id
	 * 
	 * @param jobConfig
	 * @return
	 */
	public String submitJob(JobConfig jobConfig);

	/**
	 * Fetches all jobs for the given user from server
	 * 
	 * @param username
	 * @return
	 */
	public List<JobStatus> getAllJobsFromServer();

	/**
	 * Fetches all jobs for the given user from database
	 * 
	 * @param username
	 * @return
	 */
	public List<JobStatus> getAllJobsForUserFromDatabase(String username);

	/**
	 * Cancels execution of job from server and updates database
	 * 
	 * @param jobId
	 */
	public void cancelJob(String jobId);

	/**
	 * Downloads files for job from server in zip format and returns path of
	 * file
	 * 
	 * @param jobId
	 */
	public String downloadFilesForJob(String jobId);

}
