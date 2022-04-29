package zw.co.zss.bookrestapi.service;


import zw.co.zss.bookrestapi.model.Transaction;
import zw.co.zss.bookrestapi.model.TransactionResponse;

public interface TransactionService {

    TransactionResponse doPostTransaction(Transaction transaction);

    void saveTransaction(TransactionResponse response);
}
