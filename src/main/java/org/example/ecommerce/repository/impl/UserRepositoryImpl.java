package org.example.ecommerce.repository.impl;

import org.example.ecommerce.base.repository.impl.BaseRepositoryImpl;
import org.example.ecommerce.entity.User;
import org.example.ecommerce.repository.UserRepository;
import org.hibernate.Session;

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
}
