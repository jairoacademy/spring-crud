package academy.jairo.springboot.springcrud.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    //@Pattern(regexp = "Back-end|Front-end")
    //private Category category;
    private String category;

    private List<LessonDTO> lessons;

}