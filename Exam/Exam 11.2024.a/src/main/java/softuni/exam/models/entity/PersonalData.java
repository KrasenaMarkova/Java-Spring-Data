package softuni.exam.models.entity;

import jakarta.persistence.*;
import softuni.exam.models.entity.enums.Gender;

import java.time.LocalDate;

@Entity
@Table(name = "personal_datas")
public class PersonalData extends BaseEntity{

    @Column
    private int age;
    @Column(name = "birth_date")
    private LocalDate birthDate;
    @Column(name = "card_number", nullable = false, unique = true)
    private String cardNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", columnDefinition = "CHARACTER")
    private Gender gender;

    //A Visitor may have only one Personal data,
    // and one Personal data belongs only to one Visitor.
    @OneToOne(mappedBy = "personalData")
    private Visitor visitor;

    public PersonalData() {
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Visitor getVisitor() {
        return visitor;
    }

    public void setVisitor(Visitor visitor) {
        this.visitor = visitor;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
