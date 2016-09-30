package se.academy.project1.group3;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
///**
// * Created by Administrator on 9/28/2016.
// */
//
//@Configuration
//@EnableWebSecurity
//public class SpringSec extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
//    }
//}

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class SpringSec {

    @Autowired
    Repository repository;

    @GetMapping("/login")
    public String form() {
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, HttpServletResponse res) {
        session.invalidate();
        Cookie cookie = new Cookie("jsessionid", "");
        cookie.setMaxAge(0);
        res.addCookie(cookie);
        return "redirect:/";
    }

    @PostMapping("/loginForm")
    public String submit(HttpSession session, @RequestParam String username, @RequestParam String password) {
        if (repository.loginUser(username, password) ) {
            session.setAttribute("user", username);
            return "redirect:/";
        }
        return "login";
    }

    @GetMapping("/headerIndex")
    public ModelAndView hi (HttpSession session) {
        if (session.getAttribute("user") != null) {
            ModelAndView modelAndView = new ModelAndView("header");
            modelAndView.addObject("logged", session.getAttribute("user"));
            return modelAndView;
        }
        return new ModelAndView("headerIndex");
    }
}