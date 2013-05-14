package com.mycare.actions.quartz;

import java.util.List;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.xml.JobSchedulingBundle;
import org.quartz.xml.JobSchedulingDataProcessor;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class QuartzSchedulerListener extends JobSchedulingDataProcessor implements ServletContextListener{		
	public void contextInitialized(ServletContextEvent arg0) {
		try
		{
		SchedulerFactory sf=new StdSchedulerFactory();			//instantiation
		Scheduler sch=sf.getScheduler();						//instantiation
		System.out.println("Validating...");
		
		this.processFile("/home/bovas/Workspace2K12/PharmacyDownloadServer/resource/DownloadSchedule.xml");
		System.out.println("Validated");
		
		List jl=this.jobsToSchedule;
		System.out.println(jl.size());
		
		for(int i=0;i<jl.size();i++)
		{
			JobSchedulingBundle jsb=(JobSchedulingBundle)jl.get(i);

			try
			{
			this.scheduleJob(jsb,sch,true);
			}catch(SchedulerException sc){sc.printStackTrace();}
		
			JobDetail jd=jsb.getJobDetail();
			List tl=jsb.getTriggers();
			System.out.println(jd.getName());

			for(int j=0;j<tl.size();j++)
			{
				Trigger t=(Trigger)tl.get(j);
				System.out.println(t.getName());
			}
		}
		sch.start();
		System.out.println("Scheduler Started");
		}catch(Exception e){e.printStackTrace();}
	}
	
	public void contextDestroyed(ServletContextEvent arg0) {
	}
}
