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

    /**
     * 회원가입폼
     * @return
     */
    @GetMapping("/signup")
    public String signup() {
        return "/member/signup";
    }

    /**
     * 회원가입 submit --> 회원가입 후 로그인페이지로 이동
     * @param dto
     * @return
     */
    @PostMapping("/signup")
    public String signup(MemberDto dto) {
        memberService.joinUser(dto);
        return "redirect:/user/login";
    }

    /**
     * 로그인폼
     * @return
     */
    @GetMapping("/login")
    public String login() {
        return "/member/login";
    }

    /**
     * 로그인 성공페이지
     * @param dto
     * @return
     * SecurityConfig > .defaultSuccessUrl
     */
    @GetMapping("/login/result")
    public String login(MemberDto dto) {
        return "member/loginSuccess";
    }

    /**
     * 로그아웃
     * @return
     * SecurityConfig > .logoutRequestMatcher
     */
    @GetMapping("/logout/result")
    public String logout() {
        return "/member/logout";
    }


}
