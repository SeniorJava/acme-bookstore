package acme.sales.bookstore.integration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author vmuravlev
 */
public class RunImportBookJob {
    public static void main(String[] args) throws Exception {

        ApplicationContext appCtx = new ClassPathXmlApplicationContext("integration-context.xml");

        JobLauncher jobLauncher = appCtx.getBean(JobLauncher.class);
        Job importJob = appCtx.getBean("importBooksJob", Job.class);

        JobExecution jobExecution = jobLauncher.run(importJob, new JobParameters());
        System.out.printf("Job status: %s\n", jobExecution.getStatus().name());
    }
}