package edu.iu.indra.scigw.exceptions;

import edu.iu.indra.scigw.errorcodes.ErrorCodes;

public class FileTransferException extends SciGwException
{

	private static final long serialVersionUID = 1L;

	public FileTransferException()
	{
		super(ErrorCodes.ErrorInFileTransfer);
	}

	public FileTransferException(final String errorCode)
	{
		super(errorCode);
	}

}
