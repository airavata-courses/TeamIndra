package edu.iu.indra.scigw.parser;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import edu.iu.indra.scigw.config.JobStatus;

@Service("jobStatusParser")
public class JobStatusParserImpl implements JobStatusParser
{
	final static Logger logger = Logger.getLogger(JobStatusParserImpl.class);

	public ArrayList<JobStatus> parseJobStatus(String status)
	{
		ArrayList<JobStatus> listofprocess = new ArrayList<JobStatus>();
		try
		{
			String lines[] = status.split("\n");

			for (int i = 5; i < lines.length; i++)
			{
				String fields[] = lines[i].split("\\s+");

				JobStatus js = new JobStatus();
				js.setJobId(fields[0]);
				js.setJobName(fields[3]);
				js.setStatus(fields[9]);

				listofprocess.add(js);
			}

			logger.debug("Parsing job status complete");

		} catch (Exception e)
		{
			logger.error("Error in parsing job status", e);
		}

		return listofprocess;
	}

}
