package be.yapock.services.impl;

import be.yapock.models.entities.User_;
import be.yapock.repositories.UserRepository;
import be.yapock.services.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Crée un nouvel utilisateur en l'ajoutant au référentiel.
     *
     * @param user L'utilisateur à créer.
     * @return L'utilisateur créé.
     */
    @Override
    public User_ create(User_ user) {
        return userRepository.save(user);
    }

    /**
     * Récupère tous les utilisateurs présents dans le référentiel.
     *
     * @return Une liste de tous les utilisateurs.
     */
    public List<User_> getAll(){
        return userRepository.findAll();
    }

    /**
     * Récupère un utilisateur spécifique par son identifiant.
     *
     * @param id L'identifiant de l'utilisateur à récupérer.
     * @return L'utilisateur correspondant à l'identifiant.
     * @throws NoSuchElementException si l'utilisateur n'est pas trouvé.
     */
    @Override
    public User_ getOne(Long id) {
        return userRepository.findById(id).orElseThrow();
    }
}
