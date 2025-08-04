package softuni.exam.models.dto;

import org.hibernate.validator.constraints.Length;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class BorrowingRecordsImportDto {

    @XmlElement(name = "book")
    private BookImportDto book;
    @XmlElement(name = "member")
    private LibraryMemberImportDto member;
    @XmlElement(name = "member_id")
    private int memberId;
    @XmlElement(name = "borrow_date")
    private String borrowDate;
    @XmlElement(name = "return_date")
    private String returnDate;
    @XmlElement
    @Length(min = 3, max = 100)
    private String remarks;

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public @Length(min = 3, max = 100) String getRemarks() {
        return remarks;
    }

    public void setRemarks(@Length(min = 3, max = 100) String remarks) {
        this.remarks = remarks;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(String borrowDate) {
        this.borrowDate = borrowDate;
    }

    public BookImportDto getBook() {
        return book;
    }

    public void setBook(BookImportDto book) {
        this.book = book;
    }

    public LibraryMemberImportDto getMember() {
        return member;
    }

    public void setMember(LibraryMemberImportDto member) {
        this.member = member;
    }
}
