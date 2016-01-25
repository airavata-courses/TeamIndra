package edu.iu.indra.scigw.exceptions;

import edu.iu.indra.scigw.errorcodes.ErrorCodes;

public class AuthenticationException extends SciGwException
{

	private static final long serialVersionUID = 1L;

	public AuthenticationException()
	{
		super(ErrorCodes.ErrorInFileTransfer);
	}

	public AuthenticationException(final String errorCode)
	{
		super(errorCode);
	}

}
