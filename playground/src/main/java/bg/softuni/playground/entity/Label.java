package bg.softuni.playground.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "labels")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data
public class Label extends BaseEntity {

    @Column(name = "title", unique = true)
    private String title;

    @Column(name = "subtitle")
    private String subTitle;

    @OneToMany(mappedBy = "label", fetch = FetchType.EAGER)
    @Builder.Default
    private List<Shampoo> shampoos = new ArrayList<>();
}
