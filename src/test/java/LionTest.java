import com.example.Lion;
import com.example.Predator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.Assert.*;

public class LionTest {

    @Test
    public void testGetKittens() throws Exception {
        // Мокируем Predator
        Predator predatorMock = Mockito.mock(Predator.class);
        Mockito.when(predatorMock.getKittens()).thenReturn(3);

        // Создаем экземпляр Lion с зависимостью Predator
        Lion lion = new Lion("Самец", predatorMock);

        // Проверяем метод getKittens
        assertEquals(3, lion.getKittens());
    }

    @Test
    public void testLionConstructorWithValidSex() throws Exception {
        // Мокируем Predator
        Predator predatorMock = Mockito.mock(Predator.class);

        // Проверяем конструктор для Самца
        Lion maleLion = new Lion("Самец", predatorMock);
        assertTrue(maleLion.doesHaveMane());

        // Проверяем конструктор для Самки
        Lion femaleLion = new Lion("Самка", predatorMock);
        assertFalse(femaleLion.doesHaveMane());
    }

    @Test(expected = Exception.class)
    public void testLionConstructorWithInvalidSex() throws Exception {
        // Мокируем Predator
        Predator predatorMock = Mockito.mock(Predator.class);

        // Проверяем выброс исключения при неправильном поле
        new Lion("Неправильный", predatorMock);
    }
    @Test
    public void testGetFood() throws Exception {
        // Мокируем Predator
        Predator predatorMock = Mockito.mock(Predator.class);
        Mockito.when(predatorMock.eatMeat()).thenReturn(List.of("Мясо"));

        // Создаем экземпляр Lion с зависимостью Predator
        Lion lion = new Lion("Самец", predatorMock);

        // Проверяем метод getFood
        assertEquals(List.of("Мясо"), lion.getFood());

        // Проверяем, что метод eatMeat был вызван у мока
        Mockito.verify(predatorMock).eatMeat();
    }
}
