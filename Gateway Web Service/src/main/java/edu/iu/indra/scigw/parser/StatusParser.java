package edu.iu.indra.scigw.parser;

import java.util.ArrayList;

import edu.iu.indra.scigw.config.JobStatus;

public class StatusParser {
	
	public ArrayList<JobStatus> jobStatusParser(String output){
		
		ArrayList<JobStatus> listofprocess = new ArrayList<JobStatus>();
		String lines[] = output.split("\n");
		int nooflines = lines.length - 5;
		
		System.out.println(lines.length);
		for (int i=5; i < lines.length; i++){
//			System.out.println(lines[i]);
			String fields[] = lines[i].split("\\s+");
			
//				System.out.println("Field["+j+"] : "+fields[j]);
			JobStatus js = new JobStatus();
			js.setJobId(fields[0]);
			js.setJobName(fields[3]);
			js.setStatus(fields[9]);
			
			listofprocess.add(js);
		}
		
		return listofprocess;
	}

}
