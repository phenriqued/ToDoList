package phenriqued.ToDoList.infra.ErrorsHandlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import phenriqued.ToDoList.infra.Exceptions.ToDoException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ErrorHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ToDoException.class)
    public String toDoExceptionHandler(ToDoException exception){
        return "To Do [ERROR]: " + exception.getMessage();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception exception){
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidation(MethodArgumentNotValidException exception){
        Map<String, String> erros = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach(error -> {
                String field = ((FieldError)error).getField();
                String message = error.getDefaultMessage();
                erros.put(field, message);
            });
        return erros;
    }
}
