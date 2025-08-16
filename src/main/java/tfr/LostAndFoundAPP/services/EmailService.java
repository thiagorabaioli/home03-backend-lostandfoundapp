package tfr.LostAndFoundAPP.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value; // Importar
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Autowired
    private JavaMailSender emailSender;

    @Async
    public void sendEmail(String to, String subject, String text) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(fromEmail);
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            emailSender.send(message);
        } catch (Exception e) {
            System.err.println("Falha ao enviar email assíncrono: " + e.getMessage());
        }
    }
}