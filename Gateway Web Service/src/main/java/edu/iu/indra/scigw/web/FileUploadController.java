package edu.iu.indra.scigw.web;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import edu.iu.indra.scigw.exceptions.FileTransferException;
import edu.iu.indra.scigw.filehandler.FileHandler;
import edu.iu.indra.web.response.SimpleResponse;

@Controller
public class FileUploadController
{

	@Autowired
	protected FileHandler fileHandler;

	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public @ResponseBody String provideUploadInfo()
	{
		return "You can upload a file by posting to this same URL.";
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public @ResponseBody String handleFileUpload(@RequestParam("file") MultipartFile file)
	{

		System.out.println("test");
		if (!file.isEmpty())
		{
			try
			{
				System.out.println("Coming here");
				File fname = new File(
						"C:\\Users\\Pratish\\Documents\\Assignments\\SG\\WebService\\Test Files\\newinputfile.txt");
				byte[] bytes = file.getBytes();
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(fname));
				stream.write(bytes);
				stream.close();
				return "You successfully uploaded ConfigFile !";
			} catch (Exception e)
			{
				return "You failed to upload ConfigFile => " + e.getMessage();
			}
		} else
		{
			return "You failed to upload Config because the file was empty.";
		}
	}

	@RequestMapping(value = "/getFile", method = RequestMethod.GET)

	public @ResponseBody SimpleResponse getJobStatus(@RequestParam String fileName)
	{
		String status = "Failed";
		try
		{
			status = fileHandler.downloadFile("//N//u//smhaiska//Karst//mpi_hello.c");
		} catch (FileTransferException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		;
		// String status = jobMonitor.getJobStatusByUser();
		// jobMonitor.getJobStatusByJobId(jobId);
		// status = jobConfigDAO.getJobList();
		return new SimpleResponse(true, status);
	}

}
