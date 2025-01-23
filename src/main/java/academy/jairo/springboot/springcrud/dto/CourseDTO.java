package academy.jairo.springboot.springcrud.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
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

    //@NotNull
    //@Length(max = 10)
    //@Pattern(regexp = "Back-end|Front-end")
    private String category;

    private List<LessonDTO> lessons;

}