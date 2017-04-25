package it.clevercom.echo.tm.model.entity.gateway;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @author gfares
 * @since 0.1
 * 
 *        Jpa does not support {@link java.time.LocalDate} so let's create a
 *        converter
 */
@Converter(autoApply = true)
public class LocalDateConverter implements AttributeConverter<LocalDate, Date> {

	@Override
	public Date convertToDatabaseColumn(LocalDate arg0) {
		return (arg0 == null ? null : Date.valueOf(arg0));
	}

	@Override
	public LocalDate convertToEntityAttribute(Date arg0) {
		return (arg0 == null ? null : arg0.toLocalDate());
	}

}
