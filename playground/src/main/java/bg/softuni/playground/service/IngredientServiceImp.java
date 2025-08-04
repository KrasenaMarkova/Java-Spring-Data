package bg.softuni.playground.service;

import bg.softuni.playground.dto.IngredientDto;
import bg.softuni.playground.entity.Ingredient;
import bg.softuni.playground.repository.IngredientRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IngredientServiceImp implements IngredientService{

    private final IngredientRepository ingredientRepository;
    private final ModelMapper modelMapper;

    @Override
    public IngredientDto create(IngredientDto ingredientDto) {
        Ingredient ingredient = modelMapper.map(ingredientDto, Ingredient.class);
        return modelMapper.map(ingredientRepository.save(ingredient), IngredientDto.class);
    }

    @Override
    public IngredientDto getByIngredientName(String ingredientName) {
        Ingredient ingredient = ingredientRepository.findByName(ingredientName);
        return modelMapper.map(ingredient, IngredientDto.class);
    }

    @Override
    public IngredientDto deleteByIngredientName(String ingredientName) {
        Ingredient ingredient1 = ingredientRepository.deleteByName(ingredientName);
        return  modelMapper.map(ingredient1, IngredientDto.class);
    }

    @Override
    public IngredientDto getById(long id) {
        Optional<Ingredient> ingredient = ingredientRepository.findById(id);
        return modelMapper.map(ingredient,IngredientDto.class);
    }
}
