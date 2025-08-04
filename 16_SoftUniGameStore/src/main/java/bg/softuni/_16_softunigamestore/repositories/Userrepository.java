package bg.softuni._16_softunigamestore.repositories;

import bg.softuni._16_softunigamestore.entities.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Userrepository extends JpaRepository<User, Integer> {

   Optional<User> findByEmail(String email);

    Optional<User> findByEmailAndPassword(String email, String password);
}
