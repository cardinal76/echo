package it.clevercom.echo.rd.model.converter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.dozer.CustomConverter;
import org.dozer.Mapper;
import org.dozer.MapperAware;
import org.dozer.MappingException;
import org.springframework.beans.factory.annotation.Autowired;

import it.clevercom.echo.rd.model.dto.BaseObjectDTO;
import it.clevercom.echo.rd.model.dto.UserDTO;
import it.clevercom.echo.rd.model.dto.WorkReportDTO;
import it.clevercom.echo.rd.model.dto.WorkTaskDTO;
import it.clevercom.echo.rd.model.entity.User;
import it.clevercom.echo.rd.model.entity.WorkReport;
import it.clevercom.echo.rd.model.entity.WorkReportUser;
import it.clevercom.echo.rd.model.entity.WorkStatus;
import it.clevercom.echo.rd.model.entity.WorkTask;

public class WorkReport2WorkReportDTO implements CustomConverter, MapperAware {
	@Autowired
	private Mapper rdDozerMapper;

	@Override
	public Object convert(Object destinationFieldValue, Object sourceFieldValue, Class<?> destinationClass,	Class<?> sourceClass) {
		if (sourceFieldValue == null) {
			return null;
		}
		
		if (sourceFieldValue instanceof WorkReport) {
			WorkReportDTO target = null; 
			WorkReport source = (WorkReport) sourceFieldValue;
			
			// check to see if the object already exists
			if (destinationFieldValue == null) {
				target = new WorkReportDTO();
			} else {
				target = (WorkReportDTO) destinationFieldValue;
			}
			
			target.setIdWorkReport(source.getIdworkreport());
			target.setAccessionNumber(source.getAccessionnumber());			
			target.setWorkStatus(rdDozerMapper.map(source.getWorkStatus(), BaseObjectDTO.class));
			target.setWorkTask(rdDozerMapper.map(source.getWorkStatus(), WorkTaskDTO.class));
			target.setCreationDate((source.getCreationdate() != null) ? Long.valueOf(source.getCreationdate().getTime()) : null);
			target.setCompletionDate((source.getCompletiondate() != null) ? Long.valueOf(source.getCompletiondate().getTime()) : null);
			target.setBody(source.getBody());
			if ((source.getWorkReportUsers() != null) && (source.getWorkReportUsers().size() > 0)) {
				Set<UserDTO> users = new HashSet<UserDTO>();
				for (WorkReportUser element : source.getWorkReportUsers()) {
					users.add(rdDozerMapper.map(element.getUser(), UserDTO.class));
				}
				target.setWorkReportUsers(users);
			}
			
			target.setActive(source.getActive());
			target.setCreated(source.getCreated());
			target.setUpdated(source.getUpdated());
			target.setUserupdate(source.getUserupdate());
			
			return target;
		} else if (sourceFieldValue instanceof WorkReportDTO) {
			WorkReport target = null; 
			WorkReportDTO source = (WorkReportDTO) sourceFieldValue;
			
			// check to see if the object already exists
			if (destinationFieldValue == null) {
				target = new WorkReport();
			} else {
				target = (WorkReport) destinationFieldValue;
			}
			
			target.setIdworkreport(source.getIdWorkReport());
			
			target.setWorkStatus(rdDozerMapper.map(source.getWorkStatus(), WorkStatus.class));
			target.setWorkTask(rdDozerMapper.map(source.getWorkTask(), WorkTask.class));
			target.setAccessionnumber(source.getAccessionNumber());
			target.setCreationdate((source.getCreationDate()!=0) ? new Date(source.getCreationDate()) : null);
			target.setCompletiondate((source.getCompletionDate()!=0) ? new Date(source.getCompletionDate()) : null);
			target.setBody(source.getBody());
			if ((source.getWorkReportUsers() != null) && (source.getWorkReportUsers().size() > 0)) {
				Set<WorkReportUser> workReportUsers = new HashSet<WorkReportUser>();
				for (UserDTO element : source.getWorkReportUsers()) {
					workReportUsers.add(new WorkReportUser(rdDozerMapper.map(element, User.class), null));
				}
				target.setWorkReportUsers(workReportUsers);
			}
			
			target.setActive(source.getActive());
			target.setCreated(source.getCreated());
			target.setUpdated(source.getUpdated());
			target.setUserupdate(source.getUserupdate());
			
			return target;
		} else {
			throw new MappingException("Converter WorkReport2WorkReportDTO " + "used incorrectly. Arguments passed in were:" + destinationFieldValue + " and " + sourceFieldValue);
		}
	}
	
	@Override
	public void setMapper(Mapper mapper) {
		// TODO Auto-generated method stub
		rdDozerMapper = mapper;
	}
}
