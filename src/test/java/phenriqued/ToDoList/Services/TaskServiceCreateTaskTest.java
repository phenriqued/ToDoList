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
import phenriqued.ToDoList.DTOs.ToDoDTO.TaskRequestDTO;
import phenriqued.ToDoList.Model.TaskEntity.TaskEntity;
import phenriqued.ToDoList.Repositories.TaskRepository.TaskRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class TaskServiceCreateTaskTest {

    @InjectMocks
    private TaskService service;

    @Mock
    private TaskRepository repository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<TaskRequestDTO> taskJson;

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
    @DisplayName("should not create a task when the task is empty")
    void createTaskII() throws Exception {
        //Arrange
        TaskRequestDTO taskDTO = new TaskRequestDTO("");
        //Act
        var result = mockMvc.perform(post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(taskJson.write(new TaskRequestDTO("")).getJson())
        );
        //Assert
        result.andExpect(status().isBadRequest());
        result.andExpect(jsonPath("$.task").value("A tarefa não pode ser vazia"));
    }


}