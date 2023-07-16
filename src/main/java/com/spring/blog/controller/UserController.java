package com.spring.blog.controller;

import com.spring.blog.entity.User;
import com.spring.blog.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final UsersService usersService;

    @Autowired
    public UserController(UsersService usersService){
        this.usersService = usersService;
    }

    // Get 방식으로 로그인창으로 넘어가는 로직을 작성해주세요.
    // /WEB-INF/views/user/login.jsp로 넘어가게 해주세요
    @GetMapping("/login")
    public String login(){
        return "user/login";
    }

    // Get방식으로 회원가입폼으로 넘어가는 로직을 작성해주세요.
    // /WEB-INF/views/user/signup.jsp로 넘어가게 해주세요
    @GetMapping("/signup")
    public String signupForm() {
        return "user/signup";
    }

    

    // POST 방식으로 회원가입 요청을 처리하게 해주세요.
    // 주소는 localhost:8080/signup 입니다.
    // 커맨드 객체로 User Entity를 선언해서, 가입 정보를 받아  .save()를 호출해주시면 됩니다.
    // 실행결과는 리다이렉트로 /login으로 돌아가게 해주세요
    @PostMapping("/signup")
    public String signup(User user){
        usersService.save(user);
        return "redirect:/user/login";
    }


}
