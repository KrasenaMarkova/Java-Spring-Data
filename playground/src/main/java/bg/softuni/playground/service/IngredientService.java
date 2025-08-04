package bg.softuni.playground.service;

import bg.softuni.playground.dto.IngredientDto;

public interface IngredientService {

    IngredientDto create(IngredientDto ingredientDto);

    IngredientDto getByIngredientName(String ingredientName);

    IngredientDto deleteByIngredientName(String ingredientName);

    IngredientDto getById(long id);
}
