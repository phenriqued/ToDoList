package phenriqued.ToDoList.Model.TaskEntity;


import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "TaskEntity")
@Table(name = "tb_task")

@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    private Boolean done;
    private Boolean favorite;

}
