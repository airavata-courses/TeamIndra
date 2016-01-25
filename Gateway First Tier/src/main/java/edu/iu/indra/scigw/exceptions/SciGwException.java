package edu.iu.indra.scigw.exceptions;

import edu.iu.indra.scigw.errorcodes.ErrorCodes;
import edu.iu.indra.scigw.errorcodes.ErrorMessages;

public class SciGwException extends Exception
{
	private static final long serialVersionUID = 1L;

	String errorCode = ErrorCodes.SystemError;

	public SciGwException(String errorCode)
	{
		this.errorCode = errorCode;
	}

	public SciGwException(String errorCode, String message)
	{
		super(message);
		this.errorCode = errorCode;
	}

	@Override
	public String getMessage()
	{
		String errorMessage = "Error: " + ErrorMessages.getErrorMessage(errorCode);
		errorMessage += " underlying error: " + super.getMessage();
		return errorMessage;
	}
}
