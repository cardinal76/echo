package it.clevercom.echo.rd.model.converter;

import java.sql.Date;

import org.dozer.CustomConverter;
import org.dozer.Mapper;
import org.dozer.MapperAware;
import org.dozer.MappingException;
import org.springframework.beans.factory.annotation.Autowired;

import it.clevercom.echo.rd.model.dto.BaseObjectDTO;
import it.clevercom.echo.rd.model.dto.ModalityDTO;
import it.clevercom.echo.rd.model.dto.UserDTO;
import it.clevercom.echo.rd.model.dto.WorkTaskDTO;
import it.clevercom.echo.rd.model.entity.Modality;
import it.clevercom.echo.rd.model.entity.Service;
import it.clevercom.echo.rd.model.entity.User;
import it.clevercom.echo.rd.model.entity.WorkPriority;
import it.clevercom.echo.rd.model.entity.WorkStatus;
import it.clevercom.echo.rd.model.entity.WorkTask;

public class WorkTask2WorkTaskDTO implements CustomConverter, MapperAware {
	@Autowired
	private Mapper rdDozerMapper;

	@Override
	public Object convert(Object destinationFieldValue, Object sourceFieldValue, Class<?> destinationClass,	Class<?> sourceClass) {
		if (sourceFieldValue == null) {
			return null;
		}
		
		if (sourceFieldValue instanceof WorkTask) {
			WorkTaskDTO target = null; 
			WorkTask source = (WorkTask) sourceFieldValue;
			
			// check to see if the object already exists
			if (destinationFieldValue == null) {
				target = new WorkTaskDTO();
			} else {
				target = (WorkTaskDTO) destinationFieldValue;
			}
			
			target.setAccessionNumber(source.getAccessionnumber()!=null ? source.getAccessionnumber() : null);
			target.setExecutedDate(source.getExecuteddate()!=null ? source.getExecuteddate().getTime() : null);
			target.setExecutedNote(source.getExecutednote()!=null ? source.getExecutednote() : null);
			target.setExecutingDate(source.getExecutingdate()!=null ? source.getExecutingdate().getTime() : null);
			target.setExecutingNote(source.getExecutingnote()!=null ? source.getExecutingnote() : null);
			target.setIdWorkTask(source.getIdworktask());
			target.setModality(rdDozerMapper.map(source.getModality(), ModalityDTO.class));
			target.setReportedDate(source.getReporteddate() != null ? source.getReporteddate().getTime() : null);
			target.setReportedNote(source.getReportednote() != null ? source.getReportednote() : null);
			target.setReportingDate(source.getReportingdate() != null ? source.getReportingdate().getTime() : null);
			target.setReportingNote(source.getReportingnote() != null ? source.getReportingnote() : null);
			target.setScheduledDate(source.getScheduleddate() != null ? source.getScheduleddate().getTime() : null);
			target.setService(rdDozerMapper.map(source.getService(),BaseObjectDTO.class));
			target.setStudyId(source.getStudyid() != null ? source.getStudyid() : null);
			target.setStudyUuid(source.getStudyuuid() != null ? source.getStudyuuid() : null);
			target.setUser(rdDozerMapper.map(source.getUser(),UserDTO.class));
			target.setWorkPriority(rdDozerMapper.map(source.getWorkPriority(), BaseObjectDTO.class));
			target.setWorkStatus(rdDozerMapper.map(source.getWorkStatus(), BaseObjectDTO.class));
			
			// inject tech fields
			target.setActive((source.getActive()!=null) ? source.getActive() : target.getActive());
			target.setCreated((source.getCreated()!=null) ? source.getCreated() : target.getCreated());
			target.setUpdated((source.getUpdated()!=null) ? source.getUpdated() : target.getUpdated());
			target.setUserUpdate((source.getUserupdate()!=null) ? source.getUserupdate() : target.getUserUpdate());
			
			return target;
		} else if (sourceFieldValue instanceof WorkTaskDTO) {
			WorkTask target = null; 
			WorkTaskDTO source = (WorkTaskDTO) sourceFieldValue;
			
			// check to see if the object already exists
			if (destinationFieldValue == null) {
				target = new WorkTask();
			} else {
				target = (WorkTask) destinationFieldValue;
			}
			
			target.setAccessionnumber(source.getAccessionNumber() != null ? source.getAccessionNumber() : null);
			target.setExecuteddate(source.getExecutedDate() != null ? new Date(source.getExecutedDate()) : null);
			target.setExecutednote(source.getExecutedNote() != null ? source.getExecutedNote() : null);
			target.setExecutingdate(source.getExecutingDate() != null ? new Date(source.getExecutingDate()) : null);
			target.setExecutingnote(source.getExecutingNote() != null ? source.getExecutingNote() : null);
			target.setIdworktask(source.getIdWorkTask());
			
			target.setModality(rdDozerMapper.map(source.getModality(),Modality.class));
			target.setReporteddate(source.getReportedDate() != null ? new Date(source.getReportedDate()) : null);
			target.setReportednote(source.getReportedNote() != null ? source.getReportedNote() : null);
			target.setReportingdate(source.getReportingDate() != null ? new Date(source.getReportingDate()) : null);
			target.setReportingnote(source.getReportingNote() != null ? source.getReportingNote() : null);
			target.setScheduleddate(source.getScheduledDate() != null ? new Date(source.getScheduledDate()) : null);
			target.setService(rdDozerMapper.map(source.getService(),Service.class));
			
			target.setStudyid(source.getStudyId() != null ? source.getStudyId() : null);
			target.setStudyuuid(source.getStudyUuid() != null ? source.getStudyUuid() : null);
			target.setUser((source.getUser()!=null) ? rdDozerMapper.map(source.getUser(), User.class) : null);
			target.setWorkPriority(rdDozerMapper.map(source.getWorkPriority(), WorkPriority.class));
			target.setWorkStatus(rdDozerMapper.map(source.getWorkStatus(), WorkStatus.class));
				
			// inject tech fields
			target.setActive((source.getActive()!=null) ? source.getActive() : target.getActive());
			target.setCreated((source.getCreated()!=null) ? source.getCreated() : target.getCreated());
			target.setUpdated((source.getUpdated()!=null) ? source.getUpdated() : target.getUpdated());
			target.setUserupdate((source.getUserUpdate()!=null) ? source.getUserUpdate() : target.getUserupdate());
			
			return target;
		} else {
			throw new MappingException("Converter WorkTask2WorkTaskDTO " + "used incorrectly. Arguments passed in were:" + destinationFieldValue + " and " + sourceFieldValue);
		}
	}
	
	@Override
	public void setMapper(Mapper mapper) {
		// TODO Auto-generated method stub
		rdDozerMapper = mapper;
	}
}
