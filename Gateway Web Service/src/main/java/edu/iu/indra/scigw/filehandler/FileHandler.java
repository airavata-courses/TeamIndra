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
	public void copyFile(String source, String destination, String hostname) throws FileTransferException;

	public String downloadDirectoryAsZip(String folder, String hostname) throws FileTransferException;

	/**
	 * transfers job files from jobConfig to server and returns destination path
	 * of pbs script for scheduling
	 * 
	 * @param config
	 * @return PBS script path on server
	 */
	public String transferApplicationFiles(JobConfig config) throws FileTransferException;
}
