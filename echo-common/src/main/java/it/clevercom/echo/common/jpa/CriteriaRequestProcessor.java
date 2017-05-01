package it.clevercom.echo.common.jpa;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import it.clevercom.echo.common.exception.model.PageNotFoundException;
import it.clevercom.echo.common.jpa.factory.CriteriaSpecificationFactory;
import it.clevercom.echo.common.jpa.factory.PageRequestFactory;
import it.clevercom.echo.common.model.dto.response.PagedDTO;
import it.clevercom.echo.common.model.factory.ResponseFactory;

public class CriteriaRequestProcessor<I extends JpaSpecificationExecutor<E>, E, D> {
	
	private Specification<E> specification;
	private Pageable pageable;
	private I repository;
	private Class<D> clazz;
	private String entity_name;
	private int page;
	private int size;
	private DozerBeanMapper mapper;
	private Environment env;
	private final Logger logger = Logger.getLogger(this.getClass());
	
	/**
	 * @param repo
	 * @param mapper
	 * @param clazz
	 * @param entity_name
	 * @param criteria
	 * @param sort
	 * @param field
	 * @param page
	 * @param size
	 */
	public CriteriaRequestProcessor(
			// repository that performs operation
			I repo, 
			// mapper that performs conversion
			DozerBeanMapper mapper, 
			// dto class
			Class<D> clazz, 
			// entity friendly name
			String entity_name, 
			// criteria used in params 
			String criteria, 
			// sort type 
			String sort, 
			// sort field param 
			String field, 
			// page param
			int page, 
			// size param
			int size,
			Environment env) {
		super();
		// class
		this.clazz = clazz;
		// mapper
		this.mapper = mapper;
		// set repository
		repository = repo;
		// set entity name
		this.entity_name = entity_name;
		// create and set specification with criteria param
		specification = CriteriaSpecificationFactory.getCriteriaSpecification(criteria);
		this.size = size;
		this.page = page;
		// set env
		this.env = env;
		// create paged request
		pageable = PageRequestFactory.getPageRequest(sort, field, page, size);
		
	}
	
	/**
	 * @return
	 * @throws PageNotFoundException
	 */
	public PagedDTO<D> process() throws PageNotFoundException {
		// find with specification and pagination
		Page<E> rs = repository.findAll(specification, pageable);
		
		// get content
		List<E> entity = rs.getContent();
		
		// throw exception if no content
		if (entity.size() == 0) {
			logger.warn(MessageFormat.format(env.getProperty("echo.api.crud.search.nopage"), entity_name, page));
			throw new PageNotFoundException(entity_name, page);
		}
		
		// create list
		List<D> dtoList = new ArrayList<D>();
		for (E s: entity) {
			dtoList.add(mapper.map(s, clazz));			
		}
		
		// assembly dto
		return ResponseFactory.getPagedDTO(dtoList, size, page, rs.getTotalPages(), rs.getTotalElements());
	}

	/**
	 * @param specification
	 */
	public void addAndSpecification(Specification<E> specificationToAdd) {
		specification = Specifications.where(specification).and(specificationToAdd);
	}
	
	/**
	 * @param specification
	 */
	public void addOrSpecification(Specification<E> specificationToAdd) {
		specification = Specifications.where(specification).or(specificationToAdd);
	}
	
	/**
	 * @return the specification
	 */
	public Specification<?> getSpecification() {
		return specification;
	}

	/**
	 * @param specification the specification to set
	 */
	public void setSpecification(Specification<E> specification) {
		this.specification = specification;
	}
}