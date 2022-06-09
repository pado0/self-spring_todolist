package todolist.todolist.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class TodoRepository {

    private final EntityManager em;

    // 저장 수정
    public void saveTodo(Todo todo) {
        if(todo.getId() == null) {
            em.persist(todo);
        }else{
            System.out.println("todo.getId() = " + todo.getId());
            em.merge(todo);
            // em.flush();

            Todo checkTodo = em.find(Todo.class, todo.getId());
            System.out.println(checkTodo.getId());
        }
    }

    public Todo findOne(Long id) {
        return em.find(Todo.class, id);
    }

    public List<Todo> findAll() {
        return em.createQuery("select t from Todo t", Todo.class)
                .getResultList();
    }


    public void deleteOne(Long id) {
        em.remove(findOne(id));
    }

    // todo : 쿼리가 하나씩 날리간다는 단점. batch delete로 구현해보자
    public void deleteAll(){
        findAll().stream().forEach(t -> em.remove(t));
    }
}
