package phenriqued.ToDoList.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import phenriqued.ToDoList.DTOs.ToDoDTO.TaskDTO;
import phenriqued.ToDoList.DTOs.ToDoDTO.TaskRequestDTO;
import phenriqued.ToDoList.Model.TaskEntity.TaskEntity;
import phenriqued.ToDoList.Repositories.TaskRepository.TaskRepository;

@Service
public class TaskService {

    @Autowired
    private TaskRepository repository;

    public TaskDTO createTask(TaskRequestDTO request){
        TaskEntity entity = new TaskEntity(request);
        var task = repository.save(entity);
        return new TaskDTO(task);
    }



}
