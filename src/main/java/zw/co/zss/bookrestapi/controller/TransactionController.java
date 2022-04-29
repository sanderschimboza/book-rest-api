package zw.co.zss.bookrestapi.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zw.co.zss.bookrestapi.model.Transaction;
import zw.co.zss.bookrestapi.model.TransactionResponse;
import zw.co.zss.bookrestapi.service.TransactionService;
import zw.co.zss.bookrestapi.utils.Constants;
import zw.co.zss.bookrestapi.utils.ValidationResponse;

/**
 * @author Sanders
 */

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService service;

    @PostMapping
    public ResponseEntity<Object> doTransaction(@RequestBody Transaction transaction) {

        TransactionResponse response = service.doPostTransaction(transaction);
        if (response != null) {
            service.saveTransaction(response);
            return ResponseEntity.status(Constants.RESPONSE_CODE_OK)
                    .body(response);
        }
        return ResponseEntity.status(404).body(ValidationResponse.builder()
                .description("An error occurred during connection to Client")
                .successful(Boolean.FALSE)
                .responseCode(Constants.RESPONSE_CODE_BAD_REQUEST)
                .build());
    }
}
