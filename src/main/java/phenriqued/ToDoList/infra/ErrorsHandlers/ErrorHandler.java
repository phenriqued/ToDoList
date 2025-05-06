package phenriqued.ToDoList.infra.ErrorsHandlers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import phenriqued.ToDoList.infra.Exceptions.ToDoException;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(ToDoException.class)
    public ResponseEntity<String> toDoExceptionHandler(ToDoException exception){
        return ResponseEntity.badRequest().body("To Do [ERROR]: " + exception.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception exception){
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

}
