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
		// TODO Auto-generated method stub
		return null;
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
			// TODO Auto-generated catch block
			return e.toString();
		}
	}

}
