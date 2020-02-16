package com.nalaolla.www.controller.common;

import com.google.gson.Gson;
import com.nalaolla.www.service.KakaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;



@RequiredArgsConstructor
@Controller
@RequestMapping("/social/login")
public class SocialController {

    private final Environment env;
    private final RestTemplate restTemplate;
    private final Gson gson;
    private final KakaoService kakaoService;

    @Value("${spring.url.base}")
    private String baseUrl;

    @Value("${spring.social.kakao.client_id}")
    private String kakaoClientId;

    @Value("${spring.social.kakao.redirect}")
    private String kakaoRedirect;

    @GetMapping
    public ModelAndView socialLogin(ModelAndView mav) {
        StringBuilder loginUrl = new StringBuilder()
                .append(env.getProperty("spring.social.kakao.url.login"))
                .append("?client_id=").append(kakaoClientId)
                .append("&amp;response_type=code")
                .append("&amp;redirect_uri=").append(baseUrl).append(kakaoRedirect);
        mav.addObject("loginUrl", loginUrl);
        mav.setViewName("social/login");
        return mav;
    }

    @GetMapping(value="/kakao")
    public ModelAndView redirectKakao(ModelAndView mav, @RequestParam String code) {
        mav.addObject("authInfo", kakaoService);
        mav.setViewName("social/redirectKakao");
        return mav;
    }
}
