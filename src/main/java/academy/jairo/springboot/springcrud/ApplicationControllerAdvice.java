package academy.jairo.springboot.springcrud;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import academy.jairo.springboot.springcrud.exception.AppErrorResponse;
import academy.jairo.springboot.springcrud.exception.RecordNotFoundException;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<Object> handleRecordNotFoundException(RecordNotFoundException ex) {
    
        AppErrorResponse errorResponse = AppErrorResponse.builder()
            .status(HttpStatus.NOT_FOUND.value())
            .message(ex.getMessage())
            .timestamp(LocalDateTime.now())
            .build();
    
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

}