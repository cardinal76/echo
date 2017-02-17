package it.clevercom.echo.tm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.clevercom.echo.tm.model.entity.ClinicFolder;

public interface TmClinicFolderRepository extends JpaRepository<ClinicFolder, Integer> {

}
