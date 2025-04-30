package phenriqued.ToDoList.Repositories.TaskRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import phenriqued.ToDoList.Model.TaskEntity.TaskEntity;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {

}
