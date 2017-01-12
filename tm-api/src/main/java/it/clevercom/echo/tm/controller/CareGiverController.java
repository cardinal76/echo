package it.clevercom.echo.tm.controller;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.clevercom.echo.common.exception.model.BadRequestException;
import it.clevercom.echo.common.exception.model.RecordNotFoundException;
import it.clevercom.echo.tm.model.dto.CareGiverDTO;
import it.clevercom.echo.tm.model.dto.response.CreateResponseDTO;
import it.clevercom.echo.tm.model.dto.response.DeleteResponseDTO;
import it.clevercom.echo.tm.model.dto.response.UpdateResponseDTO;
import it.clevercom.echo.tm.model.entity.Caregiver;
import it.clevercom.echo.tm.repository.CareGiverRepository;

@Controller
@RestController
@RequestMapping("protected")
public class CareGiverController {
	
	@Autowired
	private CareGiverRepository repo;
	
	@Autowired
	private ApplicationContext appContext;
	
	@Autowired
    private DozerBeanMapper dozerMapper;
	
	@RequestMapping(value = "careGiver", method = RequestMethod.GET)
	@PreAuthorize("hasRole('USER')")
	public @ResponseBody CareGiverDTO get(@RequestParam Integer id) throws RecordNotFoundException {
		Caregiver entity = repo.findOne(id);	
		if (entity == null) throw new RecordNotFoundException("Impossible to find caregiver with given id=" + id);
		CareGiverDTO dto = dozerMapper.map(entity, CareGiverDTO.class);
		return dto;
	}
	
	@RequestMapping(value = "careGiver", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ADMIN')")
	public @ResponseBody CreateResponseDTO add(@RequestBody CareGiverDTO careGiver) throws BadRequestException {
		if (careGiver == null) throw new BadRequestException("Impossible to store a null caregiver");
		Caregiver entity = dozerMapper.map(careGiver, Caregiver.class);
		repo.saveAndFlush(entity);
		return new CreateResponseDTO();
	}
	
	@RequestMapping(value = "careGiver", method = RequestMethod.PUT)
	@PreAuthorize("hasRole('ADMIN')")
	public @ResponseBody UpdateResponseDTO update(@RequestBody CareGiverDTO careGiver) {
		
		return new UpdateResponseDTO();
	}
	
	@RequestMapping(value = "careGiver", method = RequestMethod.DELETE)
	@PreAuthorize("hasRole('ADMIN')")
	public @ResponseBody DeleteResponseDTO delete() {
		
		return new DeleteResponseDTO();
	}
}
