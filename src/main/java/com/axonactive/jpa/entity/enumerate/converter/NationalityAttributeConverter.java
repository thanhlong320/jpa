package com.axonactive.jpa.entity.enumerate.converter;

import com.axonactive.jpa.entity.enumerate.Nationality;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;
import java.util.Objects;

@Converter(autoApply = true)
public class NationalityAttributeConverter implements AttributeConverter<Nationality, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Nationality nationality) {
        return Objects.nonNull(nationality) ? nationality.getValue() : null;
    }

    @Override
    public Nationality convertToEntityAttribute(Integer integer) {
        return Arrays.stream(Nationality.values()).filter(nationality -> Objects.equals(nationality.getValue(), integer)).findFirst().get();
    }
}
