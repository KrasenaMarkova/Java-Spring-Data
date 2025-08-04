package bg.softuni._16_softunigamestore.service;

import bg.softuni._16_softunigamestore.dto.UserCreateDto;
import bg.softuni._16_softunigamestore.dto.UserLoginDto;
import bg.softuni._16_softunigamestore.entities.User;

public interface UserService {
    String registerUser(UserCreateDto userCreateDto);

    String loginUser(UserLoginDto userLoginDto);

    User getUser();

    boolean isLoggedIn();

    boolean isAdmin();

    String loginout();
}
