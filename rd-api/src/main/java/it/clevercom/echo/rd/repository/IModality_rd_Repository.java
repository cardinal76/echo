package it.clevercom.echo.rd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import it.clevercom.echo.rd.model.entity.Modality;
import it.clevercom.echo.rd.model.entity.ModalityType;

public interface IModality_rd_Repository extends JpaRepository<Modality, Long>, JpaSpecificationExecutor<Modality> {
	public List<Modality> findByModalityType(ModalityType type);
}