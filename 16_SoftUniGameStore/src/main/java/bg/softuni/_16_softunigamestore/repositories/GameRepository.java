package bg.softuni._16_softunigamestore.repositories;

import bg.softuni._16_softunigamestore.dto.GameDetailDto;
import bg.softuni._16_softunigamestore.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {
    Optional<Game> findByTitle(String title);
}
