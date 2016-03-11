package edu.iu.indra.scigw.filehandler;

import edu.iu.indra.scigw.config.JobConfig;
import edu.iu.indra.scigw.exceptions.FileTransferException;

public interface FileHandler
{
	/**
	 * Copy source file at destination
	 * 
	 * @param source
	 * @param destination
	 */
	public void copyFile(String source, String destination) throws FileTransferException;

	public String downloadFile(String source) throws FileTransferException;

	public String downloadDirectoryAsZip(String folder) throws FileTransferException;

	/**
	 * transfers job files from jobConfig to server and returns destination path
	 * of pbs script for scheduling
	 * 
	 * @param config
	 * @return PBS script path on server
	 */
	public String transferApplicationFiles(JobConfig config) throws FileTransferException;
}
