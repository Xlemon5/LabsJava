package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ArrayListResultTest {

    private ArrayListResult arrayListResult;
    private final int operationCount = 1000;

    @BeforeEach
    public void setUp() {
        arrayListResult = new ArrayListResult(operationCount);
    }

    @Test
    public void testAddPerformanceTime() {
        long timeTaken = arrayListResult.AddPerformanceTime();
        assertTrue(timeTaken >= 0, "Время добавления должно быть неотрицательным");
    }

    @Test
    public void testGetPerformanceTime() {
        arrayListResult.AddPerformanceTime(); // Заполнить коллекцию для проверки
        long timeTaken = arrayListResult.GetPerformanceTime();
        assertTrue(timeTaken >= 0, "Время доступа должно быть неотрицательным");
    }

    @Test
    public void testDeletePerformanceTime() {
        arrayListResult.AddPerformanceTime(); // Заполнить коллекцию для проверки
        long timeTaken = arrayListResult.DeletePerformanceTime();
        assertTrue(timeTaken >= 0, "Время удаления должно быть неотрицательным");
    }
}
