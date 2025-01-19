package academy.jairo.springboot.springcrud.enums.converters;

import java.util.Objects;
import academy.jairo.springboot.springcrud.enums.Category;
import java.util.stream.Stream;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class CategoryConverter implements AttributeConverter<Category, String> {
    
    @Override
    public String convertToDatabaseColumn(Category category) {
        return (Objects.isNull(category)) ? null : category.getValue(); 
    }

    @Override
    public Category convertToEntityAttribute(String value) {
        if (Objects.isNull(value)) {
            return null;
        } 
        return Stream.of(Category.values())
            .filter(c-> c.getValue().equals(value))
            .findFirst()
            .orElseThrow(IllegalArgumentException::new);
    }
    
}