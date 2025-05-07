package phenriqued.ToDoList.Services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import phenriqued.ToDoList.DTOs.ToDoDTO.TaskRequestDTO;
import phenriqued.ToDoList.Model.TaskEntity.TaskEntity;
import phenriqued.ToDoList.Repositories.TaskRepository.TaskRepository;
import phenriqued.ToDoList.infra.Exceptions.ToDoException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class TaskServiceToggleFavoriteTest {

    @InjectMocks
    private TaskService service;

    @Mock
    private TaskRepository repository;

    @Test
    @DisplayName("Should mark a task as a favorite.")
    void toggleFavoriteI() {
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
    @DisplayName("should be able to unmark a task as a favorite")
    void toggleFavoriteII() {
        //Arrange
        TaskEntity task = new TaskEntity(new TaskRequestDTO("testando"));
        task.setFavorite(true);
        when(repository.findById(any(Long.class))).thenReturn(Optional.of(task));

        //Act
        service.toggleFavorite(1L);

        //Assertion
        assertFalse(task.getFavorite());
    }
    @Test
    @DisplayName("should not mark a favorite task when the ID task is not exist")
    void toggleFavoriteIII() {
        //Assertion
        assertThrows(ToDoException.class, () -> service.toggleFavorite(1L));
    }




}