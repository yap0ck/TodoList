package be.yapock.repositories;

import be.yapock.models.entities.User_;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User_, Long> {

}