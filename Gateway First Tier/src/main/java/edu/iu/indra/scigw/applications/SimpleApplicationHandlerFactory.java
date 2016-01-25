package edu.iu.indra.scigw.applications;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("applicationHandlerFactory")
public class SimpleApplicationHandlerFactory implements ApplicationHandlerFactory
{
	@Autowired
	SortingApplicationHandler sortHandler;

	@Autowired
	MpiHelloApplicationHandler mpiHandler;

	@Override
	public ApplicationHandler getApplicationHandler(int id)
	{
		ApplicationHandler applicationHandler = null;
		if (id == 2)
		{
			applicationHandler = sortHandler;
		} else if (id == 1)
		{
			applicationHandler = mpiHandler;
		} else
		{
			System.out.println("Try something else :P ");

		}

		return applicationHandler;
	}

}
