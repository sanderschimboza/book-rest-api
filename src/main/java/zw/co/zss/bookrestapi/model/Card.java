package zw.co.zss.bookrestapi.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Card {
    private String id;
    private String expiry;
}
