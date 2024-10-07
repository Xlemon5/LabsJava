import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class containerTest {

    private container<Integer> container;

    @BeforeEach
    void setUp() {
        container = new container<>();
    }

    @Test
    void testAdd() {
        container.add(10);
        container.add(20);
        assertEquals(2, container.size(), "Размер контейнера должен быть 2 после добавления 2 элементов");
        assertEquals(10, container.get(0), "Первый элемент должен быть 10");
        assertEquals(20, container.get(1), "Второй элемент должен быть 20");
    }

    @Test
    void testRemove() {
        container.add(10);
        container.add(20);
        container.add(30);

        container.remove(1); // Удаляем элемент с индексом 1 (20)
        assertEquals(2, container.size(), "Размер контейнера должен быть 2 после удаления элемента");
        assertEquals(30, container.get(1), "Теперь элемент с индексом 1 должен быть 30");
    }

    @Test
    void testGetOutOfBounds() {
        container.add(10);
        Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> {
            container.get(5); // Недопустимый индекс
        });

        String expectedMessage = "Индекс вне допустимого диапазона";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testRemoveOutOfBounds() {
        container.add(10);
        Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> {
            container.remove(5); // Недопустимый индекс
        });

        String expectedMessage = "Индекс вне допустимого диапазона";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testResize() {
        for (int i = 0; i < 11; i++) {
            container.add(i);
        }
        assertEquals(11, container.size(), "Размер контейнера должен быть 11 после добавления 11 элементов");
        assertEquals(10, container.get(10), "Последний элемент должен быть 10");
    }

    @Test
    void testPrintElems() {
        container.add(10);
        container.add(20);
        container.add(30);

        // Перехват консольного вывода, чтобы убедиться, что элементы печатаются правильно
        java.io.ByteArrayOutputStream outContent = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(outContent));

        container.printElems();

        assertEquals("Элементы контейнера: 10 20 30 \n", outContent.toString(), "Вывод должен содержать все элементы");
    }
}
