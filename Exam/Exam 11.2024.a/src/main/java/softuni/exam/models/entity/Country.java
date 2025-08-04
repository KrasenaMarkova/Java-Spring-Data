package softuni.exam.models.entity;


import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "countries")
public class Country extends BaseEntity {

    @Column
    private Double area;
    @Column(nullable = false, unique = true)
    private String name;

    //One Country can have many Attractions,
    //but one Attraction is in only one Country.
    @OneToMany(mappedBy = "country", fetch = FetchType.EAGER)
    Set<Attraction> attractions;
    //Set<Attraction> attractions = new HashSet<>();

    @OneToMany(mappedBy = "country", fetch = FetchType.EAGER)
    Set<Visitor> visitors;

    public Country() {
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public Set<Visitor> getVisitors() {
        return visitors;
    }

    public void setVisitors(Set<Visitor> visitors) {
        this.visitors = visitors;
    }

    public Set<Attraction> getAttractions() {
        return attractions;
    }

    public void setAttractions(Set<Attraction> attractions) {
        this.attractions = attractions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
