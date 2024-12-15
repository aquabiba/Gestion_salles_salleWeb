package Servlet;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.util.Properties;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/envoyerProfesseur")
public class EmailServlet extends HttpServlet {

//        private static final String EMAIL_FROM = "younesseboualam@gmail.com";
//        private static final String APP_PASSWORD = "ahek hzfp qnmq xcnc";


//    private static Properties getGmailProperties() {
//        Properties prop = new Properties();
//        prop.put("mail.smtp.auth", "true");
//        prop.put("mail.smtp.starttls.enable", "true");
//        prop.put("mail.smtp.host", "smtp.gmail.com");
//        prop.put("mail.smtp.port", "587");
//        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
//        return prop;
//    }
//
//
//    private void sendEmail(String emailTo, String subject, String messageBody) throws MessagingException {
//            Session session = Session.getInstance(getGmailProperties(), new Authenticator() {
//                @Override
//                protected PasswordAuthentication getPasswordAuthentication() {
//                    return new PasswordAuthentication(EMAIL_FROM, APP_PASSWORD);
//                }
//            });
//
//            Message message = new MimeMessage(session);
//            message.setFrom(new InternetAddress(EMAIL_FROM));
//            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailTo));
//            message.setSubject(subject);
//            message.setText(messageBody);
//
//            Transport.send(message);
//    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String recipient = "ayoubbarahal2@gmail.com";
        String subject = request.getParameter("Emploi Du Temps");
        String messageContent = request.getParameter("Vous trouvez ci_deussus une piéce conjointe qui contient emploi du temps");

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        final String username = "boualamyounesse@gmail.com";
        final String password = "ahek hzfp qnmq xcnc";

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject(subject);
            message.setText(messageContent);

            Transport.send(message);

            response.getWriter().println("Email envoyé avec succès à " + recipient);

        } catch (MessagingException e) {
            e.printStackTrace();
            response.getWriter().println("Erreur lors de l'envoi de l'email : " + e.getMessage());
        }
    }
}
