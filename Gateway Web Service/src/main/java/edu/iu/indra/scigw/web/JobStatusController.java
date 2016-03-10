package edu.iu.indra.scigw.web;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.iu.indra.scigw.config.JobStatus;
import edu.iu.indra.scigw.jobhandler.JobHandler;
import edu.iu.indra.web.response.ListResponse;

@Controller
public class JobStatusController
{
	final static Logger logger = Logger.getLogger(JobStatusController.class);

	@Autowired
	JobHandler jobHandler;

	@RequestMapping(value = "/gejobstatusforuser", method = RequestMethod.GET)
	public @ResponseBody ListResponse<JobStatus> getJobStatusForUser(@RequestParam(required = false) String username)
	{
		List<JobStatus> jobs = jobHandler.getAllJobsForUserFromDatabase(username);
		return new ListResponse<JobStatus>(jobs, true, "");
	}
}
