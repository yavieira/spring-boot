package br.com.mySpringBoot.api.repository;

import br.com.mySpringBoot.api.model.Mail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MailRepository extends JpaRepository<Mail, Long> {

}
