package edu.iu.indra.scigw.web;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.iu.indra.scigw.applications.JobMonitor;
import edu.iu.indra.scigw.config.JobStatus;
import edu.iu.indra.scigw.dao.JobConfigDao;
import edu.iu.indra.web.response.ListResponse;
import edu.iu.indra.web.response.SimpleResponse;

@Controller
public class JobStatusController
{
	final static Logger logger = Logger.getLogger(JobStatusController.class);

	@Autowired
	JobConfigDao jobConfigDAO;

	@Autowired
	JobMonitor jobMonitor;

	@RequestMapping(value = "/getjobstatus", method = RequestMethod.GET)
	public @ResponseBody SimpleResponse getJobStatus(@RequestParam String jobId)
	{
		String status = jobMonitor.getJobStatusByUser();
		// jobMonitor.getJobStatusByJobId(jobId);
		// status = jobConfigDAO.getJobList();
		return new SimpleResponse(true, status);
	}

	@RequestMapping(value = "/gejobstatusforuser", method = RequestMethod.GET)
	public @ResponseBody ListResponse<JobStatus> getJobStatusForUser(@RequestParam String username)
	{
		String status = jobMonitor.getJobStatusByUser();
		// jobMonitor.getJobStatusByJobId(jobId);
		//status = jobConfigDAO.getJobList();
		return new ListResponse<JobStatus>(null, true, status);
	}

}
