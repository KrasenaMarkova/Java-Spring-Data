package bg.softuni.playground.service;

import bg.softuni.playground.dto.IngredientDto;
import bg.softuni.playground.dto.ShampooDto;
import bg.softuni.playground.entity.Label;
import bg.softuni.playground.entity.Shampoo;
import bg.softuni.playground.repository.LabelRepository;
import bg.softuni.playground.repository.ShampooRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class ShampooServiceImp implements ShampooService{
    private final ShampooRepository shampooRepository;
    private final LabelRepository labelRepository;
    private final ModelMapper modelMapper;

    @Override
    public ShampooDto createShampoo(ShampooDto shampooDto) {
        Shampoo shampoo = modelMapper.map(shampooDto, Shampoo.class);
        Label labelEntity = labelRepository.findById(shampooDto.getLabel().getId())
                .orElseThrow(() -> new RuntimeException("missing label with ID = " + shampooDto.getLabel().getId()));
        shampoo.setLabel(null);
        shampooRepository.save(shampoo);
        if (isNull(labelEntity.getShampoos())) {
            labelEntity.setShampoos(new ArrayList<>());
        }
        labelEntity.getShampoos().add(shampoo);
        labelRepository.save(labelEntity);
        shampoo.setLabel(labelEntity);
        return modelMapper.map(shampooRepository.save(shampoo), ShampooDto.class);
    }

    @Override
    public ShampooDto getShampooById(Long id) {
        Shampoo shampoo = shampooRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Shampoo with id = " + id + " not found!"));
        return modelMapper.map(shampoo, ShampooDto.class);
    }

    @Override
    public IngredientDto findIngredientByShampooName(String shampooName) {
      return null;
    }

    @Override
    public List<ShampooDto> getAllShampoos() {
        List<Shampoo> result = shampooRepository.findAll();
        return List.of((ShampooDto) result);
    }
}
