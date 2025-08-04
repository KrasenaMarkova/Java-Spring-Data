package bg.softuni._11_springdata;

import bg.softuni._11_springdata.entitties.Account;
import bg.softuni._11_springdata.entitties.User;
import bg.softuni._11_springdata.userService.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class MainRunner implements CommandLineRunner {

    private UserService userService;

    public MainRunner(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
//        Account account =  new Account(BigDecimal.valueOf(2000));
//        User user = new User("main", 22, List.of(account));
//
//        userService.register(user);


//        User user = userService.get(1);
//
//        userService.addAccountWithBalance(user, 1200);


        User user = userService.getByAccount(2);
    }

}
