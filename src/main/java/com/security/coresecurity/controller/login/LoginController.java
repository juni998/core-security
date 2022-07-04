package com.security.coresecurity.controller.login;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "user/login/login";

    }

    //GET방식 로그아웃
    @GetMapping("/logout")
    //로그아웃 핸들러 사용
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        //시크릿 컨텍스트 내 인증객체 불러오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        //인증객체가 존재하지 않을때
        if (authentication != null) {
            //로그아웃 처리하는 핸들러
          new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return "redirect:/login";

    }
}
