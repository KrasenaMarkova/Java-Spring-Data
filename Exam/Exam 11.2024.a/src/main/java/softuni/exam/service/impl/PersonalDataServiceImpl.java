package softuni.exam.service.impl;

import jakarta.xml.bind.JAXBException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.PersonalDataImportDto;
import softuni.exam.models.dto.PersonalDataImportRootDto;
import softuni.exam.models.entity.PersonalData;
import softuni.exam.repository.PersonalDataRepository;
import softuni.exam.service.PersonalDataService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class PersonalDataServiceImpl implements PersonalDataService {

    private static final String FILE_PATH = "src/main/resources/files/xml/personal_data.xml";

    private final PersonalDataRepository personalDataRepository;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public PersonalDataServiceImpl(PersonalDataRepository personalDataRepository, XmlParser xmlParser, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.personalDataRepository = personalDataRepository;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.personalDataRepository.count() > 0;
    }

    @Override
    public String readPersonalDataFileContent() throws IOException {
        return Files.readString(Path.of(FILE_PATH));
    }

    @Override
    public String importPersonalData() throws JAXBException {
        StringBuilder sb = new StringBuilder();

        PersonalDataImportRootDto importRootDto = this.xmlParser.fromFile(FILE_PATH, PersonalDataImportRootDto.class);

        for (PersonalDataImportDto importDto : importRootDto.getPersonalDataImportDtos()) {
            if (this.personalDataRepository.findByCardNumber(importDto.getCardNumber()).isPresent()) {
                sb.append("Invalid personal data").append(System.lineSeparator());
                continue;
            }
            PersonalData personalData = this.modelMapper.map(importDto, PersonalData.class);
            this.personalDataRepository.save(personalData);
            sb.append(String.format("Successfully imported personal data for visitor with card number %s",
                    importDto.getCardNumber())).append(System.lineSeparator());
        }

        return sb.toString().trim();
    }
}
