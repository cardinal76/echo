package it.clevercom.echo.rd.model.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import it.clevercom.echo.common.dto.AbstractEchoDTO;

@JsonIgnoreProperties({"created","updated","userupdate","active","idd"})
public class WorkSessionDTO extends AbstractEchoDTO implements Serializable {

	@Override
	public Object getIdd() {
		// TODO Auto-generated method stub
		return null;
	}

}
