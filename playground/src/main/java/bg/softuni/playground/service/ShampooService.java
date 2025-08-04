package bg.softuni.playground.service;

import bg.softuni.playground.dto.IngredientDto;
import bg.softuni.playground.dto.ShampooDto;

import java.util.List;

public interface ShampooService {

    ShampooDto createShampoo(ShampooDto shampooDto);

    ShampooDto getShampooById(Long id);

    IngredientDto findIngredientByShampooName(String shampooName);

    List<ShampooDto> getAllShampoos();

}
