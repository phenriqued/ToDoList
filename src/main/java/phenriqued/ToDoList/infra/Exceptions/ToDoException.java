package phenriqued.ToDoList.infra.Exceptions;

public class ToDoException extends RuntimeException {
    public ToDoException(String message) {
        super("ERROR: \n["+message+"]");
    }
}
