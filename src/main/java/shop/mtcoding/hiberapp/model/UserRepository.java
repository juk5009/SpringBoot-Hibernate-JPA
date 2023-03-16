package shop.mtcoding.hiberapp.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.TransactionScoped;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class UserRepository {
    private final EntityManager em; // AutoWired

    @Transactional
    public User save(User user) {
        em.persist(user);
        return user;
    }

    @Transactional
    public User update(User user) {
        return em.merge(user);
    }

    @Transactional
    public void delete(User user) {
        em.remove(user);
    }

    @Transactional
    public User findById(Long id) {
        return em.find(User.class, id);
    }

    @Transactional
    public List<User> findAll(int page, int row) {
        return em.createQuery("select u from User u", User.class)
                .setFirstResult(page * row)
                .setMaxResults(row)
                .getResultList();
    }

    @Transactional
    public List<User> findAll() {
        return em.createQuery("select u from User u", User.class)
                .getResultList();
    }
}
