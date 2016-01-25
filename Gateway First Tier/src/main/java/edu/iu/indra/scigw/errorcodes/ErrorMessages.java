package edu.iu.indra.scigw.errorcodes;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
/**
 * Returns error messages for the given error code
 * 
 * @author sagar
 *
 */
public class ErrorMessages
{
	@Autowired
	static MessageSource messageSource;

	public static String getErrorMessage(final String errorCode)
	{
		return messageSource.getMessage(errorCode, null, errorCode, null);
	}

	public static String getErrorMessage(final String errorCode, Object[] args)
	{
		return messageSource.getMessage(errorCode, args, errorCode, null);
	}

	public static String getErrorMessage(final String errorCode, Object[] args, Locale locale)
	{
		return messageSource.getMessage(errorCode, args, errorCode, locale);
	}

}
