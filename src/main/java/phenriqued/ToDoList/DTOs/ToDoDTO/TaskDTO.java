package phenriqued.ToDoList.DTOs.ToDoDTO;


import phenriqued.ToDoList.Model.TaskEntity.TaskEntity;

public record TaskDTO(
        Long id,
        String task,
        Boolean done,
        Boolean favorite) {

    public TaskDTO(TaskEntity entity){
        this(entity.getId(), entity.getText(), entity.getDone(), entity.getFavorite());
    }

}
