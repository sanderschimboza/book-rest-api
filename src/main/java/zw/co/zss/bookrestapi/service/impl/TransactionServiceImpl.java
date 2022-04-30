package zw.co.zss.bookrestapi.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import zw.co.zss.bookrestapi.model.Transaction;
import zw.co.zss.bookrestapi.model.TransactionResponse;
import zw.co.zss.bookrestapi.repository.TransactionRepository;
import zw.co.zss.bookrestapi.service.TransactionService;
import zw.co.zss.bookrestapi.utils.Constants;

import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository repository;

    @Override
    public TransactionResponse doPostTransaction(Transaction transaction) {

        Transaction tran = Transaction.builder().build();
        BeanUtils.copyProperties(transaction, tran);
        tran.setReference(UUID.randomUUID().toString());

        log.info("Posting Transaction::: {}", tran);

        return postPayment(tran);
    }

    @Override
    public void saveTransaction(TransactionResponse response) {
        this.repository.save(response);
    }

    private TransactionResponse postPayment(Transaction request) {

        WebClient webClient = WebClient.builder()
                .baseUrl(Constants.TRANSACTION_END_POINT)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .build();

        TransactionResponse response = webClient.post()
                .uri(Constants.TRANSACTION_URL)
                .headers(httpHeaders -> httpHeaders.setBearerAuth(Constants.BEARER_TOKEN_KEY))
                .body(Mono.just(request), Transaction.class)
                .retrieve()

                .onStatus(HttpStatus::is5xxServerError, clientResponse -> {
                    log.error("Error endpoint with status code {}", clientResponse.statusCode());
                    return clientResponse.createException();
                })

                .bodyToMono(TransactionResponse.class)
                .block();

        log.info("RES:::::: {}", response);
        return response;
    }
}
