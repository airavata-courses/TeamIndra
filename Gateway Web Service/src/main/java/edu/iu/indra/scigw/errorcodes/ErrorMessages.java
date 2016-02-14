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
	MessageSource messageSource;

	public String getErrorMessage(final String errorCode)
	{
		return messageSource.getMessage(errorCode, null, errorCode, null);
	}

	public String getErrorMessage(final String errorCode, Object[] args)
	{
		return messageSource.getMessage(errorCode, args, errorCode, null);
	}

	public String getErrorMessage(final String errorCode, Object[] args, Locale locale)
	{
		return messageSource.getMessage(errorCode, args, errorCode, locale);
	}

	public MessageSource getMessageSource()
	{
		return this.messageSource;
	}

	public void setMessageSource(MessageSource messageSource)
	{
		this.messageSource = messageSource;
	}

}
