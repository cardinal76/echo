package it.clevercom.echo.common.jpa;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import it.clevercom.echo.common.exception.model.PageNotFoundException;
import it.clevercom.echo.common.jpa.factory.CriteriaSpecificationFactory;
import it.clevercom.echo.common.jpa.factory.PageRequestFactory;
import it.clevercom.echo.common.model.dto.response.PagedDTO;
import it.clevercom.echo.common.model.factory.PagedDTOFactory;

public class CriteriaRequestProcessor<I extends JpaSpecificationExecutor<E>, E, D> {
	
	private Specification<E> specification;
	private Pageable pageable;
	private I repository;
	private E entity;
	private D dto;
	private Class<D> clazz;
	private String entity_name;
	private int page;
	private int size;
	private DozerBeanMapper mapper;
	
	public CriteriaRequestProcessor(I repo, DozerBeanMapper mapper, Class<D> clazz, String entity_name, String criteria, String sort, String field, int page, int size) {
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
		// create paged request
		pageable = PageRequestFactory.getPageRequest(sort, field, page, size);
	}
	
	public PagedDTO<D> process() throws PageNotFoundException {
		// find with specification and pagination
		Page<E> rs = repository.findAll(specification, pageable);
		
		// get content
		List<E> entity = rs.getContent();
		
		// throw exception if no content
		if (entity.size() == 0) 
			throw new PageNotFoundException(entity_name, page);
		
		// create list
		List<D> dtoList = new ArrayList<D>();
		for (E s: entity) {
			dtoList.add(mapper.map(s, clazz));			
		}
		
		// assembly dto
		return PagedDTOFactory.getPagedDTO(dtoList, size, page, rs.getTotalPages(), rs.getTotalElements());
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

	/**
	 * @return the pageable
	 */
	public Pageable getPageable() {
		return pageable;
	}

	/**
	 * @param pageable the pageable to set
	 */
	public void setPageable(Pageable pageable) {
		this.pageable = pageable;
	}

	/**
	 * @return the repository
	 */
	public I getRepository() {
		return repository;
	}

	/**
	 * @param repository the repository to set
	 */
	public void setRepository(I repository) {
		this.repository = repository;
	}

	/**
	 * @return the entity
	 */
	public E getEntity() {
		return entity;
	}

	/**
	 * @param entity the entity to set
	 */
	public void setEntity(E entity) {
		this.entity = entity;
	}

	/**
	 * @return the dto
	 */
	public D getDto() {
		return dto;
	}

	/**
	 * @param dto the dto to set
	 */
	public void setDto(D dto) {
		this.dto = dto;
	}
}