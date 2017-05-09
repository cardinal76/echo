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
import it.clevercom.echo.rd.model.dto.PatientSmartDTO;
import it.clevercom.echo.rd.model.dto.WorkReportDTO;
import it.clevercom.echo.rd.model.dto.WorkSessionDTO;
import it.clevercom.echo.rd.model.dto.WorkTaskDTO;
import it.clevercom.echo.rd.model.entity.Patient;
import it.clevercom.echo.rd.model.entity.WorkPriority;
import it.clevercom.echo.rd.model.entity.WorkReport;
import it.clevercom.echo.rd.model.entity.WorkSession;
import it.clevercom.echo.rd.model.entity.WorkStatus;
import it.clevercom.echo.rd.model.entity.WorkTask;

public class WorkSession2WorkSessionDTO implements CustomConverter, MapperAware {
	@Autowired
	private Mapper rdDozerMapper;

	@Override
	public Object convert(Object destinationFieldValue, Object sourceFieldValue, Class<?> destinationClass,	Class<?> sourceClass) {
		if (sourceFieldValue == null) {
			return null;
		}
		
		if (sourceFieldValue instanceof WorkSession) {
			WorkSessionDTO target = null; 
			WorkSession source = (WorkSession) sourceFieldValue;
			
			// check to see if the object already exists
			if (destinationFieldValue == null) {
				target = new WorkSessionDTO();
			} else {
				target = (WorkSessionDTO) destinationFieldValue;
			}
			
			target.setIdWorkSession(source.getIdworksession());
			target.setPatient(rdDozerMapper.map(source.getPatient(), PatientSmartDTO.class));
			target.setWorkStatus(rdDozerMapper.map(source.getWorkStatus(), BaseObjectDTO.class));
			
			if ((source.getWorkReports() != null) && (source.getWorkReports().size() > 0)) {
				Set<WorkReportDTO> reports = new HashSet<WorkReportDTO>();
				for (WorkReport element : source.getWorkReports()) {
					reports.add(rdDozerMapper.map(element, WorkReportDTO.class));
				}
				target.setWorkReports(reports);
			}
			
			if ((source.getWorkTasks() != null) && (source.getWorkTasks().size() > 0)) {
				Set<WorkTaskDTO> tasks = new HashSet<WorkTaskDTO>();
				for (WorkTask element : source.getWorkTasks()) {
					tasks.add(rdDozerMapper.map(element, WorkTaskDTO.class));
				}
				target.setWorkTasks(tasks);
			}
			
			target.setWorkPriority(rdDozerMapper.map(source.getWorkPriority(), BaseObjectDTO.class));
			target.setScheduledDate(source.getScheduleddate()!=null ? source.getScheduleddate().getTime() : null);
			target.setReportedDate(source.getReporteddate()!=null ? source.getReporteddate().getTime() : null);
			
			target.setActive(source.getActive());
			target.setCreated(source.getCreated());
			target.setUpdated(source.getUpdated());
			target.setUserupdate(source.getUserupdate());
			
			return target;
		} else if (sourceFieldValue instanceof WorkSessionDTO) {
			WorkSession target = null; 
			WorkSessionDTO source = (WorkSessionDTO) sourceFieldValue;
			
			// check to see if the object already exists
			if (destinationFieldValue == null) {
				target = new WorkSession();
			} else {
				target = (WorkSession) destinationFieldValue;
			}
			
			target.setIdworksession((source.getIdWorkSession()!=null) ? source.getIdWorkSession() : null);
			target.setPatient(rdDozerMapper.map(source.getPatient(), Patient.class));
			target.setWorkStatus(rdDozerMapper.map(source.getWorkStatus(), WorkStatus.class));
			
			if ((source.getWorkReports() != null) && (source.getWorkReports().size() > 0)) {
				Set<WorkReport> reports = new HashSet<WorkReport>();
				for (WorkReportDTO element : source.getWorkReports()) {
					reports.add(rdDozerMapper.map(element, WorkReport.class));
				}
				target.setWorkReports(reports);
			}
			
			if ((source.getWorkTasks() != null) && (source.getWorkTasks().size() > 0)) {
				Set<WorkTask> tasks = new HashSet<WorkTask>();
				for (WorkTaskDTO element : source.getWorkTasks()) {
					tasks.add(rdDozerMapper.map(element, WorkTask.class));
				}
				target.setWorkTasks(tasks);
			}
			
			target.setWorkPriority(rdDozerMapper.map(source.getWorkPriority(), WorkPriority.class));
			target.setScheduleddate(source.getScheduledDate()!=null ? new Date(source.getScheduledDate()) : null);
			target.setReporteddate(source.getReportedDate()!=null ? new Date(source.getReportedDate()) : null);
			
			target.setActive((source.getActive()!=null) ? source.getActive() : null);
			target.setCreated((source.getCreated()!=null) ? source.getCreated() : null);
			target.setUpdated((source.getUpdated()!=null) ? source.getUpdated() : null);
			target.setUserupdate((source.getUserupdate()!=null) ? source.getUserupdate() : null);
			
			return target;
		} else {
			throw new MappingException("Converter WorkSession2WorkSessionDTO " + "used incorrectly. Arguments passed in were:" + destinationFieldValue + " and " + sourceFieldValue);
		}
	}
	
	@Override
	public void setMapper(Mapper mapper) {
		// TODO Auto-generated method stub
		rdDozerMapper = mapper;
	}
}
