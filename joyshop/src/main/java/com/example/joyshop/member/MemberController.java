package com.example.joyshop.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/register")
    String resister(Authentication auth){
        String page;
        if (auth.isAuthenticated()){
            page =  "redirect:/list";
        } else {
            page = "register";
        }
        return page;
    }

    @PostMapping("/member")
    String addMember(String username, String password, String displayName) throws Exception {
        memberService.saveMember(username, password, displayName);
        return "redirect:/list";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/my-page")
    public String myPage(Authentication auth){

//        auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER"));
        return "mypage";
    }
}
