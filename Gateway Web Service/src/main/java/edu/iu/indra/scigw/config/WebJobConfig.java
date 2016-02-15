package edu.iu.indra.scigw.config;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.web.multipart.MultipartFile;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WebJobConfig extends JobConfig
{

	private List<InputFile> inputFileList = new ArrayList<InputFile>();

	public List<InputFile> getInputFileList()
	{
		return this.inputFileList;
	}

	public void setInputFileList(List<InputFile> inputFileList)
	{
		this.inputFileList = inputFileList;
	}

	@JsonIgnore
	public JobConfig getJobPopulatedJobConfig()
	{
		if (inputFileList != null)
		{
			for (InputFile file : inputFileList)
			{
				File tmpFile = new File(file.getFileName());

				try
				{
					MultipartFile inputFile = file.getFile();

					if (inputFile != null)
					{
						file.getFile().transferTo(tmpFile);
						addInputFile(tmpFile.getName(), tmpFile.getAbsolutePath());
					}

				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}

		return this;
	}

}
