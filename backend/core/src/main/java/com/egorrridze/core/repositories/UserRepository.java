package com.egorrridze.core.repositories;

import com.egorrridze.core.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    /*  @Override
    default Optional<User> findById(Long aLong) {
        return Optional.empty();
    };*/
}
