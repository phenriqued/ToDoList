package phenriqued.ToDoList.Repositories.TaskRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import phenriqued.ToDoList.Model.TaskEntity.TaskEntity;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {

    @Query("SELECT task FROM TaskEntity task ORDER BY task.favorite DESC")
    Page<TaskEntity> findAllOrderByFavoriteTrue(Pageable pageable);

}
