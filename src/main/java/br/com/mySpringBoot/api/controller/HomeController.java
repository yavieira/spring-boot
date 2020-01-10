package br.com.mySpringBoot.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/home")
public class HomeController {

    @GetMapping(value = "user")
    public ModelAndView form() {

        ModelAndView mv = new ModelAndView();
        mv.setViewName("userForm");
        return mv;
    }

    public static void main(String[] args) {

    }
}