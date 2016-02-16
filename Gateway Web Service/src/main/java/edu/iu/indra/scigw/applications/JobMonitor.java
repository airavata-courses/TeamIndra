package edu.iu.indra.scigw.applications;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.iu.indra.scigw.connectionhandler.ConnectionHandlerImpl;
import edu.iu.indra.scigw.exceptions.ExecutionFailedException;
import edu.iu.indra.scigw.input.UserInput;
import edu.iu.indra.scigw.util.CommandHelper;

@Service
public class JobMonitor implements JobStatusHandler
{
	@Autowired
	ConnectionHandlerImpl connectionHandlerImpl;

	@Autowired
	public UserInput userConfig;

	@Override
	public String getJobStatusByJobId(String jobId)
	{
		try
		{
			return connectionHandlerImpl
					.executeCommandGetResult(CommandHelper.getJobStatusByjobIdCommand(jobId));
		} catch (ExecutionFailedException e)
		{
			return e.toString();
		}
	}

	@Override
	public String getJobStatusByUser()
	{
		try
		{
			return connectionHandlerImpl.executeCommandGetResult(
					CommandHelper.getJobStatusCommand(this.userConfig.getUsername()));
		} catch (ExecutionFailedException e)
		{
			return e.toString();
		}
	}

	public ConnectionHandlerImpl getConnectionHandlerImpl()
	{
		return this.connectionHandlerImpl;
	}

	public void setConnectionHandlerImpl(ConnectionHandlerImpl connectionHandlerImpl)
	{
		this.connectionHandlerImpl = connectionHandlerImpl;
	}

	public UserInput getUserConfig()
	{
		return this.userConfig;
	}

	public void setUserConfig(UserInput userConfig)
	{
		this.userConfig = userConfig;
	}

}
