package softuni.exam.models.entity;

import org.hibernate.validator.constraints.Length;
import softuni.exam.models.entity.enums.StarType;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "stars")
public class Star extends BaseEntity{

    @Column(unique = true)
    private String name;
    @Column(name = "light_years")
    @Length(min = 1)
    private Double lightYears;
    @Column
    private String description;
    @Column(name = "star_type")
    @Enumerated(EnumType.STRING)
    private StarType starType;
    
    @OneToMany(mappedBy = "observingStar", fetch = FetchType.EAGER)
    private Set<Astronomer> observers = new HashSet<>();
}
