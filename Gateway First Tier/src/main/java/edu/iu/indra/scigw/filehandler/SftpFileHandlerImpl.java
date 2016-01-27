package edu.iu.indra.scigw.filehandler;

import java.io.File;
import java.io.FileInputStream;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;

import edu.iu.indra.scigw.config.JobConfig;
import edu.iu.indra.scigw.connectionhandler.ConnectionHandler;
import edu.iu.indra.scigw.exceptions.ConnectionFaliedException;
import edu.iu.indra.scigw.exceptions.FileTransferException;
import edu.iu.indra.scigw.util.Constants;

@Service("fileHandler")
public class SftpFileHandlerImpl implements FileHandler
{
	final static Logger logger = Logger.getLogger(SftpFileHandlerImpl.class);

	@Autowired
	ConnectionHandler connectionHandler;

	@Autowired
	Constants constants;

	@Override
	public void copyFile(String source, String destination) throws FileTransferException
	{
		try
		{
			ChannelSftp sftp = connectionHandler.getSftpChannel();
			sftp.connect();
			sftp.put(source, destination);

		} catch (ConnectionFaliedException e)
		{
			throw new FileTransferException(e.getMessage());
		} catch (SftpException e)
		{
			throw new FileTransferException();
		} catch (JSchException e)
		{
			throw new FileTransferException(e.getMessage());
		}
	}

	@Override
	public String transferApplicationFiles(JobConfig config) throws FileTransferException
	{
		logger.info("Transferring files to server");

		String destPbsScriptPath = "";

		String pbsScriptPath = config.getPbsScriptPath();

		File pbsScript = new File(pbsScriptPath);

		if (pbsScriptPath == null || !pbsScript.exists())
		{
			throw new FileTransferException("PBS script path must be set in job config");
		}

		// get unique ID of job
		String uid = config.getUid().toString();

		try
		{
			// create a new directory for job
			String dirPath = constants.getJobDirPath(uid);
			ChannelSftp sftp = connectionHandler.getSftpChannel();
			sftp.connect();
			sftp.mkdir(dirPath);
			sftp.cd(dirPath);
			destPbsScriptPath = dirPath + "pbs.sh";

			// transfer pbs script to the directory
			sftp.put(new FileInputStream(pbsScript), "pbs.sh");

			logger.info("PBS file transfer complete");

			// transfer input files one by one
			Map<String, String> inputFiles = config.getInputFiles();

			if (inputFiles != null)
			{
				for (String sourceFile : inputFiles.keySet())
				{
					logger.info("Transferring file " + sourceFile + " to server");

					File f = new File(sourceFile);
					if (f.exists())
					{
						sftp.put(new FileInputStream(f), inputFiles.get(sourceFile));
					}
					Thread.sleep(1000);
				}
			}

			// file transfer complete
			logger.info("File transfer complete");

		} catch (Exception e)
		{
			throw new FileTransferException();
		}

		return destPbsScriptPath;
	}

	public ConnectionHandler getConnectionHandler()
	{
		return this.connectionHandler;
	}

	public void setConnectionHandler(ConnectionHandler connectionHandler)
	{
		this.connectionHandler = connectionHandler;
	}

	public Constants getConstants()
	{
		return this.constants;
	}

	public void setConstants(Constants constants)
	{
		this.constants = constants;
	}

}
