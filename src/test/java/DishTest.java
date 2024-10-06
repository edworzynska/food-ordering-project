import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DishTest {
    Dish dish;
    Dish dish2;

    @BeforeEach
    void setUp(){
        dish = new Dish("Tomato soup", 5.0);
    }

    @Test
    void getsNameOfDish() {
        var result = dish.getName();
        assertEquals("Tomato soup", result);
    }

    @Test
    void getsPrice() {
        var result = dish.getPrice();
        assertEquals(5.0, result);
    }

    @Test
    void throwsErrorIfNameIsEmpty() {
        RuntimeException r = assertThrows(RuntimeException.class, ()->dish2 = new Dish("", 50.00));
        assertEquals("Name cannot be empty!", r.getMessage());
    }
    @Test
    void throwsErrorIfPriceIsLessOrEqualToZero() {
        RuntimeException r = assertThrows(RuntimeException.class, ()->dish2 = new Dish("nice dish", -2.0));
        assertEquals("Price must be greater than 0!", r.getMessage());
    }

    @Test
    void displaysDish() {
        dish2 = new Dish("a fancy dish", 59.99);
        var result1 = dish.display();
        var result2 = dish2.display();
        assertEquals("Tomato soup - 5,00 GBP", result1);
        assertEquals("a fancy dish - 59,99 GBP", result2);
    }
}