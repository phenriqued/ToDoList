package phenriqued.ToDoList.infra.AutomaticExclude;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import phenriqued.ToDoList.Repositories.TaskRepository.TaskRepository;

import java.time.LocalDateTime;
import java.time.Period;

@Component
@EnableScheduling
public class AutomaticDeletion {

    private final long HORA = 60000 * 60;

    @Autowired
    private TaskRepository repository;

    @Scheduled(fixedDelay = HORA)
    public void automaticTaskDeletion(){
        repository.findAll().forEach(task -> {
            if(LocalDateTime.now().isAfter(task.getCreateDateTime().plus(Period.ofDays(1))) ){
                repository.delete(task);
            }

        });
    }

}
