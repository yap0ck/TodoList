package be.yapock.bll.services;

import be.yapock.dal.models.entities.User_;

import java.util.List;

public interface UserService {
    User_ create(User_ user);

    List<User_> getAll();
    User_ getOne(Long id);
}
