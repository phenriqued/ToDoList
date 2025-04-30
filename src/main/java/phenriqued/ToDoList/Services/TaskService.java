package phenriqued.ToDoList.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import phenriqued.ToDoList.Repositories.TaskRepository.TaskRepository;

@Service
public class TaskService {

    @Autowired
    private TaskRepository repository;





}
