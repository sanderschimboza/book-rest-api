package zw.co.zss.bookrestapi.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity(name = "transactions")
public class TransactionResponse {
    @Id
    private String reference;
    private Date updated;
    private String responseCode;
    private String responseDescription;
    private String debitReference;
}
