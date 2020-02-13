package com.nalaolla.www.controller;

import com.nalaolla.www.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    /**
     * SecurityConfig > .antMatchers("/admin/**").hasRole("ADMIN") 룰 적용
     */
    private MemberService memberService;

}
