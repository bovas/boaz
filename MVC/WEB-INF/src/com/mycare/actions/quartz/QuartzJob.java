package com.mycare.actions.quartz;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class QuartzJob implements Job{
	public void execute(JobExecutionContext ctxt) throws JobExecutionException{
		System.out.println("Hello World Quartz Scheduler: " + new Date());
		
		 String jname = ctxt.getJobDetail().getName();
		  String jgroup = ctxt.getJobDetail().getGroup();
		  System.out.println("job Name :"+jname+" Job Group Name :"+jgroup);
		  JobDataMap jdMap = ctxt.getJobDetail().getJobDataMap();
		  String name = jdMap.getString("name");			
	}
}



