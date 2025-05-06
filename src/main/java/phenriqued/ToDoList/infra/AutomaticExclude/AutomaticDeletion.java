package phenriqued.ToDoList.infra.AutomaticExclude;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import phenriqued.ToDoList.Model.TaskEntity.TaskEntity;
import phenriqued.ToDoList.Repositories.TaskRepository.TaskRepository;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;

@Component
@EnableScheduling
public class AutomaticDeletion {

    private static final Logger logger = LoggerFactory.getLogger(AutomaticDeletion.class);

    private final long HORA = 60000 * 60;

    @Autowired
    private TaskRepository repository;

    @Scheduled(fixedDelay = HORA)
    public void automaticTaskDeletion(){
        logger.info("Iniciando a verificação e exclusão automática de tarefas");

        List<TaskEntity> tasksToDelete = repository.findAll().stream()
                .filter(task -> LocalDateTime.now().isAfter(task.getCreateDateTime().plus(Period.ofDays(1))))
                .toList();

        if(!tasksToDelete.isEmpty()){
            logger.info("Encontradas {} tarefas para exclusão.", tasksToDelete.size());
            repository.deleteAll(tasksToDelete);
            logger.info("{} Deletas com sucesso!", tasksToDelete.size());
        }else{
            logger.info("Nenhuma tarefa elegível para exclusão automática no momento.");
        }
    }

}
