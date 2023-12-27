package org.example.ecommerce.repository;

import org.example.ecommerce.base.repository.BaseRepository;
import org.example.ecommerce.entity.User;

import java.util.Optional;

public interface UserRepository extends BaseRepository<Long , User> {

    Optional<User> findUserByEmailAndPassword(String email, String password);

}
