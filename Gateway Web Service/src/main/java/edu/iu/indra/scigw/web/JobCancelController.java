package edu.iu.indra.scigw.web;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.iu.indra.scigw.applications.JobCancelHandler;
import edu.iu.indra.scigw.exceptions.ExecutionFailedException;
import edu.iu.indra.web.response.SimpleResponse;

@Controller
public class JobCancelController {
	final static Logger logger = Logger.getLogger(JobStatusController.class);

	@Autowired
	JobCancelHandler jobCancelHandler;

	@RequestMapping(value = "/canceljob", method = RequestMethod.GET)
	public @ResponseBody SimpleResponse getJobStatus(@RequestParam String jobId)
	{
		logger.info("Job cancel request recieved for id "+ jobId);
		try {
			jobCancelHandler.cancelJob(jobId);
		} catch (ExecutionFailedException e) {
			return new SimpleResponse(false, "Job cancel failed");
		}
		return new SimpleResponse(true, "Successfully job cancelled");
	}
}
