package bg.softuni.playground.service;

import bg.softuni.playground.dto.LabelDto;
import bg.softuni.playground.entity.Label;
import bg.softuni.playground.repository.LabelRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LabelServiceImpl implements LabelService{

    private final LabelRepository repository;
    private final ModelMapper modelMapper;

    @Override
    public LabelDto create(LabelDto dto) {
        Label label = modelMapper.map(dto, Label.class);
        return modelMapper.map(repository.save(label), LabelDto.class);
    }

    @Override
    public LabelDto getById(Long id) {
        Label label = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("label with id = " + id + " not found!"));
        return modelMapper.map(label, LabelDto.class);
    }

    @Override
    public LabelDto getByTitle(String title) {
        return modelMapper.map(repository.findByTitle(title), LabelDto.class);
    }

    @Override
    public List<LabelDto> getAllBySubTitle(String subTitle) {
        List<Label> labelEntities = repository.findAllBySubTitleOrderByIdDesc(subTitle);
        return labelEntities.stream()
                .map(label -> modelMapper.map(label, LabelDto.class))
                .toList();
    }
}
