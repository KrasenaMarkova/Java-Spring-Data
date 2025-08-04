package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.SellerImportDto;
import softuni.exam.models.entity.Seller;
import softuni.exam.repository.SellerRepository;
import softuni.exam.service.SellerService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class SellerServiceImpl implements SellerService {

    private static final String FILE_PATH = "src/main/resources/files/json/sellers.json";

    private final SellerRepository sellerRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public SellerServiceImpl(SellerRepository sellerRepository, Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.sellerRepository = sellerRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.sellerRepository.count() > 0;
    }

    @Override
    public String readSellersFromFile() throws IOException {
        return Files.readString(Path.of(FILE_PATH));
    }

    @Override
    public String importSellers() throws IOException {
        StringBuilder sb = new StringBuilder();
        SellerImportDto[] sellerImportDtos = this.gson.fromJson(readSellersFromFile(),
                                                SellerImportDto[].class);

        for (SellerImportDto sellerImportDto : sellerImportDtos) {
            if (this.sellerRepository.findByFirstName(sellerImportDto.getFirstName()).isPresent() ||
                    this.sellerRepository.findByLastName(sellerImportDto.getLastName()).isPresent() ||
               !this.validationUtil.isValid(sellerImportDto)) {
                sb.append("Invalid seller").append(System.lineSeparator());
                continue;
            }

            Seller seller = this.modelMapper.map(sellerImportDto, Seller.class);
            this.sellerRepository.saveAndFlush(seller);

            sb.append(String.format("Successfully imported seller %s %s",
                    sellerImportDto.getFirstName(), sellerImportDto.getLastName()))
                    .append(System.lineSeparator());
        }

        return sb.toString();
    }
}
