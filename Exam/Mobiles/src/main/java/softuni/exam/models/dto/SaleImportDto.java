package softuni.exam.models.dto;

import com.google.gson.annotations.Expose;
import org.hibernate.validator.constraints.Length;

public class SaleImportDto {

    @Expose
    private boolean discounted;
    @Expose
    @Length(min = 7, max = 7)
    private String number;
    @Expose
    private String saleDate;
    @Expose
    private int seller;

    public int getSeller() {
        return seller;
    }

    public void setSeller(int seller) {
        this.seller = seller;
    }

    public String getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(String saleDate) {
        this.saleDate = saleDate;
    }

    public @Length(min = 7, max = 7) String getNumber() {
        return number;
    }

    public void setNumber(@Length(min = 7, max = 7) String number) {
        this.number = number;
    }

    public boolean isDiscounted() {
        return discounted;
    }

    public void setDiscounted(boolean discounted) {
        this.discounted = discounted;
    }
}
