package edu.iu.indra.web.response;

public class SimpleResponse
{
	boolean success;
	String message;

	public SimpleResponse(boolean success, String message)
	{
		super();
		this.success = success;
		this.message = message;
	}

	public boolean isSuccess()
	{
		return this.success;
	}

	public void setSuccess(boolean success)
	{
		this.success = success;
	}

	public String getMessage()
	{
		return this.message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	@Override
	public String toString()
	{
		return "SimpleResponse [success=" + this.success + ", message=" + this.message + "]";
	}

}
