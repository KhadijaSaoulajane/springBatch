package com.dija.tpBatch.batchconf;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.dija.tpBatch.entities.Transaction;
import com.dija.tpBatch.entities.TransactionInput;

@Configuration
@EnableBatchProcessing
public class BatchConfig {
	
	// On defini le job qui va etre utilisé par job launcher 
	@Bean
	public Job job(JobBuilderFactory jobBuilderFactory,
			StepBuilderFactory stepBuilderFactory,
			ItemReader<TransactionInput> itemReader,
			ItemProcessor<TransactionInput, Transaction> itemProcessor,
			ItemWriter<Transaction> itemWriter) {
		
		//on defini step en lui assignant l'item reader, writer et processor
		Step step= stepBuilderFactory.get("myStep1")
				.<TransactionInput,Transaction>chunk(10)
				.reader(itemReader)
				.processor(itemProcessor)
				.writer(itemWriter)
				.build();
		
		//on retourn le job 
		return jobBuilderFactory.get("myJob1")
		       .start(step).build();
		
	}
	
	// on implemente maintenant le reader
	// on utilise le flatfileItem reader puisqu'on a un CSV
	
	@Bean
	public FlatFileItemReader<TransactionInput> itemReader(){
		FlatFileItemReader<TransactionInput> flatFileItemReader = new FlatFileItemReader<>();
		// set resource be3da
		flatFileItemReader.setResource( new ClassPathResource("transactionsfile.csv"));
		// le nom du item reader 
		flatFileItemReader.setName("myReader1");
		// On veut sauter la premiere ligne qui contient le header dans le fichier csv
		flatFileItemReader.setLinesToSkip(1);
		// on doit mapper ces datas dans la classe Transaction
		flatFileItemReader.setLineMapper(lineMapper());
		
		return flatFileItemReader;
		
	}

	// definir la fonction lineMapper du flatFileItemReader
	@Bean
	public LineMapper<TransactionInput> lineMapper() {
		DefaultLineMapper<TransactionInput> defaultLineMapper = new DefaultLineMapper<>();
		
		// pour separer les colonne de fichier avec virgule
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		lineTokenizer.setDelimiter(",");
		lineTokenizer.setStrict(false);
		// definir les nom de colonnes comme dans le fichier csv
		lineTokenizer.setNames(new String[] {"idTransaction","idCompte","montant","dateTransaction"});
		defaultLineMapper.setLineTokenizer(lineTokenizer);
		
		// pour mapper chaque colonne avec les champs de Transaction 
		BeanWrapperFieldSetMapper<TransactionInput> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
		fieldSetMapper.setTargetType(TransactionInput.class);
		
		defaultLineMapper.setFieldSetMapper(fieldSetMapper);

		return defaultLineMapper;
	}

}
