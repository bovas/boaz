package com.mycare.actions.quartz;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.xml.JobSchedulingBundle;
import org.quartz.xml.JobSchedulingDataProcessor;

public class QuartzScheduleTest extends JobSchedulingDataProcessor{
	public QuartzScheduleTest() throws SchedulerException, ParseException{
		SchedulerFactory stdf = new StdSchedulerFactory();
		Scheduler schedulr = stdf.getScheduler();
		
		
		JobDetail jd=new JobDetail("myjob",schedulr.DEFAULT_GROUP,QuartzJob.class);			//job details
		
		SimpleTrigger st=new SimpleTrigger("mytrigger",schedulr.DEFAULT_GROUP,new Date(),null,SimpleTrigger.REPEAT_INDEFINITELY,60L*100L);		//trigger details(crontab)
		
		CronTrigger ct=new CronTrigger("cronTrigger","group2","0 0/1 * * * ?");
		
		schedulr.scheduleJob(jd, ct);	
		
		schedulr.start();
		
		List jl=this.jobsToSchedule;
		System.out.println(jl.size());
		for(int i=0;i<jl.size();i++)
		{
			JobSchedulingBundle jsb=(JobSchedulingBundle)jl.get(i);
			JobDetail jobdetail=jsb.getJobDetail();
			List tl=jsb.getTriggers();
			System.out.println(jobdetail.getName());

			for(int j=0;j<tl.size();j++)
			{
				Trigger t=(Trigger)tl.get(j);
				System.out.println(t.getName());
			}
		}
		
			//adding job details,trigger details to the scheduler
	}
	public static void main(String args[]){
	  try{
		  new QuartzScheduleTest();
	   }catch(Exception e){}  
	}
}
