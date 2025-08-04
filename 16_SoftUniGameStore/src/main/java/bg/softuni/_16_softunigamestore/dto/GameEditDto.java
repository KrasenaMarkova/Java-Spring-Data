package bg.softuni._16_softunigamestore.dto;

import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.time.LocalDate;

public class GameEditDto {
    private Integer id;
    @Length(min = 3, max = 100, message = "Title must be between 3 and 100 symbols")
    private String title;
    @Positive(message = "Price must be positive number")
    private BigDecimal price;
    @Positive(message = "Size must be positive number")
    private Double size;
    @Length(min = 3, max = 11, message = "Trailer must be 11 symbols length")
    private String trailer;
    private String imageThumbnail;
    @Length(min = 3, max = 20)
    private String description;
    private LocalDate releaseDate;

    public GameEditDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageThumbnail() {
        return imageThumbnail;
    }

    public void setImageThumbnail(String imageThumbnail) {
        this.imageThumbnail = imageThumbnail;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }
}
