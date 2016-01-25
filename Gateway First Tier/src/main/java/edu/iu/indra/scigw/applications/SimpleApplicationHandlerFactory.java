package edu.iu.indra.scigw.applications;

import org.springframework.stereotype.Service;

@Service
public class SimpleApplicationHandlerFactory implements ApplicationHandlerFactory
{

	@Override
	public ApplicationHandler getApplicationHandler(int id)
	{
		ApplicationHandler applicationHandler = null;
		if (id == 2)
		{
			applicationHandler = new SortingApplicationHandler();
		}
		else if (id == 3){
			applicationHandler = new MpiHelloApplicationHandler();
		}
		else
		{
			System.out.println("Try something else :P ");

		}

		return applicationHandler;
	}

}
