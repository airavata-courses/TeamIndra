package edu.iu.indra.scigw.applications;

import org.springframework.stereotype.Service;

@Service("applicationHandlerFactory")
public class SimpleApplicationHandlerFactory implements ApplicationHandlerFactory
{

	@Override
	public ApplicationHandler getApplicationHandler(int id)
	{
		ApplicationHandler applicationHandler = null;
		if (id == 2)
		{
			applicationHandler = new SortingApplicationHandler();
		} else
		{
			System.out.println("Try something else :P ");

		}

		return applicationHandler;
	}

}
