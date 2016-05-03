package edu.iu.indra.scigw.filehandler;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;

import edu.iu.indra.scigw.config.JobConfig;
import edu.iu.indra.scigw.connectionhandler.ConnectionHandler;
import edu.iu.indra.scigw.exceptions.ConnectionFailedException;
import edu.iu.indra.scigw.exceptions.FileTransferException;
import edu.iu.indra.scigw.exceptions.SciGwWebException;
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
	public void copyFile(String source, String destination, String hostname) throws FileTransferException
	{
		try
		{
			ChannelSftp sftp = connectionHandler.getSftpChannel(hostname);
			sftp.connect();
			sftp.put(source, destination);

		} catch (ConnectionFailedException e)
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
			ChannelSftp sftp = connectionHandler.getSftpChannel(config.getHostname());
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

	@Override
	public String downloadDirectoryAsZip(String folder, String hostname) throws FileTransferException
	{
		logger.info("Downloading directory " + folder);

		// create temp zip file
		File file;
		ZipOutputStream zipOutputStream = null;

		try
		{
			file = File.createTempFile("temp-zip", ".zip");

			FileOutputStream fileOutputStream = new FileOutputStream(file);

			// get sftp channel
			ChannelSftp sftp = connectionHandler.getSftpChannel(hostname);
			sftp = connectionHandler.getSftpChannel(hostname);
			sftp.connect();

			zipOutputStream = new ZipOutputStream(fileOutputStream);
			Vector<ChannelSftp.LsEntry> list = new Vector<ChannelSftp.LsEntry>();

			// list of all files from directory
			list = sftp.ls(folder);

			for (ChannelSftp.LsEntry listItem : list)
			{
				// skip directory files
				if (!listItem.getFilename().matches(".") && !listItem.getFilename().matches(".."))
				{
					String filePath = folder + listItem.getFilename();
					zipFile(new BufferedInputStream(sftp.get(filePath)), listItem.getFilename(),
							zipOutputStream);
				}
			}

			zipOutputStream.close();

		} catch (Exception e)
		{
			logger.error("Error in downloading file", e);
			throw new SciGwWebException();
		} finally
		{
			if (zipOutputStream != null)
			{
				try
				{
					zipOutputStream.close();
				} catch (IOException e)
				{
					// give up
				}
			}
		}

		return file.getAbsolutePath();
	}

	public void zipFile(BufferedInputStream fileInputStream, String parentName,
			ZipOutputStream zipOutputStream)
	{
		try
		{
			// A ZipEntry represents a file entry in the zip archive
			// We name the ZipEntry after the original file's name
			ZipEntry zipEntry = new ZipEntry(parentName);
			zipOutputStream.putNextEntry(zipEntry);

			byte[] buf = new byte[1024];
			int bytesRead;

			// Read the input file by chucks of 1024 bytes
			// and write the read bytes to the zip stream
			while ((bytesRead = fileInputStream.read(buf)) > 0)
			{
				zipOutputStream.write(buf, 0, bytesRead);
			}

			// close ZipEntry to store the stream to the file
			zipOutputStream.closeEntry();

		} catch (IOException e)
		{
			logger.error("Error in zipping file", e);
		}
	}

}
