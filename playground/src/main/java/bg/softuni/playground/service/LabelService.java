package bg.softuni.playground.service;

import bg.softuni.playground.dto.LabelDto;

import java.util.List;

public interface LabelService {

    LabelDto create(LabelDto dto);

    LabelDto getById(Long id);

    LabelDto getByTitle(String title);

    List<LabelDto> getAllBySubTitle(String subTitle);
}
