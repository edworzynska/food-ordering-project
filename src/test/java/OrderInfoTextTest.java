import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class OrderInfoTextTest {
    private OrderInfoText orderInfoText;
    @BeforeEach
    public void setUp(){
        orderInfoText = new OrderInfoText();
    }
    @Test
    void sendsOrderInfo() {
        Message mockMessage = Mockito.mock(Message.class);
        MessageCreator mockCreator = Mockito.mock(MessageCreator.class);
        when(mockCreator.create()).thenReturn(mockMessage);

        try (var mockedStatic = mockStatic(Message.class)){
            mockedStatic.when(() -> Message.creator(
                    any(PhoneNumber.class),
                    any(PhoneNumber.class),
                    anyString())
            ).thenReturn(mockCreator);

            orderInfoText.sendOrderInfo();

            mockedStatic.verify(()-> Message.creator(
                    any(PhoneNumber.class),
                    any(PhoneNumber.class),
                    anyString()), times(1)
            );
        }
    }
}