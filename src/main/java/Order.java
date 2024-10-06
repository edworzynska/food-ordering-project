import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import com.twilio.Twilio;

public class Order {

    private final LinkedHashMap<Dish, Integer> order;

    public Order() {
       order = new LinkedHashMap<>();
    }

    public LinkedHashMap<Dish, Integer> getOrder() {
        return order;
    }
    public void add(Dish dish, Integer quantity){
        if (order.containsKey(dish)){
            quantity += order.get(dish);
        }
        if (quantity > 0) {
            order.put(dish, quantity);
        }
    }
    public String orderSummary(){
        if (order.isEmpty()){
            throw new RuntimeException("Order is empty!");
        }
         return order.entrySet().stream()
                .map(entry -> "x" + entry.getValue()+ "  " + entry.getKey().getName() + " - " + String.format("%.2f", dishTotal(entry.getKey())) + " GBP")
                .collect(Collectors.joining("\n"));
    }
    public void removeDish(Dish dish){
        order.remove(dish);
    }
    public Double dishTotal(Dish dish){
        return dish.getPrice() * order.get(dish);
    }
    public Double orderTotal(){
        if (order.isEmpty()){
            throw new RuntimeException("Order is empty!");
        }
        Double total = 0.0;
        for (Map.Entry<Dish, Integer> entry : order.entrySet()){
            Dish dish = entry.getKey();
            total += dishTotal(dish);
        }
        return total;
    }
}
