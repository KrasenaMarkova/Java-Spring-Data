package softuni.exam.models.dto;

import com.google.gson.annotations.Expose;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;

public class BookImportDto {

    @Expose
    @Length(min = 3, max = 40)
    private String title;
    @Expose
    @Length(min = 3, max = 40)
    private String author;
    @Expose
    @Length(min = 5)
    private String description;
    @Expose
    private boolean available;
    @Expose
    private String genre;
    @Expose
    @Min(value = 1)
    private Double rating;

    public @Length(min = 3, max = 40) String getTitle() {
        return title;
    }

    public void setTitle(@Length(min = 3, max = 40) String title) {
        this.title = title;
    }

    public @Min(value = 1) Double getRating() {
        return rating;
    }

    public void setRating(@Min(value = 1) Double rating) {
        this.rating = rating;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public @Length(min = 5) String getDescription() {
        return description;
    }

    public void setDescription(@Length(min = 5) String description) {
        this.description = description;
    }

    public @Length(min = 3, max = 40) String getAuthor() {
        return author;
    }

    public void setAuthor(@Length(min = 3, max = 40) String author) {
        this.author = author;
    }
}
