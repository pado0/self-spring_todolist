package todolist.todolist.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Todo {

    @Id @GeneratedValue
    @Column(name = "todo_id")
    private Long id;


    // LAZY는 필요할 때 조회하는 옵션.
    @JsonIgnore //여기서 serializer found for class error 발생
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member; // member 객체와 단방향 연관관계

    private String title;
    private Long priority;
    private boolean completed;
    private String context;

}
