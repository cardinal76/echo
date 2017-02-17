package it.clevercom.echo.tm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.clevercom.echo.tm.model.entity.LanguageType;

public interface TmLanguageTypeRepository extends JpaRepository<LanguageType, Integer> {

}
