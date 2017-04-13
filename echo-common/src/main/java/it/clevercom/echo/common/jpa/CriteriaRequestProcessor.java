package it.clevercom.echo.common.jpa;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public class CriteriaRequestProcessor<I extends JpaRepository & JpaSpecificationExecutor, E, D> {
	
	List<Specification<E>> specification = new ArrayList<Specification<E>>();
	PageRequest request;
	I repository;
	E entity;
	D dto;
	
	public CriteriaRequestProcessor() {
		
	}
	
	public void test() {
		
	}
}