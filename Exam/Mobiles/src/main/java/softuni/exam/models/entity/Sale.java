package softuni.exam.models.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "sales")
public class Sale extends BaseEntity{

    @Column
    private boolean discounted;
    @Column(unique = true, nullable = false)
    private String number;
    @Column(name = "sale_date", nullable = false)
    private LocalDateTime saleDate;

    //One Seller can have many Sales, but one Sale has only one Seller.
    @ManyToOne
    @JoinColumn(name = "seller_id", referencedColumnName = "id")
    private Seller seller;

    //One Device may appear in only one Sale, but one Sale may have many Devices.
    @OneToMany(mappedBy = "sale", fetch = FetchType.EAGER)
    private Set<Device> devices;

    public Sale() {
    }

    public boolean isDiscounted() {
        return discounted;
    }

    public void setDiscounted(boolean discounted) {
        this.discounted = discounted;
    }

    public LocalDateTime getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDateTime saleDate) {
        this.saleDate = saleDate;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
