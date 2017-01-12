package it.clevercom.echo.config;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DozerMapperFactoryConfig {
	
	@Bean(name = "dozerMapper")
	public DozerBeanMapper configDozerBeanMapper() {
		DozerBeanMapper mapper = new DozerBeanMapper();
		List<String> mappingFileUrls = new ArrayList<String>();
		mappingFileUrls.add("dozer-bean-mappings.xml");
		mapper.setMappingFiles(mappingFileUrls);
		return mapper;
	}
	
}