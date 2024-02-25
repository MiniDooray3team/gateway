package com.nhnacademy.springboot.gateway.controller;

import com.nhnacademy.springboot.gateway.adaptor.AccountAdapter;
import com.nhnacademy.springboot.gateway.dto.MemberProfileDto;
import com.nhnacademy.springboot.gateway.dto.request.LoginRequestDto;
import com.nhnacademy.springboot.gateway.dto.request.MemberRegisterRequest;
import com.nhnacademy.springboot.gateway.dto.response.MemberResponseDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/account")
public class AccountController {

    private final AccountAdapter accountAdapter;

    public AccountController(AccountAdapter accountService) {
        this.accountAdapter = accountService;
    }


    @GetMapping("/login")
    public String loginForm(HttpSession session) {
        if(session.getAttribute("member") != null){
            return "redirect:/projects";
        }
        return "account/login_form";
    }

    @PostMapping("/login")
    public String loginPost(LoginRequestDto loginRequestDto, HttpSession session) {
        MemberResponseDto member = accountAdapter.login(loginRequestDto);

        if (member == null) {
            return "redirect:/account/login_form";
        }
        session.setAttribute("member", member);
        return "redirect:/projects";
    }
    @GetMapping("/register")
    public String registerForm() {

        return "account/register_form";
    }

    @PostMapping("/register")
    public String registerPost(MemberRegisterRequest memberRegisterRequest, RedirectAttributes attr) {
        accountAdapter.register(memberRegisterRequest);
        attr.addFlashAttribute("message", "회원가입 성공");
        return "redirect:/account/login";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session, @CookieValue(value = "JSESSIONID") Cookie cookie){
            session.invalidate();
            cookie.setMaxAge(0);
        return "redirect:/account/login";
    }


    @PutMapping("/status")
    public String changeStatus(@RequestParam("status") String status){
        accountAdapter.changeStatus(status);
        return "redirect:/projects";
    }

    @GetMapping("/profile")
    public String memberProfile(Model model){
        MemberProfileDto member =  accountAdapter.getMemberProfile();
        model.addAttribute("profile", accountAdapter.getMemberProfile());
        return "account/profile";
    }

}
