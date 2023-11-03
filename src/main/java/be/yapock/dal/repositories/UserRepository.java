package be.yapock.dal.repositories;

import be.yapock.dal.models.entities.User_;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User_, Long> {

}