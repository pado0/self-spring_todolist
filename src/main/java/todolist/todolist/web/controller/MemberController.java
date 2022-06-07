package todolist.todolist.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import todolist.todolist.domain.Member;
import todolist.todolist.domain.MemberService;

@Controller
@Slf4j
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/join")
    public String joinForm(@ModelAttribute Member member){ // 여기서 세팅된 모델 어트리뷰트가 html의 objectfh
        return "members/joinForm";
    }

    @PostMapping("/join")
    public String saveMember(@ModelAttribute Member member){
        memberService.join(member);
        System.out.println("member = " + member.getLoginId());
        return "redirect:/";
    }
}
