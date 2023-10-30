package be.yapock.services.impl;

import be.yapock.models.entities.User_;
import be.yapock.repositories.UserRepository;
import be.yapock.services.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User_ create(User_ user) {
        return userRepository.save(user);
    }

    public List<User_> getAll(){
        return userRepository.findAll();
    }

    @Override
    public User_ getOne(Long id) {
        return userRepository.findById(id).orElseThrow();
    }
}
