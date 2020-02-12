package br.com.mySpringBoot.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/home")
public class HomeController {

    @GetMapping
    public ModelAndView home(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("home");
        return mv;
    }

    @GetMapping(value = "user")
    public ModelAndView form() {

        ModelAndView mv = new ModelAndView();
        mv.setViewName("userForm");
        return mv;
    }

    @GetMapping(value = "music")
    public ModelAndView topic() {

        ModelAndView mv = new ModelAndView();
        mv.setViewName("musicForm");
        return mv;
    }

    @GetMapping(value = "contact")
    public ModelAndView contact() {

        ModelAndView mv = new ModelAndView();
        mv.setViewName("contact");
        return mv;
    }

    public static void main(String[] args) {

    }
}