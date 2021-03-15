package com.egorrridze.core.services;

import com.egorrridze.core.models.User;
import com.egorrridze.core.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;

@Service
@RequiredArgsConstructor
@Entity
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUser(Long id) {
        return userRepository.findById(id).get();
    }
}
