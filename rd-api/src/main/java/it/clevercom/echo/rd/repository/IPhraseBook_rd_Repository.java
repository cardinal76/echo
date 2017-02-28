package it.clevercom.echo.rd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import it.clevercom.echo.rd.model.entity.PhraseBook;

public interface IPhraseBook_rd_Repository extends JpaRepository<PhraseBook, Long>, JpaSpecificationExecutor<PhraseBook> {

}