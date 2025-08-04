package sofuni.exam.service.Impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import sofuni.exam.models.dto.PlanetSeedDTO;
import sofuni.exam.models.entity.Planet;
import sofuni.exam.repository.PlanetRepository;
import sofuni.exam.service.PlanetService;
import sofuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class PlanetServiceImpl implements PlanetService {

    private static final String FILE_PATH = "src/main/resources/files/json/planets.json";

    private final PlanetRepository planetRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public PlanetServiceImpl(PlanetRepository planetRepository, Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.planetRepository = planetRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.planetRepository.count() > 0;
    }

    @Override
    public String readPlanetsFileContent() throws IOException {
        return Files.readString(Path.of(FILE_PATH));
    }

    @Override
    public String importPlanets() throws IOException {
        StringBuilder sb = new StringBuilder();

        PlanetSeedDTO[] planetSeedDTOS = this.gson.fromJson(readPlanetsFileContent(),
                PlanetSeedDTO[].class);

        for (PlanetSeedDTO planetSeedDTO : planetSeedDTOS) {

            if (planetRepository.findPlanetByName(planetSeedDTO.getName()).isPresent() ||
                    !this.validationUtil.isValid(planetSeedDTO)) {
                sb.append("Invalid planet").append(System.lineSeparator());
                continue;
            }

            Planet planet = this.modelMapper.map(planetSeedDTO, Planet.class);
            this.planetRepository.saveAndFlush(planet);
            sb.append(String.format("Successfully imported planet %s",
                            planetSeedDTO.getName()))
                    .append(System.lineSeparator());
        }
            return sb.toString();
    }
}
