package fei.upce.st60982.pocketbook.Controllers;

import fei.upce.st60982.pocketbook.DataClasses.User;
import fei.upce.st60982.pocketbook.Repositories.UserDAO;
import fei.upce.st60982.pocketbook.Security.LoginModel;
import fei.upce.st60982.pocketbook.Security.SignInData;
import fei.upce.st60982.pocketbook.Services.UserSingInService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin
@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserSingInService userService;
    private final UserDAO userRepository;

    @PostMapping("/authenticate")
    public LoginModel login(@RequestBody SignInData signInData){
        String token = userService.signIn(signInData.getUsername(),signInData.getPassword());
        User user = userRepository.findByUsername(signInData.getUsername());
        LoginModel lm = new LoginModel(token,user);
        return lm;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.OPTIONS, RequestMethod.GET, RequestMethod.POST})
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

    @GetMapping("/user/logged/{username}/{password}")
    public User getUserByCredentials(@PathVariable(value="Username") final String username, @PathVariable(value="password") final String password){
        List<User> users = userRepository.findAll();
        for (User user: users) {
            if(user.getUsername().equals(username) && user.getPassword() == password)
                return user;
        }
        return null;
    }

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @PostMapping(value="/register",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> addUser(@RequestBody User user){
        String hashedPassword = passwordEncoder().encode(user.getPassword());
        user.setPassword(hashedPassword);
        User x = userRepository.findByUsername(user.getUsername());
        if(x != null)
        {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
        User u = userRepository.save(user);
        if (u == null) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        } else {
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        }
    }

    @DeleteMapping(value={"/user/deleteUser/{id}"})
    public void deleteUser(@PathVariable(value="id") final long id){
        User u = userRepository.findUserById(id);
        if (u == null || u.isDeleted()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        u.setDeleted(true);
        u.setDeleted_date(LocalDateTime.now());
        userRepository.save(u);
    }

    @PutMapping(value={"/user/updateUser/{id}"})
    public void updateUser(@PathVariable(value="id") final long id, @Valid @RequestBody User user){
        User u = userRepository.findUserById(id);
        u.setUsername(user.getUsername());
        u.setPassword(user.getPassword());
        u.setActive(user.isActive());
        u.setDeleted(user.isDeleted());
        if(u.isDeleted())
            u.setDeleted_date(user.getDeleted_date());
        u.setCreation_date(user.getCreation_date());
        u.setUpdate_date(LocalDateTime.now());
        userRepository.save(u);
    }

    @GetMapping(value={"/user/changePass/{id}/{newPass}"})
    public boolean changePassword(@PathVariable(value="id") final long id, @PathVariable(value="newPass") final String newPassword){
        String hashedPassword = passwordEncoder().encode(newPassword);
        User u = userRepository.findUserById(id);
        u.setPassword(hashedPassword);
        User us = userRepository.save(u);
        if(us == null )
            return false;
        return true;
    }
}
