package it.clevercom.echo.rd.controller;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.clevercom.echo.common.controller.EchoController;
import it.clevercom.echo.common.exception.model.BadRequestException;
import it.clevercom.echo.common.exception.model.RecordNotFoundException;
import it.clevercom.echo.common.jpa.CreateRequestProcessor;
import it.clevercom.echo.common.jpa.CriteriaRequestProcessor;
import it.clevercom.echo.common.jpa.UpdateRequestProcessor;
import it.clevercom.echo.common.logging.annotation.Loggable;
import it.clevercom.echo.common.model.dto.response.CreateResponseDTO;
import it.clevercom.echo.common.model.dto.response.PagedDTO;
import it.clevercom.echo.common.model.dto.response.UpdateResponseDTO;
import it.clevercom.echo.common.model.factory.PagedDTOFactory;
import it.clevercom.echo.rd.component.Validator;
import it.clevercom.echo.rd.model.dto.CountryDTO;
import it.clevercom.echo.rd.model.dto.MunicipalityDTO;
import it.clevercom.echo.rd.model.dto.ProvinceDTO;
import it.clevercom.echo.rd.model.dto.RegionDTO;
import it.clevercom.echo.rd.model.entity.BodyApparatus;
import it.clevercom.echo.rd.model.entity.Country;
import it.clevercom.echo.rd.model.entity.Municipality;
import it.clevercom.echo.rd.model.entity.Province;
import it.clevercom.echo.rd.model.entity.Region;
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
	@Value("${echo.api.setting.localcountryid}")
	public static Long localCountryId;
	
	@Value("${echo.api.setting.unknownregionid}")
	public static Long unknownRegionId;
	
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
	public static final String entity_name = "Country";
	public static final String entity_id = "idcountry";
	public static final String r_entity_name = Region_rd_Controller.entity_name;
	public static final String r_entity_id = Region_rd_Controller.entity_id;
	public static final String p_entity_name = Province_rd_Controller.entity_name;
	public static final String p_entity_id = Province_rd_Controller.entity_id;
	public static final String m_entity_name = Municipality_rd_Controller.entity_name;
	public static final String m_entity_id = Municipality_rd_Controller.entity_id;
	
	/**
	 * Get country by id
	 * @author luca
	 * @category standard get by id REST method
	 * @param id
	 * @return
	 * @since 1.2.0
	 * @throws Exception
	 */
	@Transactional("rdTm")
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody CountryDTO get(@PathVariable Long id) throws Exception {
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.getting"), entity_name, entity_id, id.toString()));
		
		// validate
		validator.validateId(id, entity_name);
		
		// find entity
		Country entity = repo.findOne(id);
		
		// check if entity has been found
		if (entity == null) {
			logger.warn(MessageFormat.format(env.getProperty("echo.api.crud.search.noresult"), entity_name, entity_id, id.toString()));
			throw new RecordNotFoundException(entity_name, entity_id, id.toString());
		}
		
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.returning.response"), entity_name, entity_id, id.toString()));
		return rdDozerMapper.map(entity, CountryDTO.class);
	}
	
	/**
	 * Get country by criteria with pagination
	 * @author luca
	 * @category standard get by criteria REST method
	 * @param criteria
	 * @param page
	 * @param size
	 * @param sort
	 * @param field
	 * @return
	 * @since 1.2.0
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
			@RequestParam(defaultValue=entity_id, required=false) String field) throws Exception {
		
		// log info
		logger.info(env.getProperty("echo.api.crud.logs.validating"));
				
		// validate
		validator.validateSort(sort);
		validator.validateSortField(field, BodyApparatus.class, entity_name);
		
		// create processor
		CriteriaRequestProcessor<ICountry_rd_Repository, Country, CountryDTO> rp = 
				new CriteriaRequestProcessor<ICountry_rd_Repository, Country, CountryDTO>(repo, 
						rdDozerMapper, 
						CountryDTO.class, 
						entity_name, 
						criteria, 
						sort, 
						field, 
						page, 
						size,
						env);
		
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.getting.with.criteria"), entity_name, criteria));
		
		// process data request
		return rp.process();
	}
	
	/**
	 * Get region by country
	 * @author luca
	 * @category get list of $region entities by parent entity $country
	 * @param id_country
	 * @return
	 * @since 1.2.0
	 * @throws Exception
	 */
	@Transactional("rdTm")
	@RequestMapping(value="/{id_country}/region", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody PagedDTO<RegionDTO> getRegionListByCountry(@PathVariable Long id_country) throws Exception {
		// log info
		logger.info(env.getProperty("echo.api.crud.logs.validating"));
		
		// validate
		validator.validateId(id_country, entity_name);
		
		// find entity
		Country country = repo.findOne(id_country);
		
		// check if entity has been found
		if (country == null) {
			logger.warn(MessageFormat.format(env.getProperty("echo.api.crud.search.noresult"), entity_name, entity_id, id_country.toString()));
			throw new RecordNotFoundException(entity_name, entity_id, id_country.toString());
		}
		
		// finding related entities
		List<Region> regions = new ArrayList<Region>();
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
		
		// return data
		return PagedDTOFactory.getPagedDTO(regionDTOList, 500, 1, 1, regionDTOList.size());
	}
	
	/**
	 * Get province by country and region
	 * @author luca
	 * @category get list of $province entities by parent entity $region and $country
	 * @param id_country
	 * @param id_region
	 * @return
	 * @since 1.2.0
	 * @throws Exception
	 */
	@Transactional("rdTm")
	@RequestMapping(value="/{id_country}/region/{id_region}/province", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody PagedDTO<ProvinceDTO> getProvinceListByRegion(@PathVariable Long id_country, @PathVariable Long id_region) throws Exception {
		// log info
		logger.info(env.getProperty("echo.api.crud.logs.validating"));
		
		// validate
		validator.validateId(id_country, entity_name);
		validator.validateId(id_region, r_entity_name);
		
		// find selected country
		Country country = repo.findOne(id_country);
		if (country == null) {
			logger.warn(MessageFormat.format(env.getProperty("echo.api.crud.search.noresult"), entity_name, entity_id, id_country.toString()));
			throw new RecordNotFoundException(entity_name, entity_id, id_country.toString());
		}
		
		// find selected region
		Region region = repo_r.findOne(id_region);
		if (region == null) {
			logger.warn(MessageFormat.format(env.getProperty("echo.api.crud.search.noresult"), r_entity_name, r_entity_id, id_region.toString()));
			throw new RecordNotFoundException(r_entity_name, r_entity_id, id_region.toString());
		}

		// check for mismatch between id_country and id_region 
		if (region.getCountry().getIdcountry().longValue() != country.getIdcountry()) {
			throw new BadRequestException(MessageFormat.format(env.getProperty("echo.api.exception.hibernate.parentmismatch"), entity_name, id_country, r_entity_name, id_region));
		}
		
		// find province
		List<Province> provinceList = repo_p.findByRegion(region, new Sort("provincename"));
		if ((provinceList == null) || (provinceList.size()==0)) {
			throw new RecordNotFoundException(p_entity_name, r_entity_id, id_region.toString());
		}
		
		// map list
		List<ProvinceDTO> provinceDTOList = new ArrayList<ProvinceDTO>();
		for (Province s: provinceList) {
			provinceDTOList.add(rdDozerMapper.map(s, ProvinceDTO.class, "province-no-region"));
		}
		
		// return data
		return PagedDTOFactory.getPagedDTO(provinceDTOList, 500, 1, 1, provinceDTOList.size());
	}
	
	/**
	 * Get municipalities by country, region, province
	 * @author luca
	 * @category get list of $municipality entities by parent entity $province and $region and $country 
	 * @param id_country
	 * @param id_region
	 * @param id_province
	 * @return
	 * @since 1.2.0
	 * @throws Exception
	 */
	@Transactional("rdTm")
	@RequestMapping(value="/{id_country}/region/{id_region}/province/{id_province}/municipality", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody PagedDTO<MunicipalityDTO> getMunicipalityListByProvince(@PathVariable Long id_country, @PathVariable Long id_region, @PathVariable Long id_province) throws Exception {
		// log info
		logger.info(env.getProperty("echo.api.crud.logs.validating"));
			
		// validate
		validator.validateId(id_country, entity_name);
		validator.validateId(id_region, r_entity_name);
		validator.validateId(id_province, p_entity_name);

		// find selected country
		Country country = repo.findOne(id_country);
		if (country == null) { 
			logger.warn(MessageFormat.format(env.getProperty("echo.api.crud.search.noresult"), entity_name, entity_id, id_country.toString()));
			throw new RecordNotFoundException(entity_name, entity_id, id_country.toString());
		}
		
		// find selected region
		Region region = repo_r.findOne(id_region);
		if (region == null) {
			logger.warn(MessageFormat.format(env.getProperty("echo.api.crud.search.noresult"), r_entity_name, r_entity_id, id_region.toString()));
			throw new RecordNotFoundException(r_entity_name, r_entity_id, id_region.toString());
		}
		
		// check for mismatch between id_country and id_region 
		if (region.getCountry().getIdcountry().longValue() != country.getIdcountry()) {
			throw new BadRequestException(MessageFormat.format(env.getProperty("echo.api.exception.hibernate.parentmismatch"), entity_name, id_country, r_entity_name, id_region));
		}
		
		// find selected province
		Province province = repo_p.findOne(id_province);
		if (province == null) {
			logger.warn(MessageFormat.format(env.getProperty("echo.api.crud.search.noresult"), p_entity_name, p_entity_id, id_province.toString()));
			throw new RecordNotFoundException(p_entity_name, p_entity_id, id_province.toString());
		}
		
		// check for mismatch between id_region and id_province 
		if (region.getCountry().getIdcountry().longValue() != country.getIdcountry()) {
			throw new BadRequestException(MessageFormat.format(env.getProperty("echo.api.exception.hibernate.parentmismatch"), r_entity_name, id_region, p_entity_name, id_province));
		}
		
		// find selected municipalities
		List<Municipality> municipality = repo_m.findByProvince(province, new Sort("municipalityname")); 
		if (municipality == null || municipality.size()==0) { 
			throw new RecordNotFoundException(m_entity_name, p_entity_id, id_province.toString());
		}
		
		// map list
		List<MunicipalityDTO> municipalityDTOList = new ArrayList<MunicipalityDTO>();
		for (Municipality s: municipality) {
			municipalityDTOList.add(rdDozerMapper.map(s, MunicipalityDTO.class, "municipality-no-province"));
		}
		
		// return data
		return PagedDTOFactory.getPagedDTO(municipalityDTOList, 1000, 1, 1, municipalityDTOList.size());
	}
	
	/**
	 * Add country
	 * @author luca
	 * @category standard create REST method
	 * @param country
	 * @param request
	 * @return
	 * @since 1.2.0
	 * @throws Exception
	 */
	@Transactional("rdTm")
	@RequestMapping(method = RequestMethod.POST)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody CreateResponseDTO<CountryDTO> add(@RequestBody CountryDTO country, HttpServletRequest request) throws Exception {
		// log info
		logger.info(env.getProperty("echo.api.crud.logs.validating"));
		
		// validate
		validator.validateDTONullIdd(country, entity_id);
		
		// create processor
		CreateRequestProcessor<ICountry_rd_Repository, Country, CountryDTO> rp = 
				new CreateRequestProcessor<ICountry_rd_Repository, Country, CountryDTO>(repo, 
						rdDozerMapper, 
						Country.class, 
						entity_name, 
						getLoggedUser(request), 
						country,
						env);
		
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.adding"), entity_name, entity_id, country.getIdd().toString()));
		
		// process
		return rp.process();
	}
	
	/**
	 * Update country
	 * @author luca
	 * @category standard update REST method
	 * @param country
	 * @param request
	 * @return
	 * @since 1.2.0
	 * @throws Exception
	 */
	@Transactional("rdTm")
	@RequestMapping(method = RequestMethod.PUT)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody UpdateResponseDTO<CountryDTO> update(@RequestBody CountryDTO country, HttpServletRequest request) throws Exception {
		// log info
		logger.info(env.getProperty("echo.api.crud.logs.validating"));
		
		// validate
		validator.validateDTOIdd(country, entity_name);

		// create processor
		UpdateRequestProcessor<ICountry_rd_Repository, Country, CountryDTO> rp = 
				new UpdateRequestProcessor<ICountry_rd_Repository, Country, CountryDTO>(repo, 
						rdDozerMapper,
						entity_name,
						entity_id,
						getLoggedUser(request), 
						country, 
						env);
		
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.updating"), entity_name, entity_id, country.getIdd().toString()));

		// return response
		return rp.process();
	}
	
	/**
	 * Delete country
	 * @author luca
	 * @category standard delete REST method
	 * @param country
	 * @param request
	 * @return
	 * @throws  
	 */
	@Transactional("rdTm")
	@RequestMapping(method = RequestMethod.DELETE)
	@PreAuthorize("hasAnyRole('ROLE_RD_REFERRING_PHYSICIAN', 'ROLE_RD_SCHEDULER', 'ROLE_RD_PERFORMING_TECHNICIAN', 'ROLE_RD_RADIOLOGIST', 'ROLE_RD_SUPERADMIN')")
	@Loggable
	public @ResponseBody UpdateResponseDTO<CountryDTO> delete(@RequestBody CountryDTO country, HttpServletRequest request) throws Exception {
		// log info
		logger.info(env.getProperty("echo.api.crud.logs.validating"));
				
		// validate
		validator.validateDTOIdd(country, entity_name);

		// create processor
		UpdateRequestProcessor<ICountry_rd_Repository, Country, CountryDTO> rp = 
				new UpdateRequestProcessor<ICountry_rd_Repository, Country, CountryDTO>(repo, 
						rdDozerMapper,
						entity_name,
						entity_id,
						getLoggedUser(request), 
						country, 
						env);
		
		// log info
		logger.info(MessageFormat.format(env.getProperty("echo.api.crud.logs.updating"), entity_name, entity_id, country.getIdd().toString()));

		// return response
		return rp.enable(false);
	}
}
