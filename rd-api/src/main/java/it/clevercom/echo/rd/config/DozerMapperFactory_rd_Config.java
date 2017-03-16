package it.clevercom.echo.rd.config;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DozerMapperFactory_rd_Config {
	
	@Bean(name = "rdDozerMapper")
	public DozerBeanMapper configDozerBeanMapper() {
		DozerBeanMapper mapper = new DozerBeanMapper();
		List<String> mappingFileUrls = new ArrayList<String>();
		mappingFileUrls.add("dozer-rd-bean-mappings.xml");
		mapper.setMappingFiles(mappingFileUrls);
		
//		Map<String, CustomConverter> customConvertersWithId = new HashMap<String, CustomConverter>();
//		// add custom converter here
//		customConvertersWithId.put("municipality-plain", new Municipality2MunicipalityDTO_PlainConverter(Municipality.class, MunicipalityDTO.class));
//
//		mapper.setCustomConvertersWithId(customConvertersWithId);
		
		return mapper;
	}
	
}