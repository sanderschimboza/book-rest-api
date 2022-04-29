package zw.co.zss.bookrestapi.utils;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ValidationResponse {
    private Boolean successful;
    private String description;
    private Integer responseCode;
}
