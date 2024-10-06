import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Receipt {
    private final Order order;
    private Boolean isFinalised = false;

    public Receipt(Order order) {
        this.order = order;
    }
    private String getTime(){
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        return currentDateTime.format(timeFormatter);
    }
    private String getDate(){
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return currentDateTime.format(dateFormatter);
    }
    public String generateReceipt(){
        isFinalised = true;
        return String.format("Your order placed on %s at %s:\n\n", getDate(), getTime()) + order.orderSummary()
                + String.format("\n\nTotal amount: %.2f GBP", order.orderTotal());
    }
    public void sendConfirmation() {
        if (isFinalised){
            OrderInfoText sendInfo = new OrderInfoText();
            sendInfo.sendOrderInfo();}
    }
}
