package com.nalaolla.www.controller;

import com.nalaolla.www.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor
@RequestMapping("/user")
public class MemberController {
    private MemberService memberService;

    @RequestMapping("/")
    public String index() {
        return "/member/index";
    }

}
