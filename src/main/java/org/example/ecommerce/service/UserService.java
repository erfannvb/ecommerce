package org.example.ecommerce.service;

import org.example.ecommerce.base.service.BaseService;
import org.example.ecommerce.entity.User;

import java.util.Optional;

public interface UserService extends BaseService<Long, User> {

    void addUser(User user);

    Optional<User> findUserByEmail(String email);

    Optional<User> findUserByPassword(String password);


}
