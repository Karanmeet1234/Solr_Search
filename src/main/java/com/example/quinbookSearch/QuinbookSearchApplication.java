package com.example.quinbookSearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;
@EnableFeignClients
@SpringBootApplication
@EnableSolrRepositories
public class QuinbookSearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuinbookSearchApplication.class, args);
	}

}
