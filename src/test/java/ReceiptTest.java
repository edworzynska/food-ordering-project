import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReceiptTest {
    Receipt receipt;
    @Mock
    Order order;

    @BeforeEach
    void setUp(){

        receipt = new Receipt();

    }
    @Test
    void generatesReceipt() {
        var mockOrder = Mockito.mock(Order.class);
        var result = receipt.generateReceipt();
        when()
    }
}