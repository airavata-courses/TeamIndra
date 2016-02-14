package edu.iu.indra.scigw.exceptions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.iu.indra.scigw.errorcodes.ErrorCodes;
import edu.iu.indra.scigw.errorcodes.ErrorMessages;

@Component
public class SciGwWebException extends RuntimeException
{
	@Autowired
	ErrorMessages errorMessages;

	private static final long serialVersionUID = 1L;

	String errorCode = ErrorCodes.SystemError;
	
	public SciGwWebException()
	{
		super();
	}
	
	public SciGwWebException(String errorCode)
	{
		this.errorCode = errorCode;
	}

	public SciGwWebException(String errorCode, String message)
	{
		super(message);
		this.errorCode = errorCode;
	}

	@Override
	public String getMessage()
	{
		String errorMessage = "Error: " + errorMessages.getErrorMessage(errorCode);
		errorMessage += " underlying error: " + super.getMessage();
		return errorMessage;
	}
}
