import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Order {

    private final HashMap<Dish, Integer> order;

    public Order() {
       order = new HashMap<>();
    }

    public HashMap<Dish, Integer> getOrder() {
        return order;
    }
    public void add(Dish dish, Integer quantity){
        if (order.containsKey(dish)){
            quantity += order.get(dish);
        }
        order.put(dish, quantity);
    }
    public String orderSummary(){
        if (order.isEmpty()){
            throw new RuntimeException("Order is empty!");
        }
         return order.entrySet().stream()
                .map(entry -> "x" + entry.getValue()+ "  " + entry.getKey().getName())
                .collect(Collectors.joining("\n"));
    }
    public void removeDish(Dish dish){
        order.remove(dish);
    }
    public Double dishTotal(Dish dish){
        return dish.getPrice() * order.get(dish);
    }
    public Double orderTotal(){
        Double total = 0.0;
        for (Map.Entry<Dish, Integer> entry : order.entrySet()){
            Dish dish = entry.getKey();
            total += dishTotal(dish);
        }
        return total;
    }
}
