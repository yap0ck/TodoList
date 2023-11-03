package be.yapock.dal.repositories;

import be.yapock.dal.models.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {

    List<Task> findByIsFinishedFalse();

    List<Task> findByIsFinishedTrue();
    @Modifying
    @Query ("DELETE FROM Task WHERE isFinished = true AND category.user.login = :value")
    void deleteTaskByIsFinishedTrue(@Param("value") String login);

}
