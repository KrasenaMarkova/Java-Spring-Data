package com.example.advquerying.repositories;

import com.example.advquerying.entities.Ingredient;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

//        4.	Select Ingredients by Name
//    Create a method that selects all ingredients, which name starts with given letters.
    List<Ingredient> findByNameStartingWith(String name);

//    5.	Select Ingredients by Names
//    Create a method that selects all ingredients, which are contained in a given list.
//    Sort the result ascending by price.
    List<Ingredient> findByNameInOrderByAsc(List<String> wanted);

    //9.	Delete Ingredients by Name
    //Create a method that deletes ingredients by a given name. Use named query.
    @Transactional
    void deleteByName(String name);

//    10.	Update Ingredients by Price
//    Create a method that increases the price of all ingredients by 10%. Use named query.

    @Query("UPDATE Ingredient i SET i.price = i.price * 1.1")
    @Modifying
    @Transactional
    void updateIngredientPriceBy10Percent();

    //11.	Update Ingredients by Names
    //Create a method that updates the price of all ingredients, which names are in a given list
    @Query("UPDATE Ingredient i SET i.price = i.price * 1.1 WHERE i.name IN :names")
    @Modifying
    @Transactional
    void updateIngredientsPricesForNames(List<String> names);
}
