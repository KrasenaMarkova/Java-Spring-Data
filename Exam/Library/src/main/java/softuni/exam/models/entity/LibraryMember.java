package softuni.exam.models.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "library_members")
public class LibraryMember extends BaseEntity{

    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column
    private String address;
    @Column(name = "phone_number", unique = true, nullable = false)
    private String phoneNumber;

    //One Borrowing Record may have only one Library Member,
    // but one Library Member can be in many Borrowing Records.
    @OneToMany(mappedBy = "libraryMember", fetch = FetchType.EAGER)
    private Set<BorrowingRecord> borrowingRecords;

    public LibraryMember() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
