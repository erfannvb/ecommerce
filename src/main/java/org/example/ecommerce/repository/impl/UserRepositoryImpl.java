package org.example.ecommerce.repository.impl;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.example.ecommerce.base.repository.impl.BaseRepositoryImpl;
import org.example.ecommerce.entity.User;
import org.example.ecommerce.repository.UserRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;

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
    public Optional<User> findUserByEmailAndPassword(String email, String password) {
        String hql = "from User where userEmail =: email and userPassword =: password";
        Query<User> userQuery = session.createQuery(hql, User.class);
        userQuery.setParameter("email", email);
        userQuery.setParameter("password", password);
        return Optional.ofNullable(userQuery.getSingleResult());
    }
}
