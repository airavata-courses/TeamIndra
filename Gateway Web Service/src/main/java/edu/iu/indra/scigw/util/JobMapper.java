package edu.iu.indra.scigw.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import edu.iu.indra.scigw.config.JobConfig;

public class JobMapper implements RowMapper<JobConfig>
{

	@Override
	public JobConfig mapRow(ResultSet rs, int rowNum) throws SQLException
	{
		// TODO Auto-generated method stub
		JobConfig jobRow = new JobConfig();
		jobRow.setJobID(rs.getString("JOBID"));
		jobRow.setWallTime((rs.getString("WALLTIME")));
		jobRow.setEmail(rs.getString("EMAIL"));
		return jobRow;

	}

}
