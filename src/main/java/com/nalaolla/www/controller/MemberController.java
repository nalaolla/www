package com.nalaolla.www.controller;

import com.nalaolla.www.dto.MemberDto;
import com.nalaolla.www.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/user")
public class MemberController {
    private MemberService memberService;

    @RequestMapping("/")
    public String index() {
        return "/member/index";
    }

    //회원가입폼
    @GetMapping("/signup")
    public String signup() {
        return "/member/signup";
    }

    //회원가입..
    @PostMapping("/signup")
    public String signup(MemberDto dto) {
        memberService.joinUser(dto);
        return "redirect:/user/login";
    }

    @GetMapping("/login")
    public String login() {
        return "/member/login";
    }

    @GetMapping("/login/result")
    public String login(MemberDto dto) {
        return "member/loginSuccess";
    }

    @GetMapping("/logout/result")
    public String logout() {
        return "/member/logout";
    }


}
