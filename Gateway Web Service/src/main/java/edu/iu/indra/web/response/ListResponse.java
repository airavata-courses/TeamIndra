package edu.iu.indra.web.response;

import java.util.List;

public class ListResponse<T>
{
	List<T> data;
	int size = 0;
	boolean success;
	String message;

	public ListResponse(List<T> data, boolean success, String message)
	{
		super();
		this.data = data;
		if (data != null)
		{
			this.size = data.size();
		}
		this.success = success;
		this.message = message;
	}

	public ListResponse()
	{

	}

	public List<T> getData()
	{
		return data;
	}

	public void setData(List<T> data)
	{
		this.data = data;
	}

	public boolean isSuccess()
	{
		return success;
	}

	public void setSuccess(boolean success)
	{
		this.success = success;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

}
