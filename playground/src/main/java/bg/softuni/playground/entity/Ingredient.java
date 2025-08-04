package bg.softuni.playground.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "ingredients")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Ingredient extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private BigDecimal price;

    @ManyToMany(mappedBy = "ingredients",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    private Set<Shampoo> shampoos;

    @Override
    public String toString() {
        return "Ingredient{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
