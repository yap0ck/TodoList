package be.yapock.services;

import be.yapock.models.entities.User_;

import java.util.List;

public interface UserService {
    User_ create(User_ user);

    List<User_> getAll();
}
