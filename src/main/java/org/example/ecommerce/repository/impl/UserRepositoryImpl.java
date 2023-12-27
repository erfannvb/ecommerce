package org.example.ecommerce.repository.impl;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.example.ecommerce.base.repository.impl.BaseRepositoryImpl;
import org.example.ecommerce.entity.User;
import org.example.ecommerce.repository.UserRepository;
import org.hibernate.Session;

import java.util.Optional;

public class UserRepositoryImpl extends BaseRepositoryImpl<Long, User> implements UserRepository {

    protected final Session session;

    public UserRepositoryImpl(Session session) {
        super(session);
        this.session = session;
    }

    @Override
    public Class<User> getEntityClass() {
        return User.class;
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        CriteriaBuilder builder = session.getCriteriaBuilder();

        CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);
        Root<User> userRoot = criteriaQuery.from(User.class);
        criteriaQuery.where(builder.equal(userRoot.get("userEmail"), email));

        TypedQuery<User> userTypedQuery = session.createQuery(criteriaQuery);
        return Optional.ofNullable(userTypedQuery.getSingleResult());
    }

    @Override
    public Optional<User> findUserByPassword(String password) {
        CriteriaBuilder builder = session.getCriteriaBuilder();

        CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);
        Root<User> userRoot = criteriaQuery.from(User.class);
        criteriaQuery.where(builder.equal(userRoot.get("userPassword"), password));

        TypedQuery<User> userTypedQuery = session.createQuery(criteriaQuery);
        return Optional.ofNullable(userTypedQuery.getSingleResult());
    }
}
