import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReceiptTest {
    Order order;
    Receipt receipt;

    @BeforeEach
    void setUp(){
        order = mock(Order.class);
        receipt = new Receipt(order);
    }
    @Test
    void generatesReceipt() {
        when(order.orderSummary()).thenReturn("random text");
        when(order.orderTotal()).thenReturn(5.00);

        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        String time =  currentDateTime.format(timeFormatter);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = currentDateTime.format(dateFormatter);

        var result = receipt.generateReceipt();
        String expected = String.format("Your order placed on %s at %s:\n\nrandom text\n\nTotal amount: 5,00 GBP", date, time);
        assertEquals(expected, result);
    }

    @Test
    void throwsErrorIfReceiptWasGenerated(){
        receipt.generateReceipt();
        when(order.orderSummary()).thenReturn("random text");
        when(order.orderTotal()).thenReturn(5.00);

        RuntimeException r = assertThrows(RuntimeException.class, ()->receipt.generateReceipt());
        assertEquals("Receipt has already been generated!", r.getMessage());
    }

    @Test
    @Disabled
    void sendsAConfirmationIfFinalised() {
        receipt.generateReceipt();
        receipt.sendConfirmation();
    }

    @Test
    @Disabled
    void doesntSendAConfirmationIfNotFinalised() {
        receipt.sendConfirmation();
    }

    @Test
    @Disabled
    void doesntSendConfirmationTwice() {
        receipt.sendConfirmation();
        receipt.sendConfirmation();
    }
}