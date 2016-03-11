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
	
	@Autowired
	GromacsApplicationHandler groHandler;

	@Override
	public ApplicationHandler getApplicationHandler(int id)
	{
		ApplicationHandler applicationHandler = null;
		if (id == 1)
		{
			applicationHandler = groHandler;
		} else if (id == 2)
		{
			applicationHandler = sortHandler;
		}else if (id == 3)
		{
			applicationHandler = mpiHandler;
		}
		else
		{
			System.out.println("Try something else :P ");

		}

		return applicationHandler;
	}

	public SortingApplicationHandler getSortHandler()
	{
		return this.sortHandler;
	}

	public void setSortHandler(SortingApplicationHandler sortHandler)
	{
		this.sortHandler = sortHandler;
	}

	public MpiHelloApplicationHandler getMpiHandler()
	{
		return this.mpiHandler;
	}

	public void setMpiHandler(MpiHelloApplicationHandler mpiHandler)
	{
		this.mpiHandler = mpiHandler;
	}
	
	public GromacsApplicationHandler getGromacsHandler()
	{
		return this.groHandler;
	}
	
	public void setGromacsHandler(GromacsApplicationHandler groHandler)
	{
		this.groHandler = groHandler;
	}

}
