package academy.jairo.springboot.springcrud.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LessonDTO {

    private Long id;

    @NotNull
    @NotBlank
    @Length(min = 5, max = 100)
    private String name;

    @NotNull
    @NotBlank
    @Length(min = 10, max = 11)
    private String youtubeUrl;

}
