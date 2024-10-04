import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class MenuTest {
    Menu menu;
    @Mock
    Dish dish;
    @BeforeEach
     void setUp() {
        menu = new Menu();
    }

    @Test
    void displaysMenu() {
        dish = Mockito.mock();
        menu.add(dish);
        when(dish.display()).thenReturn("doo bee doo");
        assertEquals("doo bee doo\n", menu.display());
    }

    @Test
    void getsMenu() {
        dish = Mockito.mock();
        menu.add(dish);
        assertEquals(List.of(dish), menu.getMenu());
    }
}