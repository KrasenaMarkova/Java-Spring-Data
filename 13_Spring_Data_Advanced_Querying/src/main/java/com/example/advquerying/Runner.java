package com.example.advquerying;

import com.example.advquerying.entities.Ingredient;
import com.example.advquerying.entities.Label;
import com.example.advquerying.entities.Shampoo;
import com.example.advquerying.entities.Size;
import com.example.advquerying.repositories.CustomRepo;
import com.example.advquerying.repositories.IngredientRepository;
import com.example.advquerying.repositories.LabelRepository;
import com.example.advquerying.repositories.ShampooRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
public class Runner implements CommandLineRunner {

    private final ShampooRepository shampooRepository;
    private final LabelRepository labelRepository;
    private final IngredientRepository ingredientRepository;
    private final CustomRepo customRepo;

    public Runner(ShampooRepository shampooRepository, LabelRepository labelRepository,
                  IngredientRepository ingredientRepository, CustomRepo customRepo) {
        this.shampooRepository = shampooRepository;
        this.labelRepository = labelRepository;
        this.ingredientRepository = ingredientRepository;
        this.customRepo = customRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Working");

//        Optional<Shampoo> byId = shampooRepository.findById(1L);
//        System.out.println(byId.get().getId());

//        List<Shampoo> byBrand = shampooRepository.findByBrand("Swiss Green Apple & Nettle");
//        System.out.println(byBrand);

//        List<Shampoo> byBrand = shampooRepository.findByBrandAndSize(
//                    "Swiss Green Apple & Nettle", Size.MEDIUM);
//        System.out.println(byBrand);

//        1.	Select Shampoos by Size
//        List<Shampoo> byBrand = shampooRepository.findBySizeOrderByIdAsc(Size.MEDIUM);
//        System.out.println(byBrand);

//        2.	Select Shampoos by Size or Label
//        Create a method that selects all shampoos with a given size or label id.
//        Sort the result ascending by price.
//        вариант 1
//        Optional<Label> label = labelRepository.findById(4L);
//        List<Shampoo>byBrand = shampooRepository.findBySizeOrLabel(Size.MEDIUM, label.get());
//
//        вариант 2
//        List<Shampoo>byBrand = shampooRepository.findBySizeOrLabelId(Size.MEDIUM, 4L);
//        System.out.println(byBrand);

//            3.	Select Shampoos by Price
//        List<Shampoo> shampoos = shampooRepository.findByPriceGreaterThanOrderByPriceDesc(BigDecimal.TEN);
//            System.out.println(shampoos);

//        4.	Select Ingredients by Name
//        List<Ingredient> ingredient = ingredientRepository.findByNameStartingWith("M");
//        System.out.println(ingredient);

//        5.	Select Ingredients by Names
//        List<Ingredient> ingredient = ingredientRepository.findByNameInOrderByAsc(
//                List.of("Lavender", "Herbs", "Apple"));
//        System.out.println(ingredient);

//        6.	Count Shampoos by Price
//       int count = shampooRepository.countByPriceLessThan(BigDecimal.valueOf(8.50));
//        System.out.println(count);

//        7.	Select Shampoos by Ingredients
//        List<Shampoo> shampoos = shampooRepository.findByIngredientsNameIn(List.of("Berry", "Mineral-Collagen"));
//        System.out.println(shampoos);


//        List<Shampoo>shampoos = shampooRepository.findByIngredientsNameInQuery(List.of("Berry", "Mineral-Collagen"));
//        System.out.println(shampoos);


//       8.	Select Shampoos by Ingredients Count
//        List<Shampoo>shampoos = shampooRepository.findByIngredientsCountLessThan(2);
//        System.out.println(shampoos);

//        Shampoo firstShampoo = customRepo.getFirstShampoo();
//        System.out.println(firstShampoo);

//        9. Delete Ingredients by Name
//        ingredientRepository.deleteByName("Lavender");

        Optional<Shampoo> shampoo = shampooRepository.findById(1L);
        // @Transactional if this is a LAZY collection
        Set<Ingredient> ingredients = shampoo.get().getIngredients();

        System.out.println(ingredients.size());

//    11.	Update Ingredients by Names
//        ingredientRepository.updateIngredientsPricesForNames(List.of("Lavender"));
    }
}
