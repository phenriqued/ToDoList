package phenriqued.ToDoList.Controllers.ToDoPage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ToDoController {

    @GetMapping("/")
    public String viewToDoList(){
        return "View/index";
    }


}
