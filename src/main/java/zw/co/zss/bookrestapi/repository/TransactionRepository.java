package zw.co.zss.bookrestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.zss.bookrestapi.model.TransactionResponse;

public interface TransactionRepository extends JpaRepository<TransactionResponse, String> {
}
