package be.yapock.services;

import be.yapock.models.entities.User_;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User_ create(User_ user);

    List<User_> getAll();
    User_ getOne(Long id);
}
