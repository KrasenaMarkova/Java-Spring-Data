package sofuni.exam.service.Impl;

import jakarta.xml.bind.JAXBException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import sofuni.exam.models.dto.MoonSeedDTO;
import sofuni.exam.models.dto.MoonSeedRootDTO;
import sofuni.exam.models.entity.Discoverer;
import sofuni.exam.models.entity.Moon;
import sofuni.exam.models.entity.Planet;
import sofuni.exam.models.enums.Type;
import sofuni.exam.repository.DiscovererRepository;
import sofuni.exam.repository.MoonRepository;
import sofuni.exam.repository.PlanetRepository;
import sofuni.exam.service.MoonService;
import sofuni.exam.service.PlanetService;
import sofuni.exam.util.ValidationUtil;
import sofuni.exam.util.XmlParser;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
public class MoonServiceImpl implements MoonService {

    private static final String FILE_PATH = "src/main/resources/files/xml/moons.xml";

    private final MoonRepository moonRepository;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final PlanetRepository planetRepository;
    private final DiscovererRepository discovererRepository;

    public MoonServiceImpl(MoonRepository moonRepository, XmlParser xmlParser, ModelMapper modelMapper, ValidationUtil validationUtil, PlanetRepository planetRepository, DiscovererRepository discovererRepository) {
        this.moonRepository = moonRepository;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.planetRepository = planetRepository;

        this.discovererRepository = discovererRepository;
    }

    @Override
    public boolean areImported() {
        return this.moonRepository.count() > 0;
    }

    @Override
    public String readMoonsFileContent() throws IOException {
        return Files.readString(Path.of(FILE_PATH));
    }

    @Override
    public String importMoons() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();

        MoonSeedRootDTO moonSeedRootDTO = xmlParser.fromFile(FILE_PATH, MoonSeedRootDTO.class);

        for (MoonSeedDTO moonSeedDTO : moonSeedRootDTO.getMoonSeedDTOList()) {

            if (this.moonRepository.findMoonByName(moonSeedDTO.getName()).isPresent() ||
                    !this.validationUtil.isValid(moonSeedDTO)) {
                sb.append("Invalid moon").append(System.lineSeparator());
                continue;
            }
            Moon moon = this.modelMapper.map(moonSeedDTO, Moon.class);

            Planet planet = planetRepository.findById(moonSeedDTO.getPlanet()).orElse(null);
            Discoverer discoverer = discovererRepository.findById(moonSeedDTO.getDiscoverer()).orElse(null);
            moon.setPlanet(planet);
            moon.setDiscoverer(discoverer);

            this.moonRepository.saveAndFlush(moon);

            sb.append(String.format("Successfully imported moon %s",
                            moonSeedDTO.getName()))
                    .append(System.lineSeparator());
        }
        return sb.toString().trim();
    }

    @Override
    public String exportMoons() {
        StringBuilder sb = new StringBuilder();

        List<Moon> foundMoons = this.moonRepository.findMoonsByPlanetTypeAndRadiusBetweenOrderByName(Type.GAS_GIANT, 700,2000);

        foundMoons.forEach(m -> {
            sb.append(String.format("""
                                    ***Moon %s is a natural satellite of %s and has a radius of %.2f km.
                                    ****Discovered by %s %s
                                    """,
                    m.getName(),
                    m.getPlanet().getName(),
                    m.getRadius(),
                    m.getDiscoverer().getFirstName(),
                    m.getDiscoverer().getLastName()))
                    .append(System.lineSeparator());

        });

        return sb.toString().trim();
    }
}
