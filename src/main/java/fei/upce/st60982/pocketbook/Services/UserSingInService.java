package fei.upce.st60982.pocketbook.Services;

import fei.upce.st60982.pocketbook.Repositories.UserDAO;
import fei.upce.st60982.pocketbook.Security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserSingInService {

    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;

    @Autowired
    public UserSingInService(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    public String signIn(String username, String password){
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        return jwtUtil.generateToken((UserDetails) authenticate.getPrincipal());
    }
}
