package currencyexchange.emailservice;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;

public class MailMail {

    private JavaMailSender mailSender;

    public void setMailSender(JavaMailSender  mailSender) {
        this.mailSender = mailSender;
    }

    public void sendMailTo(String to, String subject, String body) {

        try {

            MimeMessage message = mailSender.createMimeMessage();

            message.setSubject(subject);
            MimeMessageHelper helper;
            helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setText(body, true);
            mailSender.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
    }

    public void sendMailToAll(List<String> to, String subject, String body){

        try{
            InternetAddress[] cc = new InternetAddress[to.size()];
            MimeMessage message = mailSender.createMimeMessage();
            int counter = 0;
            for (String email : to) {
                cc[counter] = new InternetAddress(email);
                counter++;
            }
            message.setSubject(subject);
            MimeMessageHelper helper;
            helper = new MimeMessageHelper(message, true);
            helper.setBcc(cc);
            helper.setText(body, true);
            mailSender.send(message);
            System.out.println("Sent message successfully....");
        }catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
