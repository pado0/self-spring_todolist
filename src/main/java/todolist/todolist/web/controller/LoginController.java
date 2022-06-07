package todolist.todolist.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import todolist.todolist.domain.Member;
import todolist.todolist.domain.MemberRepository;
import todolist.todolist.domain.MemberService;

@RestController
@Slf4j
@RequestMapping("/members")
@RequiredArgsConstructor
public class LoginController {

    private final MemberService memberService;

    @PostMapping("/login")
    public String login(@ModelAttribute Member member) {
        log.info("login info : {} {}" , member.getLoginId(), member.getPassword());
        Member loginMember = memberService.login(member.getLoginId(), member.getPassword());

        // 찾아온 멤버가 있는지 없는지로 로그인 판단
        if(loginMember == null) return "fail";

        return "ok";
    }


}
