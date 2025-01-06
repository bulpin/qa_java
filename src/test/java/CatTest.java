import com.example.Cat;
import com.example.Feline;
import com.example.Predator;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class CatTest {

    @Test
    public void testGetSoundReturnsMeow() {
        Feline feline = new Feline();
        Cat cat = new Cat(feline);
        assertEquals("Мяу", cat.getSound());
    }

    @Test
    public void testGetFoodReturnsCorrectFood() throws Exception {
        // Создаем мок для класса Feline
        Feline felineMock = Mockito.mock(Feline.class);
        Mockito.when(felineMock.eatMeat()).thenReturn(List.of("Мясо"));

        // Передаем мок Feline в конструктор Cat
        Cat cat = new Cat(felineMock);
        assertEquals(List.of("Мясо"), cat.getFood());
    }
}