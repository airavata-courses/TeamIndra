package edu.iu.indra.scigw.jobhandler;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import edu.iu.indra.scigw.applications.ApplicationManager;
import edu.iu.indra.scigw.config.JobConfig;
import edu.iu.indra.scigw.config.JobStatus;
import edu.iu.indra.scigw.connectionhandler.ConnectionHandlerImpl;
import edu.iu.indra.scigw.dao.JobConfigDao;
import edu.iu.indra.scigw.exceptions.ExecutionFailedException;
import edu.iu.indra.scigw.exceptions.SciGwException;
import edu.iu.indra.scigw.exceptions.SciGwWebException;
import edu.iu.indra.scigw.filehandler.FileHandler;
import edu.iu.indra.scigw.input.UserInput;
import edu.iu.indra.scigw.parser.JobStatusParser;
import edu.iu.indra.scigw.util.CommandHelper;
import edu.iu.indra.scigw.util.Constants;

@Service("jobHandler")
public class JobHandlerImpl implements JobHandler
{

	final static Logger logger = Logger.getLogger(JobHandlerImpl.class);

	@Autowired
	ConnectionHandlerImpl connectionHandlerImpl;

	@Autowired
	public UserInput userConfig;

	@Autowired
	JobStatusParser jobStatusParser;

	@Autowired
	JobConfigDao jobConfigDao;

	@Autowired
	ApplicationManager applicationManager;

	@Autowired
	Constants constants;

	@Autowired
	FileHandler fileHandler;

	@Override
	public String submitJob(JobConfig jobConfig)
	{
		String jobId = null;

		try
		{
			logger.info("Submitting job with UID : " + jobConfig.getUid().toString());

			// run mpi run by default for now
			jobId = applicationManager.runApplication(jobConfig, 1);
			jobId = jobId.trim();
			System.out.println(jobId);

			jobConfig.setJobID(jobId);

			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			jobConfig.setUsername(auth.getName()); // get logged in username
			jobConfigDao.insertJobDetails(jobConfig);

			logger.info("Job submission successful, server ID: " + jobId);

		} catch (SciGwException e)
		{
			throw new SciGwWebException(e.getErrorCode(), e.getMessage());
		}

		return jobId;
	}

	@Override
	public List<JobStatus> getAllJobsFromServer()
	{
		List<JobStatus> jobs = new ArrayList<JobStatus>();

		String username = this.userConfig.getUsername();

		try
		{
			// get all jobs in string format
			String jobStatus = connectionHandlerImpl
					.executeCommandGetResult(CommandHelper.getJobStatusCommand(username));

			// use parser to get list of job status for individual jobs
			jobs = jobStatusParser.parseJobStatus(jobStatus);

			return jobs;

		} catch (ExecutionFailedException e)
		{
			logger.error("Error in getting jobs for user " + username);
		}

		return jobs;
	}

	@Override
	public void cancelJob(String jobId)
	{
		logger.info("Cancelling job based on Job ID");

		String command = CommandHelper.getJobCancelByjobIdCommand(jobId);

		try
		{
			connectionHandlerImpl.executeCommand(command);
		} catch (Exception e)
		{
			throw new SciGwWebException("Failed to cancel job with id " + jobId);
		}
	}

	@Override
	public String downloadFilesForJob(String jobId)
	{

		logger.info("Downloading output of job : " + jobId);

		try
		{
			// fetch corresponding job details from database
			JobStatus job = jobConfigDao.getJobDetailsByJobID(jobId);

			if (job == null)
			{
				throw new SciGwWebException("JOB_NOT_FOUND");
			}

			String jobDir = constants.getJobDirPath(job.getJobUID().toString());

			return fileHandler.downloadDirectoryAsZip(jobDir);

		} catch (Exception e)
		{
			logger.error("Error in downloading file", e);
			throw new SciGwWebException();
		}
	}

	@Override
	public List<JobStatus> getAllJobsForUserFromDatabase(String username)
	{
		List<JobStatus> jobs = new ArrayList<JobStatus>();

		try
		{
			if (username == null || username.length() == 0)
			{
				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				username = auth.getName(); // get logged in username
			}

			return jobConfigDao.getJobStatusForUser(username);

		} catch (Exception e)
		{
			logger.error("Error in getting jobs for user " + username, e);
		}

		return jobs;
	}
}
