package phenriqued.ToDoList.DTOs.ToDoDTO;

import jakarta.validation.constraints.NotBlank;

public record TaskRequestDTO(
        @NotBlank(message = "A tarefa não pode ser vazia")
        String task) {

}
