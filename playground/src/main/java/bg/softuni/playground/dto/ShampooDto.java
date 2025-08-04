package bg.softuni.playground.dto;

import bg.softuni.playground.entity.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ShampooDto extends BaseDto {

    private String brand;
    private BigDecimal price;
    private Size size;
    private LabelDto label;
    List<IngredientDto> ingredients;
}
