package fei.upce.st60982.pocketbook.Services.Interfaces;

import fei.upce.st60982.pocketbook.DataClasses.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserServiceInter {
    User findByUsername(String username);
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}

