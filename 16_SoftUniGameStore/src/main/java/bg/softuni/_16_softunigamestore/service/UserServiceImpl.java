package bg.softuni._16_softunigamestore.service;

import bg.softuni._16_softunigamestore.dto.UserCreateDto;
import bg.softuni._16_softunigamestore.dto.UserLoginDto;
import bg.softuni._16_softunigamestore.entities.User;
import bg.softuni._16_softunigamestore.repositories.Userrepository;
import bg.softuni._16_softunigamestore.utils.ValidatorUtil;
import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    private final Userrepository userrepository;
    private final ModelMapper modelMapper;
    private final ValidatorUtil validatorUtil;

    private User user;

    @Autowired
    public UserServiceImpl(Userrepository userrepository, ModelMapper modelMapper, ValidatorUtil validatorUtil) {
        this.userrepository = userrepository;
        this.modelMapper = modelMapper;
        this.validatorUtil = validatorUtil;
    }

    @Override
    public String registerUser(UserCreateDto userCreateDto) {
        if (!userCreateDto.getPassword().equals(userCreateDto.getConfirmPassword())) {
            return "Password do not match";
        }

        if (!validatorUtil.isValid(userCreateDto)) {
            return validatorUtil.validate(userCreateDto)
                    .stream().map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining("\n"));
        }

        if (this.userrepository.findByEmail(userCreateDto.getEmail()).isPresent()) {
            return "Email address already in use";
        }

        User user = this.modelMapper.map(userCreateDto, User.class);
        setRootUserAdmin(user);
        this.userrepository.saveAndFlush(user);
        return String.format("%s was registered/n", user.getFullName());
    }

    @Override
    public String loginUser(UserLoginDto userLoginDto) {
        Optional<User> user = this.userrepository.findByEmailAndPassword(userLoginDto.getEmail(), userLoginDto.getPassword());

        if (user.isEmpty()) {
            return "Invalid email or password";
        }else {
            this.user = user.get();
        }
        return String.format("Successfully logged in: %s\n", getUser().getFullName());
    }

    private void setRootUserAdmin(User user) {
        if (this.userrepository.count() == 0) {
            user.setAdmin(true);
        }
    }

    public User getUser() {
        return this.user;
    }

    @Override
    public boolean isLoggedIn() {
        return this.user != null;
    }

    @Override
    public boolean isAdmin() {
        return this.isLoggedIn() && this.user.isAdmin();
    }

    @Override
    public String loginout() {
        if (this.isLoggedIn()) {
            String output = String.format("User %s successfully logged out", this.user.getFullName());
            this.user = null;
            return output;
        }
        return "No logged in user";
    }

    public void setUser(User user) {
        this.user = user;
    }
}
