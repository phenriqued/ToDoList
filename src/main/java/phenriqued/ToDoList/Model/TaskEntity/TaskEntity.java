package phenriqued.ToDoList.Model.TaskEntity;


import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import phenriqued.ToDoList.DTOs.ToDoDTO.TaskRequestDTO;

@Entity(name = "TaskEntity")
@Table(name = "tb_task")

@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    private String text;
    @Setter
    private Boolean done;
    @Setter
    private Boolean favorite;

    public TaskEntity(TaskRequestDTO task) {
        this.text = task.task();
        this.done = false;
        this.favorite = false;
    }
}
