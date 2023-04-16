package fei.upce.st60982.pocketbook.Controllers;

import fei.upce.st60982.pocketbook.DataClasses.User;
import fei.upce.st60982.pocketbook.Repositories.UserDAO;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true,level= AccessLevel.PRIVATE)
@RestController
public class UserController {

    private UserDAO userRepository;

    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello World!";
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        List<User> l = userRepository.findAll();
        return l.stream().filter(p -> !p.isDeleted()).toList();
    }

    @GetMapping("/users/all")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable(value="id") final long id){
        User user = userRepository.findUserById(id);
        if(user == null || user.isDeleted()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        return user;
    }

    @GetMapping("/user/addUser")
    public void addUser(@RequestBody User user){
        userRepository.save(user);
    }

    @GetMapping(value={"/user/deleteUser/{id}"})
    public void deleteUser(@PathVariable(value="id") final long id){
        User u = userRepository.findUserById(id);
        u.setDeleted(true);
        u.setDeleted_date(LocalDate.now());
        userRepository.save(u);
    }

    @GetMapping(value={"/user/updateUser/{id}"})
    public void registerUserCredential(@PathVariable(value="id") final long id, @Valid @RequestBody User user){
        User u = userRepository.findUserById(id);
        u.setUsername(user.getUsername());
        u.setPassword(user.getPassword());
        u.setActive(user.isActive());
        u.setDeleted(user.isDeleted());
        if(u.isDeleted())
            u.setDeleted_date(user.getDeleted_date());
        u.setCreation_date(user.getCreation_date());
        u.setUpdate_date(user.getUpdate_date());
        userRepository.save(u);
    }

}
