package softuni.exam.models.dto;


import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import org.hibernate.validator.constraints.Length;

@XmlRootElement(name = "personal_data")
@XmlAccessorType(XmlAccessType.FIELD)
public class PersonalDataImportDto {

    @XmlElement(nillable = true)
    @Length(min = 1, max = 100)
    private int age;
    @XmlElement(name = "birth_date")
    private String birthDate;
    @XmlElement(name = "card_number")
    @Length(min = 9, max = 9)
    private String cardNumber;
    @XmlElement
    private Character gender;

    public Character getGender() {
        return gender;
    }

    public void setGender(Character gender) {
        this.gender = gender;
    }

    public @Length(min = 9, max = 9) String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(@Length(min = 9, max = 9) String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    @Length(min = 1, max = 100)
    public int getAge() {
        return age;
    }

    public void setAge(@Length(min = 1, max = 100) int age) {
        this.age = age;
    }
}
