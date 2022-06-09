package todolist.todolist.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.Null;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long join(Member member) {
        memberRepository.save(member);
        return member.getId();
    }

    public Member login(String loginId, String password) {
        // post로 온 요청에는 Id가 없는데, 해당 id로 조회하려하니 null이라 오류가 난다.
        log.info("memberService login info: {} {} ", loginId, password);
        return memberRepository.findByLoginId(loginId).filter(m -> m.getPassword().equals(password)).orElse(null);
    }

    public Member findMemberById(Long id) {
        return memberRepository.fineOne(id);
    }

}
