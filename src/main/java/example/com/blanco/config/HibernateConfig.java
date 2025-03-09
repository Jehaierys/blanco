package example.com.blanco.config;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class HibernateConfig {

    @Bean
    public SessionFactory sessionFactory() {
        return new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    }

    @Bean
    public Session session(SessionFactory sessionFactory) {
        return sessionFactory.openSession();
    }
}
