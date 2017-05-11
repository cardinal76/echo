package it.clevercom.echo.rd.model.dto;

import java.io.Serializable;
import java.util.List;

import it.clevercom.echo.common.dto.AbstractEchoDTO;
import it.clevercom.echo.rd.model.entity.ModalityDailyAllocation;

public class ModalityTypeIntervalAllocationDTO extends AbstractEchoDTO implements Serializable {
	
	private static final long serialVersionUID = 5880101803484534823L;
	
	private Long from;
	private Long to;
	private BaseObjectDTO modalityType;
	private Integer serviceallocation;
    private Integer serviceexcess;
    private Integer patientallocation;
    private Integer patientexcess;
	
	private List<ModalityDailyAllocation> allocations;
	
	@Override
	public Object getIdd() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
