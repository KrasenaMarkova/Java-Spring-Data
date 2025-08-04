package softuni.exam.models.dto;

import com.google.gson.annotations.Expose;
import jakarta.validation.constraints.Min;
import org.hibernate.validator.constraints.Length;

public class CountryImportDto {

    @Expose
    private Long id;
    @Expose
    private Long country;
    @Expose
    @Min(value = 0)
    private Double area;
    @Expose
    @Length(min = 3, max = 40)
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCountry() {
        return country;
    }

    public void setCountry(Long country) {
        this.country = country;
    }

    public @Min(value = 0) Double getArea() {
        return area;
    }

    public void setArea(@Min(value = 0) Double area) {
        this.area = area;
    }

    public @Length(min = 3, max = 40) String getName() {
        return name;
    }

    public void setName(@Length(min = 3, max = 40) String name) {
        this.name = name;
    }
}
