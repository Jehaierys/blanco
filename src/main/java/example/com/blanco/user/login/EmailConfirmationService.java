package example.com.blanco.user.login;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailConfirmationService {

    private final MongoTemplate mongoTemplate;

    private final JavaMailSender mailSender;

    public void sendEmailConfirmationToken(String email) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper =  new MimeMessageHelper(message, true, "UTF-8");

        helper.setTo(email);
        helper.setSubject("Email Confirmation");
        helper.setText(htmlBody(email), true);

        mailSender.send(message);
    }

    public EmailConfirmationToken findToken(String token) {

        Query query = new Query();
        query.addCriteria(Criteria.where("token").is(token));

        return mongoTemplate.findOne(
                query,
                EmailConfirmationToken.class,
                "email_confirmation_tokens"
        );
    }

    public void saveToken(EmailConfirmationToken token) {
        mongoTemplate.save(token);
    }

    public void deleteToken(EmailConfirmationToken token) {
        mongoTemplate.remove(token);
    }

    private String htmlBody(String email) {
        return "<button id=\"confirmButton\">Подтвердить</button>\n" +
                "<script>\n" +
                "    document.getElementById(\"confirmButton\").addEventListener(\"click\", async () => {\n" +
                "        try {\n" +
                "            const response = await fetch(\"http://localhost:8080/confirm" + email + "\", {\n" +
                "                method: \"PUT\",\n" +
                "                headers: {\n" +
                "                    \"Content-Type\": \"application/json\"\n" +
                "                },\n" +
                "                body: JSON.stringify({ message: \"Подтверждение\" }) // Можно отправить нужные данные\n" +
                "            });\n" +
                "            if (response.ok) {\n" +
                "                alert(\"Запрос успешно отправлен!\");\n" +
                "            } else {\n" +
                "                alert(\"Ошибка: \" + response.statusText);\n" +
                "            }\n" +
                "        } catch (error) {\n" +
                "            console.error(\"Ошибка запроса:\", error);\n" +
                "            alert(\"Ошибка отправки запроса\");\n" +
                "        }\n" +
                "    });\n" +
                "</script>";
    }
}
