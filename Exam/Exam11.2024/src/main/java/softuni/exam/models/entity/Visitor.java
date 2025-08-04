package softuni.exam.models.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "visitors")
public class Visitor extends BaseEntity {

    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;

    //A Visitor may have only one Personal data,
    // and one Personal data belongs only to one Visitor.
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "personal_data_id", referencedColumnName = "id", nullable = false)
    private PersonalData personalData;

    //One Visitor can visit only one Attraction,
    //but one Attraction can be visited by many Visitors.
    @ManyToOne
    @JoinColumn(name = "attraction_id", referencedColumnName = "id", nullable = false)
    private Attraction attraction;

    @ManyToOne
    @JoinColumn(name = "country_id", referencedColumnName = "id", nullable = false)
    private Country country;

    public Visitor() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Attraction getAttraction() {
        return attraction;
    }

    public void setAttraction(Attraction attraction) {
        this.attraction = attraction;
    }

    public PersonalData getPersonalData() {
        return personalData;
    }

    public void setPersonalData(PersonalData personalData) {
        this.personalData = personalData;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
