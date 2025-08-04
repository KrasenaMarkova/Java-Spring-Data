package bg.softuni._11_springdata.userService;

import bg.softuni._11_springdata.entitties.Account;
import bg.softuni._11_springdata.entitties.User;
import bg.softuni._11_springdata.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void register(User user) {
        userRepository.save(user);
    }

    @Override
    public User get(int id) {
        Optional<User> byId = userRepository.findById(id);

        return byId.orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public void addAccountWithBalance(User user, int balance) {
        // check if user is persisted?
        user.getAccounts().add(new Account(BigDecimal.valueOf(1200)));

        userRepository.save(user);
    }

    @Override
    public User getByAccount(int accountId) {
        return userRepository.findByAccountsId(accountId)
                .orElseThrow(EntityNotFoundException::new);
    }
}
