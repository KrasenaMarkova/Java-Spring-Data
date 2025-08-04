package bg.softuni._16_softunigamestore.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "games")
public class Game extends BaseEntity{

    @Column
    private String title;
    @Column
    private String trailer;
    @Column
    private BigDecimal price;
    @Column
    private double size;
    @Column(name = "image_thumbnail")
    private String imageThumbnail;
    @Column
    private String description;
    @Column(name = "realease_data")
    private LocalDate releaseDate;

    public Game() {
    }

    public Game(String title, LocalDate releaseDate,
                String description, String imageThumbnail,
                double size, String trailer, BigDecimal price) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.description = description;
        this.imageThumbnail = imageThumbnail;
        this.size = size;
        this.trailer = trailer;
        this.price = price;
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

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return Double.compare(size, game.size) == 0 && Objects.equals(title, game.title) && Objects.equals(trailer, game.trailer) && Objects.equals(price, game.price) && Objects.equals(imageThumbnail, game.imageThumbnail) && Objects.equals(description, game.description) && Objects.equals(releaseDate, game.releaseDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, trailer, price, size, imageThumbnail, description, releaseDate);
    }
}
