package softuni.exam.models.dto;

import com.google.gson.annotations.Expose;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.Objects;

public class AttractionImportDto {

    @Expose
    private Long id;
    @Expose
    @Length(min = 10, max = 100)
    private String description;
    @Expose
    @Min(value = 0)
    private int elevation;
    @Expose
    @Length(min = 5, max = 40)
    private String name;
    @Expose
    @Length(min = 3, max = 30)
    private String type;
    @Expose
    private Long country;

    public Long getCountry() {
        return country;
    }

    public void setCountry(Long country) {
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @Length(min = 3, max = 30) String getType() {
        return type;
    }

    public void setType(@Length(min = 3, max = 30) String type) {
        this.type = type;
    }

    public @Length(min = 5, max = 40) String getName() {
        return name;
    }

    public void setName(@Length(min = 5, max = 40) String name) {
        this.name = name;
    }

    @Min(0)
    public int getElevation() {
        return elevation;
    }

    public void setElevation(@Min(0) int elevation) {
        this.elevation = elevation;
    }

    public @Length(min = 10, max = 100) String getDescription() {
        return description;
    }

    public void setDescription(@Length(min = 10, max = 100) String description) {
        this.description = description;
    }

   /* @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AttractionImportDto that = (AttractionImportDto) o;
        return elevation == that.elevation && Objects.equals(id, that.id) && Objects.equals(description, that.description) && Objects.equals(name, that.name) && Objects.equals(type, that.type) && Objects.equals(countryId, that.countryId);
    }*/

    /*@Override
    public int hashCode() {
        return Objects.hash(id, description, elevation, name, type, countryId);
    }*/
}
