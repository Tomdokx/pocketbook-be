package fei.upce.st60982.pocketbook.Security;

import fei.upce.st60982.pocketbook.DataClasses.User;
import lombok.*;

@Setter
@Getter
@Data
public class LoginModel {
    private String token;
    private User loggedInUser;

    public LoginModel(){}

    public LoginModel(String token, User loggedInUser) {
        this.token = token;
        this.loggedInUser = loggedInUser;
    }
}
