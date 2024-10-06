import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OrderInfoText {
    private static final String ACCOUNT_SID = System.getenv("ACCOUNT_SID");
    private static final String AUTH_TOKEN = System.getenv("AUTH_TOKEN");
    private static Boolean confirmationSent = false;
    private static String NUMBER_TO = System.getenv("NUMBER_TO");
    private static String NUMBER_FROM = System.getenv("NUMBER_FROM");

    private static String getDeliveryTime(){
        LocalDateTime deliveryTime = LocalDateTime.now().plusMinutes(40);
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        return deliveryTime.format(timeFormatter);
    }

    public void sendOrderInfo(){
        if (!confirmationSent) {
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
            Message.creator(
                            new PhoneNumber(NUMBER_TO),
                            new PhoneNumber(NUMBER_FROM),
                            String.format("Thank you for your order! It will be delivered before %s.", getDeliveryTime()))
                    .create();
            confirmationSent = true;
        }
    }
}
