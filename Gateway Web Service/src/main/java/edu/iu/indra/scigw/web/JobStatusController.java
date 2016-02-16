package edu.iu.indra.scigw.web;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.iu.indra.scigw.applications.JobMonitor;
import edu.iu.indra.web.response.SimpleResponse;

@Controller
public class JobStatusController
{
	final static Logger logger = Logger.getLogger(JobStatusController.class);

	@Autowired
	JobMonitor jobMonitor;

	@RequestMapping(value = "/getJobStatus", method = RequestMethod.GET)
	public @ResponseBody SimpleResponse getJobStatus(@RequestParam String jobId)
	{
		String status = jobMonitor.getJobStatusByJobId(jobId);
		return new SimpleResponse(true, status);
	}

}
