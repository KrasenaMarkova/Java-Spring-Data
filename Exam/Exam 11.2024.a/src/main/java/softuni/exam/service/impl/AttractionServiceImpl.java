package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.AttractionImportDto;
import softuni.exam.models.entity.Attraction;
import softuni.exam.models.entity.Country;
import softuni.exam.repository.AttractionRepository;
import softuni.exam.repository.CountryRepository;
import softuni.exam.service.AttractionService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;

//ToDo - Implement all the methods
@Service
public class AttractionServiceImpl implements AttractionService {

    private static final String FILE_PATH = "src/main/resources/files/json/attractions.json";

    private final AttractionRepository attractionRepository;
    private final CountryRepository countryRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public AttractionServiceImpl(AttractionRepository attractionRepository, CountryRepository countryRepository, Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.attractionRepository = attractionRepository;
        this.countryRepository = countryRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.attractionRepository.count() > 0;
    }

    @Override
    public String readAttractionsFileContent() throws IOException {
        return Files.readString(Path.of(FILE_PATH));
    }

    @Override
    public String importAttractions() throws IOException {
        StringBuilder sb = new StringBuilder();

        AttractionImportDto[] attractionImportDtos = this.gson.fromJson(readAttractionsFileContent(),
                                                                AttractionImportDto[].class);

        for (AttractionImportDto importDto : attractionImportDtos) {
            if (
                    this.attractionRepository.findByName(importDto.getName()).isPresent() ||
                    !this.validationUtil.isValid(importDto)) {
                sb.append("Invalid attraction").append(System.lineSeparator());
                continue;
            }
            Attraction attraction = this.modelMapper.map(importDto, Attraction.class);

            Country country = countryRepository.findById(importDto.getCountry())
                    .orElse(null);
            attraction.setCountry(country);
            this.attractionRepository.save(attraction);

            sb.append(String.format("Successfully imported attraction %s",
                            importDto.getName()))
                    .append(System.lineSeparator());
        }

        return sb.toString().trim();
    }

    @Override
    public String exportAttractions() {
        StringBuilder sb = new StringBuilder();
        Set<Attraction> attractions = attractionRepository.findAllAttractions();

        this.attractionRepository.findAllAttractionss()
                        .forEach(a -> {

       // attractions.forEach(a -> {
            sb.append(String.format("Attraction with ID%d:\n" +
                                    "***%s - %s at an altitude of %dm. somewhere in %s.",
                            a.getId(), a.getName(), a.getDescription(), a.getElevation(), a.getCountry().getName()))
                    .append(System.lineSeparator());
        });
//}:
//***{attraction name} - {attraction description} at an altitude of {attraction elevation}m. somewhere in {attraction country}.
        return sb.toString();
    }
}
