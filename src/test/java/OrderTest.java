import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class OrderTest {
    Order order;
    @BeforeEach
    public void setUp(){
        order = new Order();
    }

    @Test
    void getsOrder() {
        var dish = Mockito.mock(Dish.class);
        var result = order.getOrder();
        assertEquals(Map.of(), result);
    }

    @Test
    void addsElementsToOrderAndGetsOrder() {
        var dish1 = Mockito.mock(Dish.class);
        order.add(dish1,1);
        var result = order.getOrder();
        assertEquals(Map.of(dish1,1), result);
    }

    @Test
    void doesNothingIfQuantityIsEqualOrLessThanZero() {
        var dish1 = Mockito.mock(Dish.class);
        var dish2 = Mockito.mock(Dish.class);
        var dish3 = Mockito.mock(Dish.class);
        order.add(dish1,1);
        order.add(dish2, -1);
        order.add(dish3, 0);
        var result = order.getOrder();
        assertEquals(Map.of(dish1, 1), result);
    }



    @Test
    void addsQuantityIfDishAlreadyInOrder() {
        var dish1 = Mockito.mock(Dish.class);
        order.add(dish1,1);
        order.add(dish1,5);
        var result = order.getOrder();
        assertEquals(Map.of(dish1, 6), result);
    }

    @Test
    void displaysOrderSummary() {
        var dish1 = Mockito.mock(Dish.class);
        var dish2 = Mockito.mock(Dish.class);
        when(dish1.getName()).thenReturn("a dish");
        when(dish2.getName()).thenReturn("other dish");
        when(dish2.getPrice()).thenReturn(1.5);
        order.add(dish1,1);
        order.add(dish2, 2);
        var result = order.orderSummary();
        assertEquals("x1  a dish - 0,00 GBP\nx2  other dish - 3,00 GBP", result);
    }

    @Test
    void removesDish() {
        var dish1 = Mockito.mock(Dish.class);
        var dish2 = Mockito.mock(Dish.class);
        order.add(dish1, 1);
        order.add(dish2, 1);
        order.removeDish(dish2);
        var result = order.getOrder();
        assertEquals(Map.of(dish1, 1), result);
    }

    @Test
    void calculatesDishPrice() {
        var dish1 = Mockito.mock(Dish.class);
        order.add(dish1, 3);
        when(dish1.getPrice()).thenReturn(7.0);
        var result = order.dishTotal(dish1);
        assertEquals(21.00, result);

    }

    @Test
    void throwsAnErrorIfCalculatingTotalOfEmptyOrder() {
        RuntimeException r = assertThrows(RuntimeException.class, ()->order.orderTotal());
        assertEquals("Order is empty!", r.getMessage());
    }

    @Test
    void calculatesTheTotal() {
        var dish1 = Mockito.mock(Dish.class);
        order.add(dish1, 3);
        when(dish1.getPrice()).thenReturn(7.0);
        var dish2 = Mockito.mock(Dish.class);
        order.add(dish2, 3);
        when(dish2.getPrice()).thenReturn(3.0);
        var result = order.orderTotal();
        assertEquals(30.00, result);
    }
}