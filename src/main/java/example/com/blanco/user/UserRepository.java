package example.com.blanco.user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@Transactional
@RequiredArgsConstructor
public class UserRepository {

    private final EntityManager entityManager;

    public List<User> findAll() {
        return entityManager.createQuery("SELECT u FROM User u", User.class)
                .getResultList();
    }

    public String deleteById(Long id) {
        String username = entityManager.find(User.class, id).getUsername();
        int updated = entityManager.createQuery("DELETE FROM User u WHERE u.id = :id").executeUpdate();
        if (updated != 1) {
            throw new RuntimeException("User with id " + id + " was nod deleted");
        }
        return username;
    }

    public Optional<User> findByEmail(String email) {
        try {
            return Optional.ofNullable(entityManager
                    .createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
                    .setParameter("email", email)
                    .getSingleResult());
        } catch (NoResultException e) {
            log.error(e.getMessage());
        }
        return Optional.empty();
    }

    public void save(User user) {
        entityManager.persist(user);
    }

    public Long getIdByEmail(String email) {
        Query query = entityManager.createQuery("SELECT u.id FROM User u WHERE u.email = :email", Long.class);
        query.setParameter("email", email);
        return (Long) query.getSingleResult();
    }

    public void enableUser(Long userId) {
        Query query = entityManager.createQuery("UPDATE User u SET u.enabled = true WHERE u.id = :id");
    }
}