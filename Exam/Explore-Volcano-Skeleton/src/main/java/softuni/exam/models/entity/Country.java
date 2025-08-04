package softuni.exam.models.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "countries")
public class Country extends BaseEntity{

    @Column(nullable = false, unique = true)
    private String name;
    @Column
    private String capital;
    //One Country can have many Volcanoes, but one Volcano is located in only one Country.
    @OneToMany(mappedBy = "country", fetch = FetchType.EAGER)
    private Set<Volcano> volcanoces;

    public Country() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }
}
