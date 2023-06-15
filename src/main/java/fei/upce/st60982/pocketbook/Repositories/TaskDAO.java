package fei.upce.st60982.pocketbook.Repositories;

import fei.upce.st60982.pocketbook.DataClasses.Task;
import fei.upce.st60982.pocketbook.DataClasses.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskDAO extends JpaRepository<Task, Integer> {
    Task findTaskById(int id);
    List<Task> findTasksByAuthor(User user);
}
