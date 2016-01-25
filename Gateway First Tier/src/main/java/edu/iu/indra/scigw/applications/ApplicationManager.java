package edu.iu.indra.scigw.applications;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import edu.iu.indra.scigw.config.JobConfig;

@Component
public class ApplicationManager
{
	public List<String> getAvailableApplications()
	{
		// TODO: fetch from separate listing
		List<String> applications = new ArrayList<String>();

		applications.add("1. Hello MPI world");
		applications.add("2. Large number Sorter");

		return applications;
	}

	public void runApplication(JobConfig jobConfig, int appId)
	{
		
	}
}
