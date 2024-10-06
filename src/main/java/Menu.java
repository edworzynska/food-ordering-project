import java.util.ArrayList;
import java.util.List;

public class Menu {
    private final List<Dish> menu;

    public Menu() {
        menu = new ArrayList<>();
    }
    public List<Dish> getMenu() {
        return menu;
    }
    public void add(Dish dish){
        menu.add(dish);
    }
    public String display(){
        if (menu.isEmpty()){
            throw new RuntimeException("Menu is empty!");
        }
        StringBuilder str = new StringBuilder();
        for (Dish dish : menu){
            str.append(dish.display()).append("\n");
        }
        return str.toString();
    }
}
