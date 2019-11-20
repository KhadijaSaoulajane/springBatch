package com.dija.tpBatch;

import java.util.Random;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.dija.tpBatch.entities.Compte;
import com.dija.tpBatch.repository.ICompteRepository;

@SpringBootApplication
//@EnableScheduling
public class TpBatchApplication implements CommandLineRunner {

	@Autowired
	JobLauncher jobLauncher;
	@Autowired 
	Job job;
	@Autowired 
	ICompteRepository compteRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(TpBatchApplication.class, args);		
	}
	
	// In case we want to schedule it
	//@Scheduled(cron = "0 */1 * * * ?")
	@Override
    public void run(String... args) throws Exception 
    {
		
		// on ajoute des comptes a la base de données pour ne pas avoir de probleme de Null pointer
		for(int i=1; i<10;i++) {
			Compte c = new Compte();
			c.setIdCompte(i);
			c.setSolde(i*10000);
			compteRepository.save(c);
            
		}
        JobParameters params = new JobParametersBuilder()
                    .addString("JobID", String.valueOf(System.currentTimeMillis()))
                    .toJobParameters();
        jobLauncher.run(job, params);
    }


}
