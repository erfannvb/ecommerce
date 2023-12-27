package org.example.ecommerce.service.impl;

import org.example.ecommerce.base.service.impl.BaseServiceImpl;
import org.example.ecommerce.entity.User;
import org.example.ecommerce.repository.UserRepository;
import org.example.ecommerce.service.UserService;
import org.hibernate.Session;

public class UserServiceImpl extends BaseServiceImpl<Long, User, UserRepository> implements UserService {

    protected final Session session;

    public UserServiceImpl(Session session, UserRepository repository) {
        super(session, repository);
        this.session = session;
    }

    @Override
    public void addUser(User user) {
        session.beginTransaction();
        repository.save(user);
        session.getTransaction().commit();
    }
}
