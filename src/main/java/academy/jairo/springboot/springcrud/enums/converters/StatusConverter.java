package academy.jairo.springboot.springcrud.enums.converters;

import java.util.Objects;
import academy.jairo.springboot.springcrud.enums.Status;

import java.util.stream.Stream;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<Status, String> {
    
    @Override
    public String convertToDatabaseColumn(Status value) {
        return (Objects.isNull(value)) ? null : value.getValue(); 
    }

    @Override
    public Status convertToEntityAttribute(String value) {
        if (Objects.isNull(value)) {
            return null;
        } 
        return Stream.of(Status.values())
            .filter(c-> c.getValue().equals(value))
            .findFirst()
            .orElseThrow(IllegalArgumentException::new);
    }
    
}