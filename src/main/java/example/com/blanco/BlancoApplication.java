package example.com.blanco;

import example.com.blanco.account.Card;
import example.com.blanco.user.login.EmailLoginDto;
import example.com.blanco.user.login.LoginController;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.SQLOutput;


@ComponentScan(basePackages = {"example.com.blanco.user.login", "example.com.blanco" })
@SpringBootApplication(scanBasePackages = "example.com.blanco")
public class BlancoApplication  {

    private static PasswordEncoder passwordEncoder;

    private static LoginController loginController;

    @Autowired
    public void setPasswordEncoder(
            PasswordEncoder passwordEncoder,
            LoginController loginController
            ) {
        BlancoApplication.loginController = loginController;
        BlancoApplication.passwordEncoder = passwordEncoder;
    }

    public static void main(String[] args) {
        SpringApplication.run(BlancoApplication.class, args);
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println(new Card());
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println(passwordEncoder.encode("kawaysempai@gmail.comihgervgolrlgngtd"));
        System.out.println();
        System.out.println();
        System.out.println();
        EmailLoginDto dto = new EmailLoginDto();
        dto.setPassword("jiorhvoirehoig");
        dto.setEmail("kawaysempai@gmail.com");
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
//        System.out.println();
//        System.out.println();
//        System.out.println();
//        System.out.println("HITLER");
//        loginController.login(new EmailLoginDto("kawaysempai@gmail.com", "GHDGHDGH"));
//        Long one = 1L;
//        System.out.println();
//        System.out.println();
//        System.out.println();
//        System.out.println();
//        System.out.println();
//        System.out.println();
//        System.out.println();
//        System.out.println( "TOOOOOOOOOOOOOOO OOOOOOOOOOOOOOOOOOOOOOOOOO" +
//                "OOOOOOOOKEN BYYY USER IDDDDDD");
//        System.out.println(loginController.tokenByUserId(one));
//        System.out.println();
//        System.out.println();
//        System.out.println();
//        System.out.println();
//        System.out.println();
//        System.out.println();
//        System.out.println();
    }
}