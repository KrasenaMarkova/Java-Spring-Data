package bg.softuni._16_softunigamestore.dto;

import java.math.BigDecimal;

public class GameViewDto {
    private String title;
    private BigDecimal price;

    public GameViewDto() {
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return String.format("%s %.2f\n", this.title, this.price);
    }
}
