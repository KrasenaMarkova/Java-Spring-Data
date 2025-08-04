package softuni.exam.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "borrowing_records")
@XmlAccessorType(XmlAccessType.FIELD)
public class BorrowingRecordsImportRootDto {

    @XmlElement(name = "borrowing_record")
    private List<BorrowingRecordsImportDto> borrowingRecordSeedDtos;

    public List<BorrowingRecordsImportDto> getBorrowingRecordSeedDtos() {
        return borrowingRecordSeedDtos;
    }

    public void setBorrowingRecordSeedDtos(List<BorrowingRecordsImportDto> borrowingRecordSeedDtos) {
        this.borrowingRecordSeedDtos = borrowingRecordSeedDtos;
    }
}
