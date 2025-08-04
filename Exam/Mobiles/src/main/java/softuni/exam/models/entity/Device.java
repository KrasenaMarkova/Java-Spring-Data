package softuni.exam.models.entity;

import softuni.exam.models.enums.DeviceType;

import javax.persistence.*;

@Entity
@Table(name = "devices")
public class Device extends BaseEntity{

    @Column(nullable = false)
    private String brand;
    @Column(name = "device_type")
    @Enumerated(EnumType.STRING)
    private DeviceType deviceType;
    @Column(unique = true, nullable = false)
    private String model;
    @Column
    private double price;
    @Column
    private int storage;

    //One Device may appear in only one Sale,
    // but one Sale may have many Devices.
    @ManyToOne
    @JoinColumn(name = "sale_id", referencedColumnName = "id")
    private Sale sale;

    public Device() {
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getStorage() {
        return storage;
    }

    public void setStorage(int storage) {
        this.storage = storage;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
    }
}
