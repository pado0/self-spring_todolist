package todolist.todolist.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import todolist.todolist.domain.Todo;
import todolist.todolist.domain.TodoService;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/todo")
public class TodoController {

    private final TodoService todoService;


    // user 정보를 queryparam에서 받아와야하나?
    @PostMapping("/add")
    public String addTodo(@ModelAttribute Todo todo) {
        todoService.addItem(todo);
        return "ok";
    }


}
