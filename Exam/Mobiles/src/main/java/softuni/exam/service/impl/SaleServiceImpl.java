package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.SaleImportDto;
import softuni.exam.models.entity.Sale;
import softuni.exam.repository.SaleRepository;
import softuni.exam.repository.SellerRepository;
import softuni.exam.service.SaleService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class SaleServiceImpl implements SaleService {

    private static final String FILE_PATH = "src/main/resources/files/json/sales.json";

    private final SaleRepository saleRepository;
    private final SellerRepository sellerRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public SaleServiceImpl(SaleRepository saleRepository, SellerRepository sellerRepository, Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.saleRepository = saleRepository;
        this.sellerRepository = sellerRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.saleRepository.count() > 0;
    }

    @Override
    public String readSalesFileContent() throws IOException {
        return Files.readString(Path.of(FILE_PATH));
    }

    @Override
    public String importSales() throws IOException {
        StringBuilder sb = new StringBuilder();

        SaleImportDto[] saleImportDtos = this.gson.fromJson(readSalesFileContent(),
                                                SaleImportDto[].class);

        for (SaleImportDto saleImportDto : saleImportDtos) {

            if (this.saleRepository.findSaleByNumber(saleImportDto.getNumber()).isPresent() ||
                    !this.validationUtil.isValid(saleImportDto)) {

                sb.append("Invalid sale").append(System.lineSeparator());
                continue;
            }

            Sale sales = this.saleRepository.findSaleByNumber(saleImportDto.getNumber()).orElse(null);
            if (sales != null) {
                sb.append("Invalid sale").append(System.lineSeparator());
                continue;
            }

            Sale sale = this.modelMapper.map(saleImportDto, Sale.class);
//            // ?????
//            sale.setNumber(String.valueOf(this.sellerRepository.findById((long)saleImportDto.getSeller()).get()));
            this.saleRepository.save(sale);


            sb.append(String.format("Successfully imported sale with number %s"
                    ,saleImportDto.getNumber()))
                    .append(System.lineSeparator());
        }

        return sb.toString();
    }
}
