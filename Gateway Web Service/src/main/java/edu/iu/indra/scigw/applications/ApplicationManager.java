package edu.iu.indra.scigw.applications;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.iu.indra.scigw.config.JobConfig;
import edu.iu.indra.scigw.exceptions.SciGwException;

@Component
public class ApplicationManager
{
	@Autowired
	ApplicationHandlerFactory applicationHandlerFactory;

	public List<String> getAvailableApplications()
	{
		// TODO: fetch from separate listing
		List<String> applications = new ArrayList<String>();

		applications.add("1. Hello MPI world");
		applications.add("2. Large number Sorter");

		return applications;
	}

	public void runApplication(JobConfig jobConfig, int appId) throws SciGwException
	{
		ApplicationHandler applicationHandler = applicationHandlerFactory
				.getApplicationHandler(appId);

		applicationHandler.submitJob(jobConfig);
	}

	public ApplicationHandlerFactory getApplicationHandlerFactory()
	{
		return this.applicationHandlerFactory;
	}

	public void setApplicationHandlerFactory(ApplicationHandlerFactory applicationHandlerFactory)
	{
		this.applicationHandlerFactory = applicationHandlerFactory;
	}

}
