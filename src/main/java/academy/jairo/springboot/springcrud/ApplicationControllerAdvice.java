package academy.jairo.springboot.springcrud;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.stream.Collectors;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import academy.jairo.springboot.springcrud.exception.AppErrorResponse;
import academy.jairo.springboot.springcrud.exception.RecordNotFoundException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {

        String msg = ex.getBindingResult().getFieldErrors().stream()
                .map( error -> error.getField() + " " + error.getDefaultMessage())
                .reduce("", (acc, error) -> acc + error + ", ");

        AppErrorResponse errorResponse = AppErrorResponse.builder()
            .status(HttpStatus.BAD_REQUEST.value())
            .message(msg)
            .timestamp(LocalDateTime.now())
            .build();
    
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex) {

        String msg = ex.getConstraintViolations().stream()
                .map( error -> error.getPropertyPath() + " " + error.getMessage())
                .reduce("", (acc, error) -> acc + error + ", ");

        AppErrorResponse errorResponse = AppErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(msg)
                .timestamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleConstraintViolationException(MethodArgumentTypeMismatchException ex) {

        String msg = "Argument type not valid";
        if (!Objects.isNull(ex) && !Objects.isNull(ex.getRequiredType())) {
             String type =  ex.getRequiredType().getName();
             String [] typeParts = type.split("\\.");
             String typeName = typeParts[typeParts.length-1];
             msg = ex.getName() + " should be of type " + typeName;
        }

        AppErrorResponse errorResponse = AppErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(msg)
                .timestamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }


}