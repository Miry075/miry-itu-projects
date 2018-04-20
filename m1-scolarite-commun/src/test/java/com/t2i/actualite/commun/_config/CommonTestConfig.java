package com.t2i.actualite.commun._config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;

import com.t2i.actualite.commun.tools.MockTextGenerator;

/**
 * @author Fenonantenaina
 *
 */
@Profile("tests")
@Configuration
@ComponentScan({"com.t2i.actualite.commun"})
@PropertySource({"classpath:config/application-technique-test.properties"})
public class CommonTestConfig {

	
    @Bean
	public MockTextGenerator mockTextGenerator(@Value("${application.file.news}")Resource sourceFile){
    	MockTextGenerator generator = new MockTextGenerator(sourceFile);
    	generator.initialize();
    	return generator;
	};
	
	
}
