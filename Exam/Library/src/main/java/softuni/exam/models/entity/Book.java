package softuni.exam.models.entity;

import softuni.exam.models.entity.enums.Genre;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "books")
public class Book extends BaseEntity{

    @Column(unique = true, nullable = false)
    private String title;
    @Column(nullable = false)
    private String author;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;
    @Column(nullable = false)
    private boolean available;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Genre genre;
    @Column(nullable = false)
    private Double rating;

    //One Borrowing Record may have only one Book,
    // but one Book may be in many Borrowing Records.
    @OneToMany(mappedBy = "book", fetch = FetchType.EAGER)
    private Set<BorrowingRecord> borrowingRecordsSet;

    public Book() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
