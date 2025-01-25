package academy.jairo.springboot.springcrud.dto;

import academy.jairo.springboot.springcrud.enums.Category;
import academy.jairo.springboot.springcrud.enums.validation.ValueOfEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseDTO {

    @JsonProperty( "_id")
    private Long id;

    @NotBlank
    @NotNull
    private String name;

    @NotNull
    @Length(max = 10)
    @ValueOfEnum(enumClass = Category.class)
    private String category;

    @NotNull
    @NotEmpty
    @Valid
    private List<LessonDTO> lessons;

}