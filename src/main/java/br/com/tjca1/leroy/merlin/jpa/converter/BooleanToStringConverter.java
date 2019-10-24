package br.com.tjca1.leroy.merlin.jpa.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class BooleanToStringConverter implements AttributeConverter<Boolean, Integer> {

	@Override
	public Integer convertToDatabaseColumn(Boolean value) {
		return value.equals(true) ? 1 : 0;
	}

	@Override
	public Boolean convertToEntityAttribute(Integer value) {
		return value.equals(1) ? true : false;
	}

}
