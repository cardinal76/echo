package it.clevercom.echo.tm.model.entity.gateway;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @author gfares
 * @since 0.1
 * 
 *        Jpa does not support {@link java.time.LocalDateTime} so let's create a
 *        converter
 */
@Converter(autoApply = true)
public class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, Timestamp> {

	@Override
	public Timestamp convertToDatabaseColumn(LocalDateTime arg0) {
		return (arg0 == null ? null : Timestamp.valueOf(arg0));
	}

	@Override
	public LocalDateTime convertToEntityAttribute(Timestamp arg0) {
		return (arg0 == null ? null : arg0.toLocalDateTime());
	}

}
