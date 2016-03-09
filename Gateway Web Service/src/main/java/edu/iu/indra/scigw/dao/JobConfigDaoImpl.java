package edu.iu.indra.scigw.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import edu.iu.indra.scigw.config.JobConfig;
import edu.iu.indra.scigw.config.JobStatus;
import edu.iu.indra.scigw.util.JobMapper;

@Component("jobConfigDao")
public class JobConfigDaoImpl implements JobConfigDao
{

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	/*
	 * @Override public List<JobConfig> getJobList() { String SQL =
	 * "SELECT * FROM job_details"; Map<String, String> namedParameters = new
	 * HashMap<String, String>();// List<JobConfig> jobRows = (List<JobConfig>)
	 * jdbcTemplate.query(SQL, namedParameters, new JobMapper()); return
	 * jobRows; }
	 */

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
		String sql = "DELETE from job_details WHERE JOBID = :JOBID";
		Map<String, String> namedParameters = new HashMap<String, String>();//
		jdbcTemplate.update(sql, namedParameters);
		// TODO Auto-generated method stub

	}

	@Override
	public JobConfig getJobDetailsByJobID(String jobID)
	{
		// TODO Auto-generated method stub
		String SQL = "SELECT * FROM job_details WHERE JOBID = :JOBID";
		Map<String, String> namedParameters = new HashMap<String, String>();//
		return (JobConfig) jdbcTemplate.query(SQL, namedParameters, new JobMapper());
	}

	@Override
	public List<JobStatus> getJobStatusForUser(String userName)
	{
		// TODO Auto-generated method stub
		String SQL = "SELECT * FROM job_details";
		Map<String, String> namedParameters = new HashMap<String, String>();//
		List<JobStatus> jobRows = (List<JobStatus>) jdbcTemplate.query(SQL, namedParameters,
				new RowMapper<JobStatus>() {

					@Override
					public JobStatus mapRow(ResultSet rs, int rowNum) throws SQLException
					{
						// TODO Auto-generated method stub
						JobStatus jobRow = new JobStatus();
						jobRow.setJobId(rs.getString("JOBID"));
						jobRow.setJobUID(UUID.fromString(rs.getString("JOBUID")));
						return jobRow;
					}

				});
		return jobRows;
	}

}
