package todolist.todolist.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TodoService {

    private final TodoRepository todoRepository;

    // 아이템 추가
    @Transactional
    public void addItem(Todo todo){
        todoRepository.saveTodo(todo);
    }

    // 특정 아이템 조회
    public Todo getTodo(Long id) {
        return todoRepository.findOne(id);
    }

    public List<Todo> getTodoList(){
        return todoRepository.findAll();
    }


    @Transactional
    public void deleteTodo(Long id) {
        todoRepository.deleteOne(id);
    }

    @Transactional
    public void deleteAllTodo() {
        todoRepository.deleteAll();
    }

    @Transactional
    public void updateTodo(Long id, String titie, Long priority) {
        Todo todoFind = todoRepository.findOne(id);

        // 변경감지
        todoFind.setPriority(priority);
        todoFind.setTitle(titie);

    }
}
