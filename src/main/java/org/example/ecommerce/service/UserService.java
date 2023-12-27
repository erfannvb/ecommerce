package org.example.ecommerce.service;

import org.example.ecommerce.base.service.BaseService;
import org.example.ecommerce.entity.User;

public interface UserService extends BaseService<Long, User> {

    void addUser(User user);

}
