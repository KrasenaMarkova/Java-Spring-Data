package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.DeviceImportDto;
import softuni.exam.models.dto.DeviceImportRootDto;
import softuni.exam.models.entity.Device;
import softuni.exam.models.entity.Sale;
import softuni.exam.models.enums.DeviceType;
import softuni.exam.repository.DeviceRepository;
import softuni.exam.repository.SaleRepository;
import softuni.exam.service.DeviceService;
import softuni.exam.service.SaleService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
public class DeviceServiceImpl implements DeviceService {

    private static final String FILE_PATH = "src/main/resources/files/xml/devices.xml";

    private final DeviceRepository deviceRepository;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final SaleRepository saleRepository;

    public DeviceServiceImpl(DeviceRepository deviceRepository, XmlParser xmlParser, ModelMapper modelMapper, ValidationUtil validationUtil, SaleRepository saleRepository) {
        this.deviceRepository = deviceRepository;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.saleRepository = saleRepository;
    }

    @Override
    public boolean areImported() {
        return this.deviceRepository.count() > 0;
    }

    @Override
    public String readDevicesFromFile() throws IOException {
        return Files.readString(Path.of(FILE_PATH));
    }

    @Override
    public String importDevices() throws JAXBException {
        StringBuilder sb = new StringBuilder();

        DeviceImportRootDto deviceImportRootDto = this.xmlParser.fromFile(FILE_PATH, DeviceImportRootDto.class);

        for (DeviceImportDto deviceImportDto : deviceImportRootDto.getDeviceImportDtos()) {
            if (this.deviceRepository.findByBrand(deviceImportDto.getBrand()).isPresent() ||
                    this.deviceRepository.findByModel(deviceImportDto.getModel()).isPresent() ||
                    !this.validationUtil.isValid(deviceImportDto)) {
                sb.append("Invalid device").append(System.lineSeparator());
                continue;
            }
           Sale sale = saleRepository.findSaleById(deviceImportDto.getSale());

           if (sale == null) {
               sb.append("Invalid device").append(System.lineSeparator());
               continue;
           }
            Device device = deviceRepository.findByModel(deviceImportDto.getModel())
                    .orElse(null);
            if (device != null) {
                sb.append("Invalid device").append(System.lineSeparator());
                continue;
            }

            Device devices = this.modelMapper.map(deviceImportDto, Device.class);
            devices.setDeviceType(DeviceType.valueOf(deviceImportDto.getDeviceType()));

            sb.append(String.format("Successfully imported device of type %s with brand %s\n",
                    devices.getDeviceType(),devices.getBrand())).append(System.lineSeparator());;
        }

        return sb.toString();
    }

    @Override
    public String exportDevices() {
        StringBuilder sb = new StringBuilder();
//        this.deviceRepository.findAllByPriceLessThanAndDeviceTypeAndPriceMoreThanEqualOrderByBrand(1000.00, 128.00)
//                .forEach(d -> {
//                    sb.append(String.format("Device brand: %s\n" +
//                            "   *Model: %d\n" +
//                            "   **Storage: %d\n" +
//                            "   ***Price: %.2f\n",
//                            d.getBrand(), d.getModel(), d.getStorage(), d.getPrice()))
//                            .append(System.lineSeparator());
//                });

        //List<Device> foundDevices = deviceRepository.findAllSmartPhonesCheaperThan1000AndStorageMoreThan128();
        List<Device> foundDevices = deviceRepository.findAllSmartPhonesByPriceCheaperThanAndStorageGreaterThanEqualOrderByBrand(1000, 128);

        //this.deviceRepository.findAllSmartPhonesByPriceCheaperThanAndStorageGreaterThanEqualOrderByBrand(1000, 128)
        foundDevices.forEach(d -> {
                    sb.append(String.format("Device brand: %s\n" +
                            "   *Model: %d\n" +
                            "   **Storage: %d\n" +
                            "   ***Price: %.2f\n",
                            d.getBrand(), d.getModel(), d.getStorage(), d.getPrice()))
                            .append(System.lineSeparator());
                });
        //foundDevices.forEach(v -> {
//            sb.append(String.format("Device brand: %s\n" +
//                                    "   *Model: %s\n" +
//                                    "   **Storage: %d\n" +
//                                    "   ***Price: %.2f",
//                            v.getBrand(),
//                            v.getModel(),
//                            v.getStorage(),
//                            v.getPrice()))
//                    .append(System.lineSeparator());
//
//        });

        return sb.toString();
    }
}
