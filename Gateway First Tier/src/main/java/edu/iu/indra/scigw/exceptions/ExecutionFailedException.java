package edu.iu.indra.scigw.exceptions;

import edu.iu.indra.scigw.errorcodes.ErrorCodes;

public class ExecutionFailedException extends SciGwException
{
	private static final long serialVersionUID = 1L;

	public ExecutionFailedException()
	{
		super(ErrorCodes.ErrorInFileTransfer);
	}

	public ExecutionFailedException(final String errorCode)
	{
		super(errorCode);
	}

}
