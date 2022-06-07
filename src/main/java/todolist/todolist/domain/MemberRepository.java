package todolist.todolist.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
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
        System.out.println("id: " + id);
        return em.find(Member.class, id);
    }

    // id로 멤버 찾기
    public Optional<Member> findByLoginId(String loginId) {
        log.info("memberRepository login info {}" , loginId);
        findALl().stream().forEach(m -> System.out.println("iter:: " + m.getLoginId()));
        return findALl().stream()
                .filter(m -> m.getLoginId().equals(loginId)).findFirst();
    }

    public List<Member> findALl(){
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }

}
