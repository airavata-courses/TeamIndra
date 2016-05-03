package edu.iu.indra.scigw.config;

import org.springframework.web.multipart.MultipartFile;

public class InputFile
{
	private String fileName;
	private MultipartFile file;

	public InputFile(String fileName, MultipartFile file)
	{
		super();
		this.fileName = fileName;
		this.file = file;
	}

	public InputFile()
	{}

	public String getFileName()
	{
		return this.fileName;
	}

	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}

	public MultipartFile getFile()
	{
		return this.file;
	}

	public void setFile(MultipartFile file)
	{
		this.file = file;
	}

	@Override
	public String toString()
	{
		return "InputFile [fileName=" + this.fileName + ", file size=" + this.file.getSize() + "]";
	}

}
