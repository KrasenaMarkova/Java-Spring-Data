package bg.softuni.playground.repository;

import bg.softuni.playground.entity.Ingredient;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    Ingredient findByName(String name);

    // ???
    @Transactional
    Ingredient deleteByName(String name);

}
