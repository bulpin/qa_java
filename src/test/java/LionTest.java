import com.example.Feline;
import com.example.Lion;
import com.example.Predator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;
import static org.junit.Assert.*;
import java.util.List;

@RunWith(Parameterized.class)
public class LionParameterizedTest {

    private final String sex;
    private final boolean expectedHasMane;
    private final boolean isValid;

    public LionParameterizedTest(String sex, boolean expectedHasMane, boolean isValid) {
        this.sex = sex;
        this.expectedHasMane = expectedHasMane;
        this.isValid = isValid;
    }

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][] {
                {"Самец", true, true},
                {"Самка", false, true},
                {"Неправильный", false, false}
        };
    }

    @Test
    public void testLionBehavior() {
        try {
            // Мокируем зависимость Predator
            Predator predatorMock = Mockito.mock(Predator.class);
            Mockito.when(predatorMock.eatMeat()).thenReturn(List.of("Мясо"));

            // Создаем экземпляр Lion
            Lion lion = new Lion(sex, predatorMock);

            // Проверяем наличие гривы
            assertEquals(expectedHasMane, lion.doesHaveMane());

            // Проверяем метод getFood
            assertEquals(List.of("Мясо"), lion.getFood());

            // Проверяем метод getKittens только если Predator — Feline
            if (predatorMock instanceof Feline) {
                Feline felineMock = (Feline) predatorMock;
                Mockito.when(felineMock.getKittens()).thenReturn(3);
                assertEquals(3, lion.getKittens());
            }
        } catch (Exception e) {
            if (isValid) {
                fail("Тест упал из-за исключения при валидных данных: " + e.getMessage());
            } else {
                assertEquals("Используйте допустимые значения пола животного - Самец или Самка", e.getMessage());
            }
        }
    }
}
