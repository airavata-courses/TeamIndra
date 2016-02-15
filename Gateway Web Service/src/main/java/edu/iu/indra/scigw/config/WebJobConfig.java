package edu.iu.indra.scigw.config;

import java.io.File;
import java.io.IOException;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.web.multipart.MultipartFile;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WebJobConfig extends JobConfig
{

	private InputFile userInputFile;

	public InputFile getUserInputFile()
	{
		return this.userInputFile;
	}

	public void setUserInputFile(InputFile userInputFile)
	{
		this.userInputFile = userInputFile;
	}

	@JsonIgnore
	public JobConfig getJobPopulatedJobConfig()
	{
		if (userInputFile != null)
		{
			File tmpFile = new File(userInputFile.getFileName());

			try
			{
				MultipartFile inputFile = userInputFile.getFile();

				if (inputFile != null)
				{
					userInputFile.getFile().transferTo(tmpFile);
					addInputFile(tmpFile.getName(), tmpFile.getAbsolutePath());
				}

			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}

		return this;
	}

}
