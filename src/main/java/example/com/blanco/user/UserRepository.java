package example.com.blanco.user;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final Session session;

    public List<User> findAll() {
        return session.createQuery("SELECT U FROM User U", User.class).getResultList();
    }
}
