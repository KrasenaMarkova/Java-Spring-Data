package softuni.exam.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.Device;

import java.util.Optional;
import java.util.List;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {

    Optional<Device> findByBrand(String brand);
    Optional<Device> findByModel(String model);

    //List<Device> findAllByPriceLessThanAndDeviceTypeAndPriceMoreThanEqualOrderByBrand(Double price, Double priceMoreThanEqual);

    @Query("select d from Device d where d.price < 1000 and  d.deviceType = 'SMART_PHONE' and d.storage >= 128" +
            " order by LOWER(d.brand) asc")
    List<Device> findAllSmartPhonesCheaperThan1000AndStorageMoreThan128();

    List<Device> findAllSmartPhonesByPriceCheaperThanAndStorageGreaterThanEqualOrderByBrand(double price, int storage);

    List<Device> findAllByPriceLessThanAndStorageGreaterThanEqual(double price, int storage);
}
