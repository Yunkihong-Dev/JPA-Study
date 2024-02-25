package com.app.security.controller;


import com.app.security.Service.MemberService;
import com.app.security.domain.MemberDTO;
import com.app.security.exception.LoginFailException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member/*")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("join")
    public String goToJoinForm(MemberDTO memberDTO){
        return "/member/join";
    }

    @PostMapping("join")
    public RedirectView join(MemberDTO memberDTO){
        memberService.join(memberDTO);
        return new RedirectView("/member/login");
    }
    @GetMapping("login")
    public String goToLoginForm(){
        return "member/login";
    }
    @PostMapping("login")
    public RedirectView login(MemberDTO memberDTO, HttpSession httpSession){
        memberService.login(memberDTO.getMemberId(),memberDTO.getMemberPassword())
        .ifPresentOrElse(member -> {
                    httpSession.setAttribute("role", member.getMemberRole().name());
                    httpSession.setAttribute("memberId",member.getMemberId());
                    httpSession.setAttribute("id",member.getId());
                    httpSession.setMaxInactiveInterval(1800);
                },() ->{throw new LoginFailException();});
        return new RedirectView("/board/list");
    }


    @GetMapping("logout")
    public RedirectView logout(HttpSession session){
        session.invalidate();
        return new RedirectView("/member/login");
    }

}
