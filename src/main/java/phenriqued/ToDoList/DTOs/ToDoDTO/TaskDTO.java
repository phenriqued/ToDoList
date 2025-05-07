package phenriqued.ToDoList.DTOs.ToDoDTO;


import jakarta.validation.constraints.NotBlank;
import phenriqued.ToDoList.Model.TaskEntity.TaskEntity;

public record TaskDTO(
        Long id,
        @NotBlank(message = "A tarefa não pode ser vazia")
        String task,
        Boolean done,
        Boolean favorite) {

    public TaskDTO(TaskEntity entity){
        this(entity.getId(), entity.getText(), entity.getDone(), entity.getFavorite());
    }

}
