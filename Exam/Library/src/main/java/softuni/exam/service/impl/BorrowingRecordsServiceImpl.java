package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.BorrowingRecordsImportDto;
import softuni.exam.models.dto.BorrowingRecordsImportRootDto;
import softuni.exam.models.entity.BorrowingRecord;
import softuni.exam.models.entity.LibraryMember;
import softuni.exam.models.entity.enums.Genre;
import softuni.exam.repository.BookRepository;
import softuni.exam.repository.BorrowingRecordRepository;
import softuni.exam.repository.LibraryMemberRepository;
import softuni.exam.service.BorrowingRecordsService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class BorrowingRecordsServiceImpl implements BorrowingRecordsService {

    private static final String FILE_PATH = "src/main/resources/files/xml/borrowing-records.xml";

    private final BorrowingRecordRepository borrowingRecordRepository;
    private final LibraryMemberRepository libraryMemberRepository;
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;

    public BorrowingRecordsServiceImpl(BorrowingRecordRepository borrowingRecordRepository,
                                       LibraryMemberRepository libraryMemberRepository,
                                       BookRepository bookRepository,
                                       ModelMapper modelMapper,
                                       XmlParser xmlParser,
                                       ValidationUtil validationUtil) {
        this.borrowingRecordRepository = borrowingRecordRepository;
        this.libraryMemberRepository = libraryMemberRepository;
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.borrowingRecordRepository.count() > 0;
    }

    @Override
    public String readBorrowingRecordsFromFile() throws IOException {
        return Files.readString(Path.of(FILE_PATH));
    }

    @Override
    public String importBorrowingRecords() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();

        BorrowingRecordsImportRootDto borrowingRecordsImportRootDto =
                                             this.xmlParser.fromFile(FILE_PATH,
                                                BorrowingRecordsImportRootDto.class);

        for(BorrowingRecordsImportDto borrowingRecordsImportDto : borrowingRecordsImportRootDto.getBorrowingRecordSeedDtos()) {
            if (this.bookRepository.findByTitle(borrowingRecordsImportDto.getBook().getTitle()).isEmpty() ||
                    !this.validationUtil.isValid(borrowingRecordsImportDto)) {
                //?????
                Optional<LibraryMember> byId = this.libraryMemberRepository.findById((long)borrowingRecordsImportDto.getMemberId());
                if (byId.isPresent()) {
                    sb.append("Invalid borrowing record").append(System.lineSeparator());
                    continue;
                }
                sb.append("Invalid borrowing record").append(System.lineSeparator());
                continue;
            }

            BorrowingRecord borrowingRecord = this.modelMapper.map(borrowingRecordsImportDto, BorrowingRecord.class);
                    this.borrowingRecordRepository.saveAndFlush(borrowingRecord);
                    sb.append(String.format("Successfully imported borrowing record %s - %s",
                                        borrowingRecordsImportDto.getBook().getTitle(),
                                         borrowingRecordsImportDto.getBorrowDate()))
                            .append(System.lineSeparator());
        }

        return sb.toString();
    }

    @Override
    public String exportBorrowingRecords() {
        StringBuilder sb = new StringBuilder();

        this.borrowingRecordRepository.findAllByBorrowDateBeforeAndBook_GenreOrderByBorrowDateDesc
                        (LocalDate.parse("2021-09-10"), Genre.SCIENCE_FICTION)
                .forEach(b -> {
                    sb.append(String.format("Book title: %s\n" +
                            "*Book author: %s\n" +
                            "**Date borrowed: %s\n" +
                            "***Borrowed by: %s %s",
                            b.getBook().getTitle(),
                            b.getBook().getAuthor(),
                            b.getBorrowDate(),
                            b.getLibraryMember().getFirstName(),
                            b.getLibraryMember().getLastName()))
                            .append(System.lineSeparator());
                });

        return sb.toString().trim();
    }
}
