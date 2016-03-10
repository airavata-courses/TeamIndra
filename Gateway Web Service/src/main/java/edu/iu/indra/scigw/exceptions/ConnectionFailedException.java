package edu.iu.indra.scigw.exceptions;

import edu.iu.indra.scigw.errorcodes.ErrorCodes;

public class ConnectionFailedException extends SciGwException
{

	private static final long serialVersionUID = 1L;

	public ConnectionFailedException()
	{
		super(ErrorCodes.ErrorInAuth);
	}

	public ConnectionFailedException(final String errorCode)
	{
		super(errorCode);
	}

}
