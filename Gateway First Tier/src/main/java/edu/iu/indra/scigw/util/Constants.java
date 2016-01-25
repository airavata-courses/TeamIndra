package edu.iu.indra.scigw.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.iu.indra.scigw.input.UserInput;

@Component
public class Constants
{
	@Autowired
	public UserInput userConfig;

	public static final String scratch_dir_path = "//N//dc2//scratch//";
	public static final String default_filename = "job.tar";
	public static final String sortAppExe = "sortApp";

	public String getUsername()
	{
		return this.userConfig.getUsername();
	}

	public String getScratchDirPath()
	{
		return scratch_dir_path + getUsername() + "//";
	}

	public String getSortAppDirPath()
	{
		return scratch_dir_path + getUsername() + "//" + "sortapp" + "//";
	}

	public String getSortAppExeCommand(String inputFile)
	{
		return getSortAppDirPath() + sortAppExe + " " + inputFile;
	}

	public String getJobDirPath(String uuid)
	{
		return getScratchDirPath() + uuid + "//";
	}

}
