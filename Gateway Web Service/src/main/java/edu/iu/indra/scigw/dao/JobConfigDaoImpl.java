package edu.iu.indra.scigw.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import edu.iu.indra.scigw.config.JobConfig;

@Component("jobConfigDao")
public class JobConfigDaoImpl implements JobConfigDao
{

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public List<JobConfig> getJobList()
	{
		String getJobs = "select * from job_details";
		return jdbcTemplate.query(getJobs, new RowMapper<JobConfig>() {
			public JobConfig mapRow(ResultSet rs, int rowNum) throws SQLException
			{
				// TODO add mapping here
				return new JobConfig();
			}
		});
	}
}
