package bg.softuni._11_springdata.entitties;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private BigDecimal balance;

    @OneToMany
    private List<Account> accounts;

    public Account() {
        this.accounts = new ArrayList<>();
    }

    public Account(BigDecimal balance) {
        this.balance = balance;
    }
}
