package phenriqued.ToDoList.Model.TaskEntity;


import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

}
