package edu.iu.indra.scigw.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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
import edu.iu.indra.scigw.config.JobStatus.JOB_STATUS;
import edu.iu.indra.scigw.util.JobMapper;

@Component("jobConfigDao")
public class JobConfigDaoImpl implements JobConfigDao
{

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public void insertJobDetails(JobConfig jobRow)
	{
		Map<String, String> namedParameters = new HashMap<String, String>();//

		Date date = new Date();
		namedParameters.put("UUID", jobRow.getUid().toString());
		namedParameters.put("JOBID", jobRow.getJobID());
		namedParameters.put("WALLTIME", jobRow.getWallTime());
		namedParameters.put("EMAIL", jobRow.getEmail());
		namedParameters.put("JOB_STATUS", JOB_STATUS.NA.name());
		namedParameters.put("SUBMIT_TIME", "" + date.getTime());
		namedParameters.put("USERNAME", jobRow.getUsername());
		namedParameters.put("JOBNAME", jobRow.getJobName());
		String sql = "INSERT INTO job_details (UUID, JOBID, WALLTIME, EMAIL, JOB_STATUS, SUBMIT_TIME, USERNAME, JOBNAME) VALUES (:UUID, :JOBID, :WALLTIME, :EMAIL, :JOB_STATUS, :SUBMIT_TIME, :USERNAME, :JOBNAME)";
		jdbcTemplate.update(sql, namedParameters);
	}

	@Override
	public void deleteJob(String jobID)
	{
		String sql = "DELETE from job_details WHERE JOBID = :JOBID";
		Map<String, String> namedParameters = new HashMap<String, String>();//
		jdbcTemplate.update(sql, namedParameters);
	}

	@Override
	public JobConfig getJobDetailsByJobID(String jobID)
	{
		String SQL = "SELECT * FROM job_details WHERE JOBID = :JOBID";
		Map<String, String> namedParameters = new HashMap<String, String>();//
		return (JobConfig) jdbcTemplate.query(SQL, namedParameters, new JobMapper());
	}

	@Override
	public List<JobStatus> getJobStatusForUser(String userName)
	{
		String SQL = "SELECT * FROM job_details where username = :USERNAME";
		Map<String, String> namedParameters = new HashMap<String, String>();//
		namedParameters.put("USERNAME", userName);

		List<JobStatus> jobRows = (List<JobStatus>) jdbcTemplate.query(SQL, namedParameters,
				new RowMapper<JobStatus>() {

					@Override
					public JobStatus mapRow(ResultSet rs, int rowNum) throws SQLException
					{
						JobStatus jobRow = new JobStatus();
						jobRow.setJobId(rs.getString("JOBID"));
						jobRow.setJobUID(UUID.fromString(rs.getString("UUID")));
						jobRow.setStatus(rs.getString("JOB_STATUS"));
						jobRow.setJobSubmitTime(rs.getLong("SUBMIT_TIME"));
						jobRow.setJobName(rs.getString("JOBNAME"));
						return jobRow;
					}
				});
		return jobRows;
	}
	
	
	@Override
	public List<JobStatus> getJobStatusOfAllJobs()
	{
		String SQL = "SELECT * FROM job_details where job_status not like 'C'";
		Map<String, String> namedParameters = new HashMap<String, String>();//

		return jdbcTemplate.query(SQL, namedParameters,
				new RowMapper<JobStatus>() {

					@Override
					public JobStatus mapRow(ResultSet rs, int rowNum) throws SQLException
					{
						JobStatus jobRow = new JobStatus();
						jobRow.setJobId(rs.getString("JOBID"));
						jobRow.setJobUID(UUID.fromString(rs.getString("UUID")));
						jobRow.setStatus(rs.getString("JOB_STATUS"));
						jobRow.setJobSubmitTime(rs.getLong("SUBMIT_TIME"));
						jobRow.setJobName(rs.getString("JOBNAME"));
						return jobRow;
					}
				});
		
		
		
	}

	@Override
	public void updateJobStatus(List<JobStatus> jobs)
	{
		for (JobStatus status : jobs)
		{
			Map<String, String> namedParameters = new HashMap<String, String>();

			namedParameters.put("JOBID", status.getJobId());
			namedParameters.put("STATUS", status.getStatus());
			String sql = "UPDATE job_details set job_status=:STATUS where JOBID=:JOBID";
			jdbcTemplate.update(sql, namedParameters);
		}
	}
}
