package com.example.advquerying.repositories;

import com.example.advquerying.entities.Label;
import com.example.advquerying.entities.Shampoo;
import com.example.advquerying.entities.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ShampooRepository extends JpaRepository<Shampoo, Long> {
    List<Shampoo> findByBrand(String brand);

    List<Shampoo> findByBrandAndSize(String brand, Size size);

    // 1.	Select Shampoos by Size
    //Create a method that selects all shampoos with a given size, ordered by shampoo id.
    // добавяме в Runner
    List<Shampoo> findBySizeOrderByIdAsc(Size size);

    //2.	Select Shampoos by Size or Label
    //Create a method that selects all shampoos with a given size or label id.
    // Sort the result ascending by price.
    // добавяме в Runner
    // вариант 1
    List<Shampoo> findBySizeOrLabel(Size size, Label label);

    //вариант 2
    List<Shampoo> findBySizeOrLabelId(Size size, long labelId);

//    3.	Select Shampoos by Price
//    Create a method that selects all shampoos, which price is higher than a given price.
//    Sort the result descending by price.
    List<Shampoo> findByPriceGreaterThanOrderByPriceDesc(BigDecimal price);

//   6.	Count Shampoos by Price
//    Create a method that counts all shampoos with price lower than a given price.
    int countByPriceLessThan(BigDecimal price);

//    JPQL Querying
//    7.	Select Shampoos by Ingredients
//    Create a method that selects all shampoos with ingredients included in a given list.
    List<Shampoo> findByIngredientsNameIn(List<String> names);



//        @Query("SELECT s FROM Shampoo AS s" +
//            " JOIN s.ingredients AS i" +
//            " WHERE i.name IN :names")
//        @Query("""
//        SELECT s FROM Shampoo AS s
//        JOIN s.ingredients AS i
//        WHERE i.name IN :names
//        """)
List<Shampoo> findByIngredientsNameInQuery(@Param("names") List<String> ingNames);

//    JPQL Querying
//    8.	Select Shampoos by Ingredients Count
//    Create a method that selects all shampoos with ingredients less than a given number.
        @Query("SELECT s FROM Shampoo AS s" +
        " WHERE SIZE(s.ingredients) < :count")
//    @Query(value = "SELECT * FROM shampoos s JOIN shampoos_ingredients...", nativeQuery = true)
List<Shampoo> findByIngredientsCountLessThan(int count);



    @Query(value = "SELECT * FROM shampoos WHERE brand = :brand", nativeQuery = true)
    List<Shampoo> findByBrandNative(String brand);
}
