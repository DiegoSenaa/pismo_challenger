package com.diego.app.domain.strategy.transaction;

import com.diego.app.infrastructure.commun.MathBasicOperations;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component("compraavista")
@Slf4j
public class TransactionOperationCompraAvistaStrategy implements TransactionOperationStrategy {

    @Override
    public Double calcValue(Double value) {

        log.info("called strategy transaction: compraavista");
        return MathBasicOperations.toNegative(value);
    }
}
