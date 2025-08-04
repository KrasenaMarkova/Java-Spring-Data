package softuni.exam.models.entity;

import softuni.exam.models.enums.VolcanoType;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "volcanoes")
public class Volcano extends BaseEntity{

    @Column(unique = true, nullable = false)
    private String name;
    @Column(nullable = false)
    private int elevation;
    @Column(name = "volcano_type")
    @Enumerated(EnumType.STRING)
    private VolcanoType volcanoType;
    @Column(name = "is_active", nullable = false)
    private boolean isActive;
    @Column(name = "last_eruption")
    private LocalDate lastEruption;
    //One Country can have many Volcanoes, but one Volcano is located in only one Country.
    @ManyToOne
    @JoinColumn(name = "country_id", referencedColumnName = "id")
    private Country country;

    //One Volcanologist may explore only one Volcano,
    // but one Volcano may be explored by many Volcanologists.
    @OneToMany(mappedBy = "volcano", fetch = FetchType.EAGER)
    private Set<Volcanologist> volcanologists;

    public Volcano() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public LocalDate getLastEruption() {
        return lastEruption;
    }

    public void setLastEruption(LocalDate lastEruption) {
        this.lastEruption = lastEruption;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public VolcanoType getVolcanoType() {
        return volcanoType;
    }

    public void setVolcanoType(VolcanoType volcanoType) {
        this.volcanoType = volcanoType;
    }

    public int getElevation() {
        return elevation;
    }

    public void setElevation(int elevation) {
        this.elevation = elevation;
    }
}
