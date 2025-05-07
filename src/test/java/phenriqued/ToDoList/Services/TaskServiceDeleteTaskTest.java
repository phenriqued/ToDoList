package phenriqued.ToDoList.Services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import phenriqued.ToDoList.DTOs.ToDoDTO.TaskDTO;
import phenriqued.ToDoList.DTOs.ToDoDTO.TaskRequestDTO;
import phenriqued.ToDoList.Model.TaskEntity.TaskEntity;
import phenriqued.ToDoList.Repositories.TaskRepository.TaskRepository;
import phenriqued.ToDoList.infra.Exceptions.ToDoException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
class TaskServiceDeleteTaskTest {

    @InjectMocks
    private TaskService service;

    @Mock
    private TaskRepository repository;

    @Test
    @DisplayName("should delete a task when the task ID is correct")
    void deleteTaskI() {
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

    @Test
    @DisplayName("should not delete a task when the task ID is incorrect")
    void deleteTaskII() {
        // Assert
        assertThrows(ToDoException.class, () -> service.deleteTask(1L));
    }


}