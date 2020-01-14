package br.com.mySpringBoot.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Mail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String from;

    private String subject;

    private String content;

    public Mail() {

    }

    public Mail(String from, String subject, String content) {
        this.from = from;
        this.subject = subject;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
