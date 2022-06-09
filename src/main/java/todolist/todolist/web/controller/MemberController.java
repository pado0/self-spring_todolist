package todolist.todolist.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import todolist.todolist.domain.Member;
import todolist.todolist.domain.MemberService;

import javax.validation.Valid;

// 회원 가입 컨트롤러. REST controller로 전환
@RestController
@Slf4j
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    /* @GetMapping("/join")
    public String joinForm(@ModelAttribute Member member){ // 여기서 세팅된 모델 어트리뷰트가 html의 objectfh
        return "members/joinForm";
    }*/

    // todo 아이디 중복 해결. 빈값이면 안된다도 추가.
    // validation , request용 MemberRequestDto 추가
    // https://jeong-pro.tistory.com/203
    @PostMapping("/join")
    public String saveMember(@ModelAttribute Member member){
        memberService.join(member);
        log.info("loginID={}, password={}", member.getLoginId(), member.getPassword());
        return "ok";
    }
}
