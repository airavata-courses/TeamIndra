package edu.iu.indra.scigw.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import edu.iu.indra.scigw.config.JobConfig;
import edu.iu.indra.scigw.config.JobStatus;
import edu.iu.indra.scigw.config.JobStatus.JOB_STATUS;

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
		namedParameters.put("HOSTNAME", jobRow.getHostname());
		String sql = "INSERT INTO job_details (UUID, JOBID, WALLTIME, EMAIL, JOB_STATUS, SUBMIT_TIME, USERNAME, JOBNAME, LOCAL_PATH, HOSTNAME) VALUES (:UUID, :JOBID, :WALLTIME, :EMAIL, :JOB_STATUS, :SUBMIT_TIME, :USERNAME, :JOBNAME, NULL, :HOSTNAME)";
		jdbcTemplate.update(sql, namedParameters);
	}

	@Override
	public void deleteJob(String jobID)
	{
		String sql = "DELETE from job_details WHERE JOBID = :JOBID";
		Map<String, String> namedParameters = new HashMap<String, String>();//
		namedParameters.put("JOBID", jobID);
		jdbcTemplate.update(sql, namedParameters);
	}

	@Override
	public JobStatus getJobDetailsByJobID(String jobID)
	{
		String SQL = "SELECT * FROM job_details WHERE JOBID = :JOBID";
		Map<String, String> namedParameters = new HashMap<String, String>();
		namedParameters.put("JOBID", jobID);
		return jdbcTemplate.query(SQL, namedParameters, new ResultSetExtractor<JobStatus>() {

			@Override
			public JobStatus extractData(ResultSet rs) throws SQLException, DataAccessException
			{
				rs.next();
				JobStatus jobRow = new JobStatus();
				jobRow.setJobUID(UUID.fromString(rs.getString("UUID")));
				jobRow.setJobId(rs.getString("JOBID"));
				jobRow.setLocalPath(rs.getString("LOCAL_PATH"));
				jobRow.setHostname(rs.getString("HOSTNAME"));
				return jobRow;
			}
		});
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
						jobRow.setHostname(rs.getString("HOSTNAME"));
						return jobRow;
					}
				});
		return jobRows;
	}

	@Override
	public List<JobStatus> getAllQuedJobs()
	{
		String SQL = "SELECT * FROM job_details where job_status not like 'C'";
		Map<String, String> namedParameters = new HashMap<String, String>();//

		return jdbcTemplate.query(SQL, namedParameters, new RowMapper<JobStatus>() {

			@Override
			public JobStatus mapRow(ResultSet rs, int rowNum) throws SQLException
			{
				JobStatus jobRow = new JobStatus();
				jobRow.setJobId(rs.getString("JOBID"));
				jobRow.setJobUID(UUID.fromString(rs.getString("UUID")));
				jobRow.setStatus(rs.getString("JOB_STATUS"));
				jobRow.setJobSubmitTime(rs.getLong("SUBMIT_TIME"));
				jobRow.setJobName(rs.getString("JOBNAME"));
				jobRow.setHostname(rs.getString("HOSTNAME"));
				return jobRow;
			}
		});
	}

	@Override
	public List<JobStatus> getJobsForOutputSync()
	{
		String SQL = "SELECT * FROM job_details where job_status like 'C' and local_path is null";
		Map<String, String> namedParameters = new HashMap<String, String>();//

		return jdbcTemplate.query(SQL, namedParameters, new RowMapper<JobStatus>() {

			@Override
			public JobStatus mapRow(ResultSet rs, int rowNum) throws SQLException
			{
				JobStatus jobRow = new JobStatus();
				jobRow.setJobId(rs.getString("JOBID"));
				jobRow.setJobUID(UUID.fromString(rs.getString("UUID")));
				jobRow.setStatus(rs.getString("JOB_STATUS"));
				jobRow.setJobSubmitTime(rs.getLong("SUBMIT_TIME"));
				jobRow.setJobName(rs.getString("JOBNAME"));
				jobRow.setLocalPath(rs.getString("LOCAL_PATH"));
				jobRow.setHostname(rs.getString("HOSTNAME"));
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
			namedParameters.put("LOCAL_PATH", status.getLocalPath());
			String sql = "UPDATE job_details set job_status=:STATUS, local_path=:LOCAL_PATH where JOBID=:JOBID";
			jdbcTemplate.update(sql, namedParameters);
		}
	}

	public NamedParameterJdbcTemplate getJdbcTemplate()
	{
		return this.jdbcTemplate;
	}

	public void setJdbcTemplate(NamedParameterJdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}

}
