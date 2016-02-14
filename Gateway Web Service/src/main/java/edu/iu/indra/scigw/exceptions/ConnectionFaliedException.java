package edu.iu.indra.scigw.exceptions;

import edu.iu.indra.scigw.errorcodes.ErrorCodes;

public class ConnectionFaliedException extends SciGwException
{

	private static final long serialVersionUID = 1L;

	public ConnectionFaliedException()
	{
		super(ErrorCodes.ErrorInAuth);
	}

	public ConnectionFaliedException(final String errorCode)
	{
		super(errorCode);
	}

}
