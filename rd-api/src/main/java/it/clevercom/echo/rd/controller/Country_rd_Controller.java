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

import it.clevercom.echo.common.controller.EchoController;
import it.clevercom.echo.common.exception.model.BadRequestException;
import it.clevercom.echo.common.exception.model.EchoException;
import it.clevercom.echo.common.exception.model.PageNotFoundException;
import it.clevercom.echo.common.exception.model.RecordNotFoundException;
import it.clevercom.echo.common.jpa.CriteriaRequestProcessor;
import it.clevercom.echo.common.jpa.helper.SearchCriteria;
import it.clevercom.echo.common.jpa.helper.SpecificationQueryHelper;
import it.clevercom.echo.common.jpa.helper.SpecificationsBuilder;
import it.clevercom.echo.common.logging.annotation.Loggable;
import it.clevercom.echo.rd.component.Validator;
import it.clevercom.echo.rd.model.dto.CodingActorDTO;
import it.clevercom.echo.rd.model.dto.CountryDTO;
import it.clevercom.echo.rd.model.dto.MunicipalityDTO;
import it.clevercom.echo.rd.model.dto.ProvinceDTO;
import it.clevercom.echo.rd.model.dto.RegionDTO;
import it.clevercom.echo.rd.model.entity.CodingActor;
import it.clevercom.echo.rd.model.entity.Country;
import it.clevercom.echo.rd.model.entity.Municipality;
import it.clevercom.echo.rd.model.entity.Province;
import it.clevercom.echo.rd.model.entity.Region;
import it.clevercom.echo.common.model.dto.response.PagedDTO;
import it.clevercom.echo.rd.repository.ICodingActor_rd_Repository;
import it.clevercom.echo.rd.repository.ICountry_rd_Repository;
import it.clevercom.echo.rd.repository.IMunicipality_rd_Repository;
import it.clevercom.echo.rd.repository.IProvince_rd_Repository;
import it.clevercom.echo.rd.repository.IRegion_rd_Repository;

@Controller
@RestController
@RequestMapping("rd/types/country")
@PropertySource("classpath:rest.platform.properties")
@PropertySource("classpath:rest.rd.properties")

/**
 * Country Controller
 * @author luca
 */

public class Country_rd_Controller extends EchoController {
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
    private DozerBeanMapper rdDozerMapper;
	
	@Autowired
	private Validator validator;
	
	private final Logger logger = Logger.getLogger(this.getClass());
	
	// used to bind it in exception message
	private static String entity_name = "Country";
	private static String entity_id = "idcountry";
	private static String r_entity_name = "Region";
	private static String r_entity_id = "idregion";
	private static String p_entity_name = "Province";
	private static String p_entity_id = "idprovince";
	private static String m_entity_name = "Municipality";
	private static String m_entity_id = "idmunicipality";
	
	/**
	 * Get country by id
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Transactional("rdTm")
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody CountryDTO get(@PathVariable Long id) throws Exception {
		Country entity = repo.findOne(id);
		if (entity == null) throw new RecordNotFoundException(entity_name, entity_id, id.toString());
		return rdDozerMapper.map(entity, CountryDTO.class);
	}
	
	/**
	 * Get region by country 
	 * @param id_country
	 * @return
	 * @throws Exception
	 */
	@Transactional("rdTm")
	@RequestMapping(value="/{id_country}/region", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody PagedDTO<RegionDTO> getRegionListByCountry(@PathVariable Long id_country) throws Exception {
		List<Region> regions = new ArrayList<Region>();
		Country country = repo.findOne(id_country);
		
		if (country == null) throw new RecordNotFoundException(entity_name, entity_id, id_country.toString());
		
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
			regionDTOList.add(rdDozerMapper.map(s, RegionDTO.class, "region-no-country"));
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
	 * Get province by country and region
	 * @param id_country
	 * @param id_region
	 * @return
	 * @throws Exception
	 */
	@Transactional("rdTm")
	@RequestMapping(value="/{id_country}/region/{id_region}/province", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody PagedDTO<ProvinceDTO> getProvinceListByRegion(@PathVariable Long id_country, @PathVariable Long id_region) throws Exception {
		List<Province> provinces = new ArrayList<Province>();

		// find selected country
		Country country = repo.findOne(id_country);
		if (country == null) throw new RecordNotFoundException(entity_name, entity_id, id_country.toString());
		
		// find selected region
		Region region = repo_r.findOne(id_region);
		if (region == null) throw new RecordNotFoundException(r_entity_name, r_entity_id, id_region.toString());
		if (region.getCountry().getIdcountry().longValue() != country.getIdcountry()) {
			throw new BadRequestException(MessageFormat.format(env.getProperty("echo.api.exception.hibernate.parentmismatch"), entity_name, id_country, r_entity_name, id_region));
		}
		
		provinces.addAll(repo_p.findByRegion(region, new Sort("provincename")));
		
		// map list
		List<ProvinceDTO> provinceDTOList = new ArrayList<ProvinceDTO>();
		for (Province s: provinces) {
			provinceDTOList.add(rdDozerMapper.map(s, ProvinceDTO.class, "province-no-region"));
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
	 * Get municipalities by country, region, province
	 * @param id_country
	 * @param id_region
	 * @param id_province
	 * @return
	 * @throws Exception
	 */
	@Transactional("rdTm")
	@RequestMapping(value="/{id_country}/region/{id_region}/province/{id_province}/municipality", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody PagedDTO<MunicipalityDTO> getMunicipalityListByProvince(@PathVariable Long id_country, @PathVariable Long id_region, @PathVariable Long id_province) throws Exception {
		List<Municipality> municipalities = new ArrayList<Municipality>();

		// find selected country
		Country country = repo.findOne(id_country);
		if (country == null) throw new RecordNotFoundException(entity_name, entity_id, id_country.toString());
		
		// find selected region
		Region region = repo_r.findOne(id_region);
		if (region == null) throw new RecordNotFoundException(r_entity_name, r_entity_id, id_region.toString());
		if (region.getCountry().getIdcountry().longValue() != country.getIdcountry()) {
			throw new BadRequestException(MessageFormat.format(env.getProperty("echo.api.exception.hibernate.parentmismatch"), entity_name, id_country, r_entity_name, id_region));
		}
		
		// find selected province
		Province province = repo_p.findOne(id_province);
		if (province == null) throw new RecordNotFoundException(p_entity_name, p_entity_id, id_province.toString());
		if (region.getCountry().getIdcountry().longValue() != country.getIdcountry()) {
			throw new BadRequestException(MessageFormat.format(env.getProperty("echo.api.exception.hibernate.parentmismatch"), r_entity_name, id_region, p_entity_name, id_province));
		}
		
		// find selected municipalities
		List<Municipality> municipality = repo_m.findByProvince(province, new Sort("municipalityname")); 
		if (municipality == null || municipality.size()==0) throw new RecordNotFoundException(m_entity_name, p_entity_id, province.getIdprovince().toString());

		// add to list
		municipalities.addAll(municipality);
		
		// map list
		List<MunicipalityDTO> municipalityDTOList = new ArrayList<MunicipalityDTO>();
		for (Municipality s: municipalities) {
			municipalityDTOList.add(rdDozerMapper.map(s, MunicipalityDTO.class, "municipality-no-province"));
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
	 * Get country by criteria with pagination
	 * @param criteria
	 * @param page
	 * @param size
	 * @param sort
	 * @param field
	 * @return
	 * @throws Exception
	 */
	@Transactional("rdTm")
	@RequestMapping(value="", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody PagedDTO<CountryDTO> getByCriteria (
			@RequestParam(defaultValue="null", required=false) String criteria, 
			@RequestParam(defaultValue="1", required=false) int page, 
			@RequestParam(defaultValue="500", required=false) int size, 
			@RequestParam(defaultValue="asc", required=false) String sort, 
			@RequestParam(defaultValue="idcountry", required=false) String field) throws Exception {
		
		// check enum string params
		validator.validateSort(sort);
		
		CriteriaRequestProcessor<ICountry_rd_Repository, Country, CountryDTO> rp = 
				new CriteriaRequestProcessor<ICountry_rd_Repository, Country, CountryDTO>(repo, 
						rdDozerMapper, 
						CountryDTO.class, 
						entity_name, 
						criteria, 
						sort, 
						field, 
						page, 
						size);
		
		return rp.process();
	}
}
