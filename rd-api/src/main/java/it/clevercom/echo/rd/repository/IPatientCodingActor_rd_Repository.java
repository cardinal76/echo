package it.clevercom.echo.rd.repository;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import it.clevercom.echo.rd.model.entity.PatientCodingActor;

public interface IPatientCodingActor_rd_Repository extends JpaRepository<PatientCodingActor, Long>, JpaSpecificationExecutor<PatientCodingActor> {
	public List<PatientCodingActor> findByExternalcode (String externalcode, Pageable request);
	public Long countByExternalcode(String externalcode);

}