package edu.iu.indra.web.response;

public class JobSubmissionResponse extends SimpleResponse
{
	String jobId;

	public JobSubmissionResponse(boolean success, String message)
	{
		super(success, message);
		this.jobId = null;
	}
	
	public JobSubmissionResponse(boolean success, String jobId, String message)
	{
		super(success, message);
		this.jobId = jobId;
	}

}
