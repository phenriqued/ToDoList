package phenriqued.ToDoList.Controllers.ToDoPage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import phenriqued.ToDoList.DTOs.ToDoDTO.TaskDTO;
import phenriqued.ToDoList.DTOs.ToDoDTO.TaskRequestDTO;
import phenriqued.ToDoList.Services.TaskService;

@Controller
public class ToDoController {

    @Autowired
    private TaskService service;


    @GetMapping
    public String viewToDoList(){
        return "View/index";
    }

    @PostMapping
    @ResponseBody
    public void createTask(@RequestBody TaskRequestDTO taskRequestDTO){
        service.createTask(taskRequestDTO);
    }

    @GetMapping("/ToDoList")
    @ResponseBody
    public Page<TaskDTO> listAllTask(@PageableDefault(size = 20)Pageable pageable){
        return service.listAllToDo(pageable);
    }

    @PutMapping("task/{id}/favorite")
    public ResponseEntity<Void> toggleFavorite(@PathVariable Long id){
        service.toggleFavorite(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/task/{id}")
    public ResponseEntity<Void> updateTask(@PathVariable Long id, @RequestBody TaskDTO taskDTO){
        service.updateTask(id, taskDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/task/{id}/remove")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id){
        service.deleteTask(id);
        return ResponseEntity.noContent().build();
    }


}
