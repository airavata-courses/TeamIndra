package edu.iu.indra.scigw.tasks.jobconfig;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import edu.iu.indra.scigw.config.JobStatus;
import edu.iu.indra.scigw.dao.JobConfigDao;
import edu.iu.indra.scigw.jobhandler.JobHandler;
import edu.iu.indra.scigw.parser.JobStatusParser;

/**
 * fetches job status from the host server and updates in the database
 * 
 * @author sagar
 *
 */
@Component
@EnableScheduling
public class JobConfigUpdaterTask implements JobStatusSynchronizer
{
	final static Logger logger = Logger.getLogger(JobConfigUpdaterTask.class);

	@Autowired
	JobHandler jobHandler;

	@Autowired
	JobConfigDao jobDao;

	@Autowired
	JobStatusParser jobStatusParser;

	@Override
	@Scheduled(fixedDelay = 10000)
	public void syncJobStatusInfo()
	{
		logger.info("Running Job Config Updater Task");

		// get the job status from the host server
		List<JobStatus> jobs = jobHandler.getAllJobsFromServer();

		// update the job status in database
		jobDao.updateJobStatus(jobs);

		logger.info("Job status updated successfully in database");
	}
}
