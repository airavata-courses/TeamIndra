package edu.iu.indra.scigw.parser;

import java.util.ArrayList;

import edu.iu.indra.scigw.config.JobStatus;

public interface JobStatusParser
{
	public ArrayList<JobStatus> parseJobStatus(String status);
}
