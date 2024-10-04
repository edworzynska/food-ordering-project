import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class IntegrationTest {
    Order order;
    Dish dish1;
    Dish dish2;
    Dish dish3;
    Menu menu;
    @BeforeEach
    void setUp() {
        order = new Order();
        menu = new Menu();
        dish1 = new Dish("tomato soup", 5.0);
        dish2 = new Dish("spaghetti", 20.0);
        dish3 = new Dish("prawns", 49.99);
    }
    @Test
    void addsDishesToMenu() {
        menu.add(dish1);
        menu.add(dish2);
        menu.add(dish3);
        assertEquals(List.of(dish1, dish2, dish3), menu.getMenu());
    }

    @Test
    void displaysMenu() {
        menu.add(dish1);
        menu.add(dish2);
        menu.add(dish3);
        var result = menu.display();
        assertEquals("tomato soup - 5,00 GBP\nspaghetti - 20,00 GBP\nprawns - 49,99 GBP\n", result);
    }

    @Test
    void throwsAnErrorWhenTryingToDisplayEmptyMenu() {
        RuntimeException r = assertThrows(RuntimeException.class, ()->menu.display());
        assertEquals("Menu is empty!", r.getMessage());
    }

    @Test
    void addsDishToOrder() {
        order.add(dish1, 1);
        assertEquals(Map.of(dish1, 1), order.getOrder());
    }

    @Test
    void changesTheQuantityIfDishAddedTwice() {
        order.add(dish1, 1);
        order.add(dish1, 1);
        assertEquals(Map.of(dish1, 2), order.getOrder());
    }
    @Test
    void returnsOrderSummaryAsString() {
        order.add(dish1, 1);
        order.add(dish2, 3);
        var result =  order.orderSummary();
        assertEquals("x1  tomato soup\nx3  spaghetti", result);
    }

    @Test
    void throwsErrorIfOrderIsEmpty() {
        RuntimeException r = assertThrows(RuntimeException.class, ()->order.orderSummary());
        assertEquals("Order is empty!", r.getMessage());
    }

    @Test
    void removesDishFromOrder() {
        order.add(dish1, 1);
        order.add(dish2, 2);
        order.removeDish(dish2);
        var result = order.getOrder();
        assertEquals(Map.of(dish1, 1), result);
    }

    @Test
    void returnsTotalPricePerDish() {
        order.add(dish1, 5);
        order.add(dish2, 2);
        var result1 = order.dishTotal(dish1);
        var result2 = order.dishTotal(dish2);
        assertEquals(25, result1);
        assertEquals(40, result2);
    }

    @Test
    void returnsOrderTotal() {
        order.add(dish1, 5);
        order.add(dish2, 2);
        var result = order.orderTotal();
        assertEquals(65, result);
        order.add(dish2, 1);
        var result2 = order.orderTotal();
        assertEquals(85, result2);
    }
}
