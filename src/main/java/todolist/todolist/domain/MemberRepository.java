package todolist.todolist.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    // Entity Manager 주입
    //@PersistenceContext
    private final EntityManager em;

    // 회원 저장
    public Long save(Member member){
        em.persist(member);
        return member.getId();
    }

    // 회원 조회
    public Member fineOne(Long id) {
        return em.find(Member.class, id);
    }

}
