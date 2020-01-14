package br.com.mySpringBoot.api.controller;

import br.com.mySpringBoot.api.model.Mail;
import br.com.mySpringBoot.api.repository.MailRepository;
import br.com.mySpringBoot.api.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;

@Controller
@RequestMapping(value = "/mail")
public class MailController {

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    private MailRepository mailRepository;

    @GetMapping
    public void sendMail() {

        try {
//            emailSenderService.sendMail("yuriiagi1@gmail.com", "Test Subject", "First email sender");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostMapping
    public ModelAndView contactUs(@RequestParam String email,
                                  @RequestParam String subject,
                                  @RequestParam String message){
        try {
            Mail mail = new Mail(email, subject, message);
            emailSenderService.sendMail(mail);
            mailRepository.save(mail);
            return new ModelAndView("home");

        } catch (MessagingException e){
            e.printStackTrace();
        }
        return new ModelAndView("contact");
    }

}
