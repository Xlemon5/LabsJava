package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LinkedListResultTest {

    private LinkedListResult linkedListResult;
    private final int operationCount = 1000;

    @BeforeEach
    public void setUp() {
        linkedListResult = new LinkedListResult(operationCount);
    }

    @Test
    public void testAddPerformanceTime() {
        long timeTaken = linkedListResult.AddPerformanceTime();
        assertTrue(timeTaken >= 0, "Время добавления должно быть неотрицательным");
    }

    @Test
    public void testGetPerformanceTime() {
        linkedListResult.AddPerformanceTime(); // Заполнить коллекцию для проверки
        long timeTaken = linkedListResult.GetPerformanceTime();
        assertTrue(timeTaken >= 0, "Время доступа должно быть неотрицательным");
    }

    @Test
    public void testDeletePerformanceTime() {
        linkedListResult.AddPerformanceTime(); // Заполнить коллекцию для проверки
        long timeTaken = linkedListResult.DeletePerformanceTime();
        assertTrue(timeTaken >= 0, "Время удаления должно быть неотрицательным");
    }
}
