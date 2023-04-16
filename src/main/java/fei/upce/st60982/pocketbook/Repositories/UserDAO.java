package fei.upce.st60982.pocketbook.Repositories;

import fei.upce.st60982.pocketbook.DataClasses.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findUserById(long id);
}
