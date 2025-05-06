package phenriqued.ToDoList.Services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import phenriqued.ToDoList.DTOs.ToDoDTO.TaskDTO;
import phenriqued.ToDoList.DTOs.ToDoDTO.TaskRequestDTO;
import phenriqued.ToDoList.Model.TaskEntity.TaskEntity;
import phenriqued.ToDoList.Repositories.TaskRepository.TaskRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
class TaskServiceTest {

    @InjectMocks
    private TaskService service;

    @Mock
    private TaskRepository repository;

    @Test
    @DisplayName("Should create a task.")
    void createTaskI() {
        //Arrange
        TaskRequestDTO taskDTO = new TaskRequestDTO("testando");
        TaskEntity entity = new TaskEntity(taskDTO);

        when(repository.save(any(TaskEntity.class))).thenReturn(entity);

        //Act
        var resultDTO = service.createTask(taskDTO);
        //Assert
        assertEquals(taskDTO.task(), resultDTO.task());
        assertFalse(resultDTO.done());
        assertFalse(resultDTO.favorite());
    }

    @Test
    @DisplayName("Should mark a task as complete.")
    void markAsDone() {
        //Arrange
        TaskEntity task = new TaskEntity(new TaskRequestDTO("testando"));
        task.setDone(false);
        when(repository.findById(any(Long.class))).thenReturn(Optional.of(task));

        //Act
        service.markAsDone(1L);

        //Assertion
        assertTrue(task.getDone());
    }

    @Test
    @DisplayName("Should mark a task as a favorite.")
    void toggleFavorite() {
        //Arrange
        TaskEntity task = new TaskEntity(new TaskRequestDTO("testando"));
        task.setFavorite(false);
        when(repository.findById(any(Long.class))).thenReturn(Optional.of(task));

        //Act
        service.toggleFavorite(1L);

        //Assertion
        assertTrue(task.getFavorite());
    }

    @Test
    @DisplayName("Should update a task")
    void updateTask() {
        //Arrange
        TaskEntity entity = new TaskEntity(new TaskRequestDTO("testando"));

        when(repository.findById(any(Long.class))).thenReturn(Optional.of(entity));
        System.out.println("\n[Task before update]: "+entity.getText()+"\n");

        entity.setText("updating to do");
        TaskDTO taskDTO = new TaskDTO(entity);
        //Act
        service.updateTask(1L, taskDTO);

        //Assertion
        assertEquals(taskDTO.task(), entity.getText());
        assertFalse(taskDTO.done());
        assertFalse(taskDTO.favorite());
    }

    @Test
    void deleteTask() {
        // Arrange
        Long taskId = 1L;
        TaskEntity taskEntity = new TaskEntity();
        when(repository.findById(any(Long.class))).thenReturn(Optional.of(taskEntity));

        // Act
        service.deleteTask(taskId);

        // Assert
        verify(repository, times(1)).findById(taskId);
        verify(repository, times(1)).delete(taskEntity);
    }
}