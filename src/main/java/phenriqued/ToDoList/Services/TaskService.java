package phenriqued.ToDoList.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import phenriqued.ToDoList.DTOs.ToDoDTO.TaskDTO;
import phenriqued.ToDoList.DTOs.ToDoDTO.TaskRequestDTO;
import phenriqued.ToDoList.Model.TaskEntity.TaskEntity;
import phenriqued.ToDoList.Repositories.TaskRepository.TaskRepository;
import phenriqued.ToDoList.infra.Exceptions.ToDoException;

@Service
public class TaskService {

    @Autowired
    private TaskRepository repository;

    public TaskDTO createTask(TaskRequestDTO request){
        TaskEntity entity = new TaskEntity(request);
        var task = repository.save(entity);
        return new TaskDTO(task);
    }


    public Page<TaskDTO> listAllToDo(Pageable pageable) {
        return repository.findAllOrderByFavoriteTrue(pageable).map(TaskDTO::new);
    }

    public void markAsDone(Long id) {
        var task = repository.findById(id).orElseThrow(() -> new ToDoException("could not find task id"));
        Boolean done =! task.getDone();
        task.setDone(done);
        repository.flush();
    }

    public void toggleFavorite(Long id) {
        var task = repository.findById(id).orElseThrow(() -> new ToDoException("could not find task id"));
        Boolean favorite =! task.getFavorite();
        task.setFavorite(favorite);
        repository.flush();
    }

    public void updateTask(Long id, TaskDTO taskDTO) {
        var task = repository.findById(id).orElseThrow(() -> new ToDoException("could not find task id to update"));
        task.setText(taskDTO.task());
        repository.flush();
    }

    public void deleteTask(Long id) {
        var task = repository.findById(id).orElseThrow(() -> new ToDoException("could not find task id to delete"));
        repository.delete(task);
    }

}
