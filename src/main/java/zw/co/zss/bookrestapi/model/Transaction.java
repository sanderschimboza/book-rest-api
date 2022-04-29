package zw.co.zss.bookrestapi.model;

import lombok.Builder;
import lombok.Data;
import java.util.Map;

@Data
@Builder
public class Transaction {
    private String type;
    private String extendedType;
    private Double amount;
    private String created;
    private String reference;
    private String narration;
    private Map<String, Object> additionalData;
    private Card card;
}
