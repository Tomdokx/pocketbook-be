package fei.upce.st60982.pocketbook.Repositories;

import fei.upce.st60982.pocketbook.DataClasses.Task;
import fei.upce.st60982.pocketbook.DataClasses.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskDAO extends JpaRepository<Task, Long> {
    Task findTaskById(long id);
    List<Task> findTasksByAuthor(User user);
}
