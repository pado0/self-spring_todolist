package todolist.todolist.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import todolist.todolist.domain.Member;
import todolist.todolist.domain.MemberRepository;
import todolist.todolist.domain.MemberService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("/members")
@RequiredArgsConstructor
public class LoginController {

    private final MemberService memberService;


    // todo: 아이디 틀렸을 경우 리턴처리
    // todo: @Valid로 유효성검사 및 bindingResult
    @PostMapping("/login")
    public String login(@ModelAttribute @Valid Member member, BindingResult bindingResult) {

        List<String> errors = bindingResult.getAllErrors().stream().map(e->e.getDefaultMessage()).collect(Collectors.toList());
        log.info("login info : {} {}" , member.getLoginId(), member.getPassword());
        Member loginMember = memberService.login(member.getLoginId(), member.getPassword());

        // 찾아온 멤버가 있는지 없는지로 로그인 판단
        if(loginMember == null) return "fail";

        return "ok";
    }


}
