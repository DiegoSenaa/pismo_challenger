package com.diego.app.domain.strategy.transaction;

import com.diego.app.infrastructure.commun.MathBasicOperations;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component("pagamento")
@Slf4j
public class TransactionOperationPagamentoStrategy implements TransactionOperationStrategy {

    @Override
    public Double calcValue(Double value) {
        log.info("called strategy transaction: pagamento");
        return MathBasicOperations.toPositive(value);
    }
}
