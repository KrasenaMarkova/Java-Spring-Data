package softuni.exam.models.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "constellations")
public class Constellation extends BaseEntity{

    @Column(unique = true)
    private String name;
    @Column
    private String description;

    @OneToMany(mappedBy = "constellation", fetch = FetchType.EAGER)
    private Set<Star> stars = new HashSet<>();



}
