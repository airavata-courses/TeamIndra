package edu.iu.indra.scigw.web;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.iu.indra.scigw.config.JobConfig;
import edu.iu.indra.scigw.jobhandler.JobHandler;
import edu.iu.indra.web.response.JobSubmissionResponse;

@Controller
public class JobSubmitController
{
	final static Logger logger = Logger.getLogger(JobSubmitController.class);

	@Autowired
	JobHandler jobHandler;

	@RequestMapping(value = "submitjob", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody JobSubmissionResponse submitJob(@RequestBody JobConfig jobConfig)
	{
		String jobId = null;
		jobId = jobHandler.submitJob(jobConfig);
		return new JobSubmissionResponse(true, jobId, "Job submited to server. JobId: " + jobId);
	}

	@RequestMapping(value = "/submitjob", method = RequestMethod.GET)
	public @ResponseBody JobSubmissionResponse submitJob()
	{
		ObjectMapper mapper = new ObjectMapper();
		String submitFormat = "";
		try
		{
			submitFormat = mapper.writeValueAsString(new JobConfig());
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return new JobSubmissionResponse(true, "Submit job in following format: " + submitFormat);
	}

}
