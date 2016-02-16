package edu.iu.indra.scigw.applications;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.iu.indra.scigw.connectionhandler.ConnectionHandlerImpl;
import edu.iu.indra.scigw.exceptions.ExecutionFailedException;
import edu.iu.indra.scigw.util.CommandHelper;


@Service
public class JobCancelHandlerImpl implements JobCancelHandler{

	final static Logger logger = Logger.getLogger(JobCancelHandlerImpl.class);
	
	@Autowired
	ConnectionHandlerImpl connectionHandlerImpl;

	
	@Override
	public void cancelJob(String jobId) throws ExecutionFailedException{
		// TODO Auto-generated method stub
		
		logger.info("Cancelling job based on Job ID");
		
		String command = CommandHelper.getJobCancelByjobIdCommand(jobId);
		
		try {
			connectionHandlerImpl.executeCommand(command);
		} catch (Exception e) {
			throw new ExecutionFailedException("Failed to cancel job with id "+jobId);
			
		}
	}
	
}
