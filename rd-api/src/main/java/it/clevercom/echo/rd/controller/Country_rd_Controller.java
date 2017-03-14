package it.clevercom.echo.rd.controller;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.clevercom.echo.common.exception.model.BadRequestException;
import it.clevercom.echo.common.exception.model.EchoException;
import it.clevercom.echo.common.exception.model.PageNotFoundException;
import it.clevercom.echo.common.exception.model.RecordNotFoundException;
import it.clevercom.echo.common.logging.annotation.Loggable;
import it.clevercom.echo.rd.model.dto.CountryDTO;
import it.clevercom.echo.rd.model.dto.MunicipalityDTO;
import it.clevercom.echo.rd.model.dto.PagedDTO;
import it.clevercom.echo.rd.model.dto.ProvinceDTO;
import it.clevercom.echo.rd.model.dto.RegionDTO;
import it.clevercom.echo.rd.model.entity.Country;
import it.clevercom.echo.rd.model.entity.Municipality;
import it.clevercom.echo.rd.model.entity.Province;
import it.clevercom.echo.rd.model.entity.Region;
import it.clevercom.echo.rd.model.jpa.helper.SpecificationQueryHelper;
import it.clevercom.echo.rd.model.jpa.helper.SpecificationsBuilder;
import it.clevercom.echo.rd.repository.ICountry_rd_Repository;
import it.clevercom.echo.rd.repository.IMunicipality_rd_Repository;
import it.clevercom.echo.rd.repository.IProvince_rd_Repository;
import it.clevercom.echo.rd.repository.IRegion_rd_Repository;

@Controller
@RestController
@RequestMapping("rd/types/country")
@PropertySource("classpath:rest.platform.properties")
@PropertySource("classpath:rest.rd.properties")

public class Country_rd_Controller {
	// hard coded data 
	public static Long localCountryId = 1105l;
	public static Long unknownRegionId = 0l;
	
	@Autowired
	private Environment env;
	
	@Autowired
	private ICountry_rd_Repository repo;
	
	@Autowired
	private IRegion_rd_Repository repo_r;
	
	@Autowired
	private IProvince_rd_Repository repo_p;
	
	@Autowired
	private IMunicipality_rd_Repository repo_m;
	
	@Autowired
    private DozerBeanMapper dozerMapper;
	
	private final Logger logger = Logger.getLogger(this.getClass());
	
	// used to bind it in exception message
	private static String entity = "Country";
	private static String r_entity = "Region";
	private static String p_entity = "Province";
	private static String m_entity = "Muicipality";
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws EchoException
	 */
	@Transactional("rdTm")
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody CountryDTO get(@PathVariable Long id) throws Exception {
		Country entity = repo.findOne(id);
		if (entity == null) throw new RecordNotFoundException(Country_rd_Controller.entity, id.toString());
		return dozerMapper.map(entity, CountryDTO.class);
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws EchoException
	 */
	@Transactional("rdTm")
	@RequestMapping(value="/{id_country}/region", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody PagedDTO<RegionDTO> getRegionListByCountry(@PathVariable Long id_country) throws Exception {
		List<Region> regions = new ArrayList<Region>();
		Country country = repo.findOne(id_country);
		
		if (country == null) throw new RecordNotFoundException(Country_rd_Controller.entity, id_country.toString());
		
		if (country.getIdcountry().longValue() != localCountryId.longValue()) {
			// return unknown region
			regions.add(repo_r.findOne(unknownRegionId));
		} else {
			// return local country region			
			regions = repo_r.findByCountry(country, new Sort("regionname"));
		}
		
		// map list
		List<RegionDTO> regionDTOList = new ArrayList<RegionDTO>();
		for (Region s: regions) {
			regionDTOList.add(dozerMapper.map(s, RegionDTO.class, "region-no-country"));
		}
		
		// assembly dto
		PagedDTO<RegionDTO> dto = new PagedDTO<RegionDTO>();
		dto.setElements(regionDTOList);
		dto.setPageSize(500);
		dto.setCurrentPage(1);
		dto.setTotalPages(1);
		dto.setTotalElements(regionDTOList.size());
		return dto;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws EchoException
	 */
	@Transactional("rdTm")
	@RequestMapping(value="/{id_country}/region/{id_region}/province", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody PagedDTO<ProvinceDTO> getProvinceListByRegion(@PathVariable Long id_country, @PathVariable Long id_region) throws Exception {
		List<Province> provinces = new ArrayList<Province>();

		// find selected country
		Country country = repo.findOne(id_country);
		if (country == null) throw new RecordNotFoundException(Country_rd_Controller.entity, id_country.toString());
		
		// find selected region
		Region region = repo_r.findOne(id_region);
		if (region == null) throw new RecordNotFoundException(Country_rd_Controller.r_entity, id_region.toString());
		if (region.getCountry().getIdcountry().longValue() != country.getIdcountry()) {
			throw new BadRequestException(MessageFormat.format(env.getProperty("echo.api.exception.hibernate.parentmismatch"), Country_rd_Controller.entity, id_country, Country_rd_Controller.r_entity, id_region));
		}
		
		provinces.addAll(repo_p.findByRegion(region, new Sort("provincename")));
		
		// map list
		List<ProvinceDTO> provinceDTOList = new ArrayList<ProvinceDTO>();
		for (Province s: provinces) {
			provinceDTOList.add(dozerMapper.map(s, ProvinceDTO.class, "province-no-region"));
		}
		
		// assembly dto
		PagedDTO<ProvinceDTO> dto = new PagedDTO<ProvinceDTO>();
		dto.setElements(provinceDTOList);
		dto.setPageSize(500);
		dto.setCurrentPage(1);
		dto.setTotalPages(1);
		dto.setTotalElements(provinceDTOList.size());
		return dto;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws EchoException
	 */
	@Transactional("rdTm")
	@RequestMapping(value="/{id_country}/region/{id_region}/province/{id_province}/municipality", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody PagedDTO<MunicipalityDTO> getMunicipalityListByProvince(@PathVariable Long id_country, @PathVariable Long id_region, @PathVariable Long id_province) throws Exception {
		List<Municipality> municipalities = new ArrayList<Municipality>();

		// find selected country
		Country country = repo.findOne(id_country);
		if (country == null) throw new RecordNotFoundException(Country_rd_Controller.entity, id_country.toString());
		
		// find selected region
		Region region = repo_r.findOne(id_region);
		if (region == null) throw new RecordNotFoundException(Country_rd_Controller.r_entity, id_region.toString());
		if (region.getCountry().getIdcountry().longValue() != country.getIdcountry()) {
			throw new BadRequestException(MessageFormat.format(env.getProperty("echo.api.exception.hibernate.parentmismatch"), Country_rd_Controller.entity, id_country, Country_rd_Controller.r_entity, id_region));
		}
		
		// find selected province
		Province province = repo_p.findOne(id_province);
		if (province == null) throw new RecordNotFoundException(Country_rd_Controller.p_entity, id_province.toString());
		if (region.getCountry().getIdcountry().longValue() != country.getIdcountry()) {
			throw new BadRequestException(MessageFormat.format(env.getProperty("echo.api.exception.hibernate.parentmismatch"), Country_rd_Controller.r_entity, id_region, Country_rd_Controller.p_entity, id_province));
		}
		
		municipalities.addAll(repo_m.findByProvince(province, new Sort("municipalityname")));
		
		// map list
		List<MunicipalityDTO> municipalityDTOList = new ArrayList<MunicipalityDTO>();
		for (Municipality s: municipalities) {
			municipalityDTOList.add(dozerMapper.map(s, MunicipalityDTO.class, "municipality-no-province"));
		}
		
		// assembly dto
		PagedDTO<MunicipalityDTO> dto = new PagedDTO<MunicipalityDTO>();
		dto.setElements(municipalityDTOList);
		dto.setPageSize(500);
		dto.setCurrentPage(1);
		dto.setTotalPages(1);
		dto.setTotalElements(municipalityDTOList.size());
		return dto;
	}
	
	
	/**
	 * @param criteria
	 * @param page
	 * @param size
	 * @param sort
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@Transactional("rdTm")
	@RequestMapping(value="", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody PagedDTO<CountryDTO> getByCriteria (	@RequestParam(defaultValue="null", required=false) String criteria, 
																@RequestParam(defaultValue="1", required=false) int page, 
																@RequestParam(defaultValue="500", required=false) int size, 
																@RequestParam(defaultValue="asc", required=false) String sort, 
																@RequestParam(defaultValue="idcountry", required=false) String field) throws Exception {
		// create paged request
		PageRequest request = null;
		
		if (sort.equals("asc")) {
			 request = new PageRequest(page-1, size, Direction.ASC, field);
		} else if (sort.equals("desc")) {
			request = new PageRequest(page-1, size, Direction.DESC, field);
		} else {
			throw new BadRequestException(env.getProperty("echo.api.exception.search.sort.wrongsortparam"));
		}
		
		// create predicate if criteria is not null
		Page<Country> rs = null;
		
		if (!criteria.equals("null")) {
	        SpecificationsBuilder<Country, SpecificationQueryHelper<Country>> builder = new SpecificationsBuilder<Country, SpecificationQueryHelper<Country>>();
	        Pattern pattern = Pattern.compile("(\\w+)(:|<|>)(\\w+)");
	        Matcher matcher = pattern.matcher(criteria + ",");
	        while (matcher.find()) {
	            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
	        }
	        Specification<Country> spec = builder.build();
	        
	        // obtain records
	        rs = repo.findAll(spec, request);
		} else {
			rs = repo.findAll(request);
		}
		
		int totalPages = rs.getTotalPages();
        long totalElements = rs.getTotalElements();
		List<Country> entity = rs.getContent();
		
		if (entity.size() == 0) throw new PageNotFoundException(Country_rd_Controller.entity, page);
		
		// map list
		List<CountryDTO> countryDTOList = new ArrayList<CountryDTO>();
		for (Country s: entity) {
			countryDTOList.add(dozerMapper.map(s, CountryDTO.class));
		}
		
		// assembly dto
		PagedDTO<CountryDTO> dto = new PagedDTO<CountryDTO>();
		dto.setElements(countryDTOList);
		dto.setPageSize(size);
		dto.setCurrentPage(page);
		dto.setTotalPages(totalPages);
		dto.setTotalElements(totalElements);
		return dto;
	}
}
