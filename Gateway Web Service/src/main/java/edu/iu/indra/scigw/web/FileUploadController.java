package edu.iu.indra.scigw.web;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import edu.iu.indra.scigw.jobhandler.JobHandler;
import edu.iu.indra.scigw.util.Constants;
import edu.iu.indra.web.response.SimpleResponse;

@Controller
public class FileUploadController
{
	final static Logger logger = Logger.getLogger(FileUploadController.class);

	@Autowired
	JobHandler jobHandler;

	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public @ResponseBody String provideUploadInfo()
	{
		return "You can upload a file by posting to this same URL.";
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public @ResponseBody SimpleResponse handleFileUpload(@RequestParam("file") MultipartFile file)
	{
		if (!file.isEmpty())
		{
			try
			{
//				File fname = File.createTempFile("input-file", ".tpr");
				File fname = new File(Constants.inputFilePath+"input.tpr");
				System.out.println("Inside files structure");
				byte[] bytes = file.getBytes();
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(fname));
				stream.write(bytes);
				stream.close();
				
				System.out.println("Came here");
				return new SimpleResponse(true, "You successfully uploaded ConfigFile !");
				
			} catch (Exception e)
			{
				System.out.println("Failed");
				return new SimpleResponse(false,"You failed to upload ConfigFile => " + e.getMessage());
			}
		} else
		{
			System.out.println("Failed");
			return new SimpleResponse(false,"You failed to upload Config because the file was empty.");
		}
	}

	@RequestMapping(value = "/downloadoutput", method = RequestMethod.GET, produces = "application/zip")
	public @ResponseBody SimpleResponse downloadOutput(@RequestParam String jobId,
			HttpServletResponse response)
	{
		File file = null;

		try
		{
			String filePath = jobHandler.downloadFilesForJob(jobId);
			file = new File(filePath);

			if (file.exists())
			{
				String outFile = "filename=" + jobId + "_output.zip";
				response.setHeader("Content-Disposition", "attachment; " + outFile);
				FileInputStream fis = new FileInputStream(file);
				IOUtils.copy(fis, response.getOutputStream());
				response.flushBuffer();
			}
		} catch (Exception e)
		{
			logger.error("Error in file download", e);
			return new SimpleResponse(false, "Error in downloading file");
		} finally
		{
			// delete temp file
			if (file != null && file.exists())
			{
				file.delete();
			}
		}

		return new SimpleResponse(true, "File downloaded successfully");
	}
}
