package academy.jairo.springboot.springcrud.exception;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AppErrorResponse {

    private int status;
    private String message;
    private LocalDateTime timestamp;

}
