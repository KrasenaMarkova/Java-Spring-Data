package bg.softuni._15_modelmapping;

import bg.softuni._15_modelmapping.entities.Address;
import bg.softuni._15_modelmapping.entities.Transfer;
import bg.softuni._15_modelmapping.entities.User;
import bg.softuni._15_modelmapping.entities.dtos.*;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoField;

public class Main {
    public static void main(String[] args) {
        Address address = new Address("BG", "Sofia");
        User user = new User("user", "pass", "first", "last", 22, address);
        User sender = new User("send", "send", "firstSend", "lastSend", 11, address);

        ModelMapper mapper = new ModelMapper();

        Transfer transfer = new Transfer(sender, user, BigDecimal.TEN, LocalDate.now(), Instant.now());
//        TransferDTO dto = mapper.map(transfer, TransferDTO.class);

        Converter<Instant, String> instantToString =
                context -> context.getSource().get(ChronoField.NANO_OF_SECOND) + "";

        PropertyMap<Transfer, TransferDTO> transferToDTOMap = new PropertyMap<>() {
            @Override
            protected void configure() {
                map().setSenderName(source.getSender().getFirstName());
                map().setReceiverName(source.getReceiver().getFirstName());
            }
        };
        mapper.addMappings(transferToDTOMap);
        mapper.addConverter(instantToString, Instant.class, String.class);

        TransferDTO dto = mapper.map(transfer, TransferDTO.class);

        System.out.println(dto);
    }

    public void examples(User user) {
        ModelMapper mapper = new ModelMapper();

        UserLoginDTO loginDTO = mapper.map(user, UserLoginDTO.class);
        System.out.println(loginDTO);

        User loginUser = mapper.map(loginDTO, User.class);
        System.out.println(loginUser);

        UserNamesDTO names = mapper.map(user, UserNamesDTO.class);
        System.out.println(names);

        User emptyUser = new User();
        mapper.map(names, emptyUser);
        System.out.println(emptyUser);

        UserDeliveryDTO withAddress = mapper.map(user, UserDeliveryDTO.class);
        System.out.println(withAddress);
    }

    // Validate that userId exists
    // Do we have rights
    // Plan A - Fetch user from DB -> apply changes -> save
    // Plan B - Save changed info to DB
    public void updateUser(long userId, UserPublicInfoDTO data) {
    }
}
