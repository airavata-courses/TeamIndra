package edu.iu.indra.scigw.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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
		// String getJobs = "select * from job_details";
		// return jdbcTemplate.query(getJobs, new RowMapper<JobConfig>() {
		// public JobConfig mapRow(ResultSet rs, int rowNum) throws SQLException
		// {
		// // TODO add mapping here
		// return new JobConfig();
		// }
		// });
		return null;
	}

	@Override
	public void insertJobDetails(JobConfig jobRow)
	{
		// TODO Auto-generated method stub
		Map<String, String> namedParameters = new HashMap<String, String>();//

		namedParameters.put("UUID", jobRow.getUid().toString());
		namedParameters.put("JOBID", jobRow.getJobID());
		namedParameters.put("WALLTIME", jobRow.getWallTime());
		namedParameters.put("EMAIL", jobRow.getEmail());
		String sql = "INSERT INTO job_details (UUID, JOBID, WALLTIME, EMAIL) VALUES (:UUID, :JOBID, :WALLTIME, :EMAIL)";
		jdbcTemplate.update(sql, namedParameters);

	}

	@Override
	public void deleteJob(String jobID)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public JobConfig getJobDetails(String jobName)
	{
		// TODO Auto-generated method stub
		return null;
	}
}
