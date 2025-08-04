package softuni.exam.models.dto;


import org.hibernate.validator.constraints.Length;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "device")
@XmlAccessorType(XmlAccessType.FIELD)
public class DeviceImportDto {

    @XmlElement
    @Length(min = 2, max = 20)
    private String brand;
    @XmlElement(name = "device_type")
    private String deviceType;
    @XmlElement
    @Length(min = 1, max = 20)
    private String model;
    @XmlElement
    private double price;
    @XmlElement
    private int storage;
    @XmlElement(name = "sale_id")
    private Long sale;

    public Long getSale() {
        return sale;
    }

    public void setSale(Long sale) {
        this.sale = sale;
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

    public @Length(min = 1, max = 20) String getModel() {
        return model;
    }

    public void setModel(@Length(min = 1, max = 20) String model) {
        this.model = model;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public @Length(min = 2, max = 20) String getBrand() {
        return brand;
    }

    public void setBrand(@Length(min = 2, max = 20) String brand) {
        this.brand = brand;
    }
}
