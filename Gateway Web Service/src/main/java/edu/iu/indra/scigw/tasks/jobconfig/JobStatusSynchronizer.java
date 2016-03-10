package edu.iu.indra.scigw.tasks.jobconfig;

public interface JobStatusSynchronizer
{
	/**
	 * Query the host server and update the job execution status in database
	 */

	public void syncJobStatusInfo();
}
