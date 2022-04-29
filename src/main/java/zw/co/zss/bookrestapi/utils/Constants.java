package zw.co.zss.bookrestapi.utils;

public class Constants {

    private Constants() {}

    public static final String STATUS_DUPLICATE            = "DUPLICATE";
    public static final String STATUS_SUCCESS              = "SUCCESS";
    public static final String STATUS_ERROR                = "ERROR";
    public static final String STATUS_PENDING              = "PENDING";
    public static final String STATUS_NOT_FOUND            = "NOTFOUND";

    public static final Integer RESPONSE_CODE_OK           = 200;
    public static final Integer RESPONSE_CODE_CREATED      = 201;
    public static final Integer RESPONSE_CODE_NOT_FOUND    = 404;
    public static final Integer RESPONSE_CODE_BAD_REQUEST  = 400;

    public static final String BEARER_TOKEN_KEY            ="9ca3d5ed-dc04-4700-8dd6-7d60c3cdf0fa";
    public static final String TRANSACTION_END_POINT       ="https://lab.v.co.zw/interview";
    public static final String TRANSACTION_URL            ="/api/transaction";

}
