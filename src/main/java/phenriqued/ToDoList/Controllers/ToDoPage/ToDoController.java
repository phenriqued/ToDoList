package phenriqued.ToDoList.Controllers.ToDoPage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
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
    public Page<TaskDTO> listAllTask(@PageableDefault(size = 10)Pageable pageable){
        return service.listAllToDo(pageable);
    }


}
