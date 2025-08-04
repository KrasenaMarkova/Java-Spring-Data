package softuni.exam.service.impl;

import jakarta.xml.bind.JAXBException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.VisitorImportDto;
import softuni.exam.models.dto.VisitorImportRootDTO;
import softuni.exam.models.entity.Visitor;
import softuni.exam.repository.VisitorRepository;
import softuni.exam.service.VisitorService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class VisitorServiceImpl implements VisitorService {

    private static final String FILE_PATH = "src/main/resources/files/xml/visitors.xml";

    private final VisitorRepository visitorRepository;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public VisitorServiceImpl(VisitorRepository visitorRepository, XmlParser xmlParser, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.visitorRepository = visitorRepository;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.visitorRepository.count() > 0;
    }

    @Override
    public String readVisitorsFileContent() throws IOException {
        return Files.readString(Path.of(FILE_PATH));
    }

    @Override
    public String importVisitors() throws JAXBException {
        StringBuilder sb = new StringBuilder();

        VisitorImportRootDTO visitorImportRootDTO = this.xmlParser.fromFile(FILE_PATH, VisitorImportRootDTO.class);
        //    if (this.volcanologistRepository.findByFirstNameAndLastName(volcanologistImportDto.getFirstName(),
        //                                                                volcanologistImportDto.getLastName()).isPresent() ||
        //                this.volcanoRepository.findById((long) volcanologistImportDto.getExploringVolcanoId()).isEmpty() ||
        //                    !this.validationUtil.isValid(volcanologistImportDto))
        for(VisitorImportDto visitorImportDto : visitorImportRootDTO.getVisitorImportDtos()) {
            if((this.visitorRepository.findByFirstName(visitorImportDto.getFirstName()).isPresent() &&
                    this.visitorRepository.findByLastName(visitorImportDto.getLastName()).isPresent()) ||
                    this.visitorRepository.findByPersonalData(visitorImportDto.getPersonalData()).isPresent() ||
                    !this.validationUtil.isValid(visitorImportDto)) {

                sb.append("Invalid visitor").append(System.lineSeparator());
                continue;
            }

            Visitor visitor = this.modelMapper.map(visitorImportDto, Visitor.class);
//

        }



        return sb.toString().trim();
    }
}
