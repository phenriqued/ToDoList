package phenriqued.ToDoList.Controllers.ToDoPage;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import phenriqued.ToDoList.DTOs.ToDoDTO.TaskDTO;
import phenriqued.ToDoList.DTOs.ToDoDTO.TaskRequestDTO;
import phenriqued.ToDoList.Services.TaskService;

import java.net.URI;

@Controller
@Tag(name = "To Do List", description = "To Do List To Do List com funcionalidades básicas: criação de novas tarefas, visualização da lista, marcação de tarefas como feitas e exclusão de itens.")
public class ToDoController {

    @Autowired
    private TaskService service;


    @GetMapping
    public String viewToDoList(){
        return "View/index";
    }

    @Operation(summary = "Cria uma nova tarefa")
    @ApiResponse(responseCode = "201", description = "Cria com sucesso uma nova tarefa")
    @ApiResponse(responseCode = "400", description = "Criar uma tarefa com a mesma vazia")
    @PostMapping
    @ResponseBody
    public ResponseEntity<Void> createTask(@RequestBody @Valid TaskRequestDTO taskRequestDTO, UriComponentsBuilder uriComponentsBuilder){
        var task = service.createTask(taskRequestDTO);
        URI uri = uriComponentsBuilder.path("/").buildAndExpand(task.id()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @Operation(summary = "Lista todas tarefas")
    @ApiResponse(responseCode = "200", description = "Listar todas tarefas com sucesso")
    @GetMapping("/ToDoList")
    @ResponseBody
    public ResponseEntity<Page<TaskDTO>> listAllTask(@PageableDefault(size = 20)Pageable pageable){
        return ResponseEntity.ok(service.listAllToDo(pageable));
    }

    @Operation(summary = "Atualiza a tarefa e marca ou desmarca como concluída")
    @ApiResponse(responseCode = "204", description = "acha a tarefa e efetua a atualização")
    @ApiResponse(responseCode = "400", description = "Não conseguiu achar a tarefa no banco de dados")
    @PutMapping("/task/{id}/done")
    public ResponseEntity<Void> markAsDone(@PathVariable Long id){
        service.markAsDone(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Marca a tarefa como favorita e coloca no topo da lista")
    @ApiResponse(responseCode = "204", description = "marca ou desmarca a tarefa como concluída")
    @ApiResponse(responseCode = "400", description = "Não conseguiu achar a tarefa no banco de dados")
    @PutMapping("task/{id}/favorite")
    public ResponseEntity<Void> toggleFavorite(@PathVariable Long id){
        service.toggleFavorite(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Atualiza o campo da tarefa para ser efetuada")
    @ApiResponse(responseCode = "204", description = "acha a tarefa e Atualiza o campo da tarefa para ser efetuada")
    @ApiResponse(responseCode = "400", description = "Não conseguiu achar a tarefa no banco de dados")
    @PutMapping("/task/{id}")
    public ResponseEntity<Void> updateTask(@PathVariable Long id, @RequestBody @Valid TaskDTO taskDTO){
        service.updateTask(id, taskDTO);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Deleta uma tarefa")
    @ApiResponse(responseCode = "204", description = "acha a tarefa e deleta")
    @ApiResponse(responseCode = "400", description = "Não conseguiu achar a tarefa no banco de dados")
    @Tag(name = "AutomaticDeletion", description = "A exclusão de tarefas automaticas ocorre após 24 horas depois da criação da tarefa!")
    @DeleteMapping("/task/{id}/remove")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id){
        service.deleteTask(id);
        return ResponseEntity.noContent().build();
    }


}
