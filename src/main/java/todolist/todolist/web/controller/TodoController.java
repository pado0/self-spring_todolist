package todolist.todolist.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import todolist.todolist.domain.MemberService;
import todolist.todolist.domain.Todo;
import todolist.todolist.domain.TodoService;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/todo")
public class TodoController {

    private final TodoService todoService;
    private final MemberService memberService;


    // user 정보를 queryparam에서 받아와야하나?
    @PostMapping("/add")
    public String addTodo(@RequestParam("memberId") Long memberId,
                          @RequestParam("title") String title,
                          @RequestParam("context") String context,
                          @RequestParam("priority") Long priority) {

        Todo todoSet = new Todo();
        todoSet.setTitle(title);
        todoSet.setMember(memberService.findMemberById(memberId));
        todoSet.setContext(context);
        todoSet.setPriority(priority);

        todoService.addItem(todoSet);
        return "ok";
    }

    // 전체 todo 조회
    @GetMapping("/lists")
    public List<Todo> getTodoLists() {
        return todoService.getTodoList();
    }

    // todoId로 단건 todo 조회
    @GetMapping("/{id}")
    public Todo getTodo(@PathVariable Long id){
        return todoService.getTodo(id);
    }


    // 단건 todo 삭제
    @DeleteMapping("/{id}")
    public String deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
        return "ok";
    }

    @DeleteMapping("/lists")
    public String deleteAll(){
        todoService.deleteAllTodo();
        return "ok";
    }

    // 수정
    @PutMapping("/{id}")
    public String updateTodo(@PathVariable Long id, @ModelAttribute Todo todo){
        todoService.updateTodo(todo.getId(), todo.getTitle(), todo.getPriority());

        return "ok";
    }
}
