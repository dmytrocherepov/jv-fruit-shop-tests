package core.basesyntax.handlers;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import core.basesyntax.db.Storage;
import core.basesyntax.model.FruitTransaction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class SupplyOperationHandlerTest {
    private static SupplyOperationHandler supplyOperationHandler;

    @BeforeAll
    static void beforeAll() {
        supplyOperationHandler = new SupplyOperationHandler();
    }

    @AfterEach
    void tearDown() {
        Storage.STORAGE.clear();
    }

    @Test
    void processSupply_ok() {
        Storage.STORAGE.put("banana", 15);
        FruitTransaction fruitTransaction = new FruitTransaction(
                FruitTransaction.Operation.RETURN, "banana", 100);
        assertDoesNotThrow(() -> supplyOperationHandler.calculateOperation(fruitTransaction)
        );
        int actualAmount = Storage.STORAGE.get("banana");
        assertEquals(115, actualAmount);
    }
}