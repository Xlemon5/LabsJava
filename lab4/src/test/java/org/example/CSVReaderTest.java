package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.UUID;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.io.File;
import java.util.Comparator;


public class CSVReaderTest {

    @Test
    public void testParsePersons() throws IOException {
        // Создаем временную CSV-директорию и файл для теста
        Path tempDir = Files.createTempDirectory("testData");
        Path testCSV = tempDir.resolve("test.csv");

        String csvContent = "personId;Full Name;Sex;Birth Date;Department;Salary\n" +
                "1;Алексей Смирнов;Мужской;01.01.1990;IT;50000\n" +
                "2;Мария Иванова;Женский;15.05.1985;HR;60000";

        Files.writeString(testCSV, csvContent);

        // Тестируем метод parsePersons
        List<Person> persons = CSVReader.parsePersons(testCSV);

        assertEquals(2, persons.size());

        // проверки объектов person1 и person2

        // Удаляем временную директорию вместе со всеми файлами внутри нее
        Files.walk(tempDir)
                .sorted(Comparator.reverseOrder())
                .map(Path::toFile)
                .forEach(File::delete);
    }


    @Test
    public void testGetPersonValidLine() {
        String line = "1;Алексей Смирнов;Мужской;01.01.1990;IT;50000;" + UUID.randomUUID();

        Person person = CSVReader.getPerson(line);

        assertEquals(1, person.getPersonID());
        assertEquals("Алексей Смирнов", person.getName());
        assertEquals("Мужской", person.getSex());
        assertEquals("01.01.1990", person.getBirthday());
        assertEquals(50000, person.getSalary());
        assertEquals("IT", person.getDepartment().getDepartmentName());
        assertNotNull(person.getDepartment().getDepartmentID());
    }

    @Test
    public void testGetPersonInvalidLine() {
        String line = "Invalid;Line;Data";

        Exception exception = assertThrows(RuntimeException.class, () -> {
            CSVReader.getPerson(line);
        });

        String expectedMessage = "Invalid CSV line";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
