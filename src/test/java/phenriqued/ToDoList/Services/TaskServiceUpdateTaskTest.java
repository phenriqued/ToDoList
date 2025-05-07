package phenriqued.ToDoList.Services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import phenriqued.ToDoList.DTOs.ToDoDTO.TaskDTO;
import phenriqued.ToDoList.DTOs.ToDoDTO.TaskRequestDTO;
import phenriqued.ToDoList.Model.TaskEntity.TaskEntity;
import phenriqued.ToDoList.Repositories.TaskRepository.TaskRepository;
import phenriqued.ToDoList.infra.Exceptions.ToDoException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class TaskServiceUpdateTaskTest {

    @InjectMocks
    private TaskService service;

    @Mock
    private TaskRepository repository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<TaskDTO> taskJson;

    @Test
    @DisplayName("Should update a task")
    void updateTaskI() {
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
    @DisplayName("should not make the update from one task to another empty one")
    void updateTaskII() throws Exception {
        TaskEntity entity = new TaskEntity(new TaskRequestDTO("testando"));
        entity.setText("");

        when(repository.findById(any(Long.class))).thenReturn(Optional.of(entity));
        mockMvc.perform(put("/task/"+1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(taskJson.write(new TaskDTO(entity)).getJson())

        ).andExpect(status().isBadRequest())
         .andExpect(jsonPath("$.task").value("A tarefa não pode ser vazia"));
    }

    @Test
    @DisplayName("should not mark a favorite task when the ID task is not exist")
    void updateTaskIII() {
        TaskDTO taskDTO = new TaskDTO(new TaskEntity(new TaskRequestDTO("Teste")));
        //Assertion
        assertThrows(ToDoException.class, () -> service.updateTask(1L, taskDTO));
    }

}