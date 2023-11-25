package com.diego.app.domain.strategy.transaction;

import com.diego.app.infrastructure.commun.MathBasicOperations;
import org.springframework.stereotype.Component;

@Component("pagamento")
public class TransactionOperationPagamentoStrategy implements TransactionOperationStrategy {

    @Override
    public Double calcValue(Double value) {
        return MathBasicOperations.toPositive(value);
    }
}
