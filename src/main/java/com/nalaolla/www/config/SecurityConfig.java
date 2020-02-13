package com.nalaolla.www.config;

import com.nalaolla.www.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
/**
 * @Configuration 클래스에 @EnableWebSecurity 어노테이션을 추가하여 Spring Security 설정할 클래스라고 정의.
 * 설정은 WebSebSecurityConfigurerAdapter 클래스를 상속받아 메서드를 구현하는 것이 일반적인 방법.
 */
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * WebSecurityConfigurerAdapter 클래스
     * WebSecurityConfigurer 인스턴스를 편리하게 생성하기 위한 클래스.
     */

    private MemberService memberService;

    @Bean
    /**
     * BCryptPasswordEncoder는 Spring Security에서 제공하는 비밀번호 암호화 객체.
     * Service에서 비밀번호를 암호화할 수 있도록 Bean으로 등록.
     */
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity webSecurity) throws Exception {
        // static 디렉터리의 하위 파일 목록은 인증 무시 ( = 항상통과 )
        webSecurity.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**");
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        /**
         * .antMatchers("/admin/**").hasRole("ADMIN")
         * /admin 으로 시작하는 경로는 ADMIN 롤을 가진 사용자만 접근 가능합니다.
         *
         * .antMatchers("/user/myinfo").hasRole("MEMBER")
         * /user/myinfo 경로는 MEMBER 롤을 가진 사용자만 접근 가능합니다.
         *
         * .antMatchers("/**").permitAll()
         * 모든 경로에 대해서는 권한없이 접근 가능합니다.
         *
         * .anyRequest().authenticated()
         * 모든 요청에 대해, 인증된 사용자만 접근하도록 설정할 수도 있습니다.
         */
        httpSecurity.authorizeRequests()
                // 페이지 권한 설정
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/user/my").hasRole("MEMBER")
                .antMatchers("/**").permitAll() //모든 경로에 대해서는 권한없이 접근 가능.
            .and()
                // 로그인 설정
                .formLogin()
                .loginPage("/user/login")
                .defaultSuccessUrl("/user/login/result")
                .permitAll()
                /**
                 * formlogin()
                 * form 기반으로 인증을 하도록 합니다. 로그인 정보는 기본적으로 HttpSession을 이용합니다.
                 * /login 경로로 접근하면, Spring Security에서 제공하는 로그인 form을 사용할 수 있습니다.
                 * .loginPage("/user/login")
                 * 기본 제공되는 form 말고, 커스텀 로그인 폼을 사용하고 싶으면 loginPage() 메서드를 사용합니다.
                 * 이 때 커스텀 로그인 form의 action 경로와 loginPage()의 파라미터 경로가 일치해야 인증을 처리할 수 있습니다. ( login.html에서 확인 )
                 * .defaultSuccessUrl("/user/login/result")
                 * 로그인이 성공했을 때 이동되는 페이지이며, 마찬가지로 컨트롤러에서 URL 매핑이 되어 있어야 합니다.
                 * .usernameParameter("파라미터명")
                 * 로그인 form에서 아이디는 name=username인 input을 기본으로 인식하는데, usernameParameter() 메서드를 통해 파라미터명을 변경할 수 있습니다.
                 */
            .and()
                // 로그아웃 설정
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
                .logoutSuccessUrl("/user/logout/result")
                .invalidateHttpSession(true)
                /**
                 * 로그아웃을 지원하는 메서드이며, WebSecurityConfigurerAdapter를 사용할 때 자동으로 적용됩니다.
                 * 기본적으로 "/logout"에 접근하면 HTTP 세션을 제거합니다.
                 * .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
                 * 로그아웃의 기본 URL(/logout) 이 아닌 다른 URL로 재정의합니다.
                 * .invalidateHttpSession(true)
                 * HTTP 세션을 초기화하는 작업입니다.
                 * deleteCookies("KEY명")
                 * 로그아웃 시, 특정 쿠기를 제거하고 싶을 때 사용하는 메서드입니다.
                 */
            .and()
                // 403 예외처리 핸들링
                /**
                 * 예외가 발생했을 때 exceptionHandling() 메서드로 핸들링할 수 있습니다.
                 */
                .exceptionHandling().accessDeniedPage("/user/accessDenied");
    }

    @Override
    public  void  configure(AuthenticationManagerBuilder auth) throws Exception {
        /**
         * Spring Security에서 모든 인증은 AuthenticationManager를 통해 이루어지며 AuthenticationManager를 생성하기 위해서는 AuthenticationManagerBuilder를 사용합니다.
         * 로그인 처리 즉, 인증을 위해서는 UserDetailService를 통해서 필요한 정보들을 가져오는데, 예제에서는 서비스 클래스(memberService)에서 이를 처리합니다.
         * 서비스 클래스에서는 UserDetailsService 인터페이스를 implements하여, loadUserByUsername() 메서드를 구현하면 됩니다.
         * 비밀번호 암호화를 위해, passwordEncoder를 사용하고 있습니다.
         */
        auth.userDetailsService(memberService).passwordEncoder(passwordEncoder());
    }
}
