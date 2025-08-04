package bg.softuni.playground.repository;

import bg.softuni.playground.entity.Shampoo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ShampooRepository extends JpaRepository<Shampoo, Long> {
    Shampoo findByBrand(String name);

    Shampoo findByPrice(BigDecimal price);

    void deleteByBrand(String brand);

    List<Shampoo> findAllByIngredients_Name(String shampooName);
}
