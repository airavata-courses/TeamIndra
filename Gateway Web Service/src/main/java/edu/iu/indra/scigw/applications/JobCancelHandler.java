package edu.iu.indra.scigw.applications;

import edu.iu.indra.scigw.exceptions.ExecutionFailedException;

public interface JobCancelHandler {
	public void cancelJob(String jobId) throws ExecutionFailedException;
}
