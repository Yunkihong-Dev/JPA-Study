package com.app.jwtsecurity.controller;

import com.app.jwtsecurity.config.auth.PrincipalDetails;
import com.app.jwtsecurity.entity.User;
import com.app.jwtsecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

// @CrossOrigin 이건 인증이 필요하지 않을 때
@RestController
@RequiredArgsConstructor
@Slf4j
public class RestApiController {

    private final UserRepository userRepository;

     private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/home")
    public String home(){
        return "<h1>home</h1>";
    }

    @PostMapping("token")
    public String token(){
        return "<h1>token</h1>";
    }

    @PostMapping("join")
    public String join(@RequestBody User user){
        user.setUserPassword(bCryptPasswordEncoder.encode(user.getUserPassword()));
        user.setRoles("ROLE_USER");
        userRepository.save(user);
        return "회원가입 완료";
    }

//    user, admin, manager 모두 접근 가능
    @GetMapping("/api/v1/user")
    public String user(Authentication authentication){
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        log.info(principalDetails.getUsername());
        return "user";
    }


//    admin, manager 접근가능
    @GetMapping("/api/v1/manager")
    public String manager(){
        return "manager";
    }

//    admin 접근 가능
    @GetMapping("/api/v1/admin")
    public String admin(){
        return "admin";
    }


}
