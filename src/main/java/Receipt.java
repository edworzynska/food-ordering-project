import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Receipt {
    private final Order order;

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
        return String.format("Your order placed on %s at %s:\n\n", getDate(), getTime()) + order.orderSummary()
                + String.format("\n\nTotal amount: %.2f GBP", order.orderTotal());
    }
}
