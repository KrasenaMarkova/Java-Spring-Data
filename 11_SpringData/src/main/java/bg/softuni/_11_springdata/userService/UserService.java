package bg.softuni._11_springdata.userService;

import bg.softuni._11_springdata.entitties.User;

public interface UserService {
    void register(User user);

    User get(int id);

    void addAccountWithBalance(User user, int balance);

    User getByAccount(int accountId);
}
