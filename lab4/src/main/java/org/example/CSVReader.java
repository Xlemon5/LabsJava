package org.example;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

public class CSVReader {
    private static final String DELIMITER = ";";

    /**
     * Метод для парсинга списка объектов Person из CSV-файла
     * @param sourcePath путь к исходному файлу
     * @return Список объектов Person
     * @throws IOException если возникает ошибка ввода-вывода
     */
    public static List<Person> parsePersons(Path sourcePath) throws IOException {
        try {
            // Предобработка данных
            PreprocessCSV(sourcePath);
            // Чтение обработанных данных и создание объектов Person
            return GenerateListOfPersons(getProcessedPath(sourcePath));
        } catch (RuntimeException e) {
            throw new IOException("Error processing CSV: " + e.getMessage(), e);
        }
    }

    /**
     * Получить путь для обработанного файла
     * @param sourcePath исходный путь
     * @return путь к обработанному файлу
     */
    private static Path getProcessedPath(Path sourcePath) {
        return Path.of(sourcePath.getParent().toString(), "edited_" + sourcePath.getFileName());
    }

    /**
     * Генерирует список объектов Person из обработанного CSV-файла
     * @param path путь к обработанному CSV
     * @return список объектов Person
     * @throws IOException если ошибка при чтении файла
     */
    private static List<Person> GenerateListOfPersons(Path path) throws IOException {
        return Files.lines(path)
                .skip(1) // Пропуск заголовков
                .map(line -> {
                    try {
                        return getPerson(line);
                    } catch (RuntimeException e) {
                        // Логируем ошибку, но продолжаем обработку
                        System.err.println("Error parsing line: " + line + ", Error: " + e.getMessage());
                        return null; // Пропускаем ошибочные строки
                    }
                })
                .filter(Objects::nonNull) // Убираем строки с ошибками
                .collect(Collectors.toList());
    }

    /**
     * Предобрабатывает CSV-файл, добавляя UUID для департаментов
     * @param sourcePath путь к исходному CSV-файлу
     * @throws IOException если ошибка при чтении или записи
     */
    static void PreprocessCSV(Path sourcePath) throws IOException {
        if (!Files.exists(sourcePath)) {
            throw new IOException("File not found: " + sourcePath);
        }

        // Словарь для хранения UUID департаментов
        HashMap<String, UUID> departmentsWithKeys = new HashMap<>();
        Path outputPath = getProcessedPath(sourcePath);
        final AtomicBoolean isFirstLine = new AtomicBoolean(true);

        try (BufferedWriter bw = Files.newBufferedWriter(outputPath)) {
            Files.lines(sourcePath).forEach(line -> {
                try {
                    if (line.trim().isEmpty()) return; // Пропуск пустых строк
                    if (isFirstLine.get()) {
                        bw.write(line + DELIMITER + "department UUID");
                        isFirstLine.set(false); // Пропускаем заголовок
                    } else {
                        // Получаем название департамента и генерируем UUID
                        String depName = getDepartmentNameFrom(line);
                        departmentsWithKeys.computeIfAbsent(depName, key -> UUID.randomUUID());
                        // Записываем строку с добавленным UUID
                        bw.write(line + DELIMITER + departmentsWithKeys.get(depName));
                    }
                    bw.newLine(); // Переход на новую строку
                } catch (Exception e) {
                    System.err.println("Error processing line: " + line + ", Error: " + e.getMessage());
                }
            });
        }
    }

    /**
     * Преобразует строку CSV в объект Person
     * @param line строка из CSV
     * @return объект Person
     * @throws RuntimeException если строка невалидна
     */
    static Person getPerson(String line) throws RuntimeException {
        String[] fields = line.split(DELIMITER);
        if (fields.length != 7) { // Ожидаем, что в каждой строке будет 7 полей
            throw new RuntimeException("Invalid CSV line: " + line);  // выбрасываем исключение
        }
        return new Person(
                Integer.parseInt(fields[0]),
                fields[1],
                fields[2],
                fields[3],
                Integer.parseInt(fields[5]),
                new Department(fields[4], UUID.fromString(fields[6]))
        );
    }

    /**
     * Получение названия департамента из строки CSV
     * @param line строка из CSV
     * @return название департамента
     * @throws RuntimeException если строка невалидна
     */
    static String getDepartmentNameFrom(String line) throws RuntimeException {
        String[] fields = line.split(DELIMITER);
        if (fields.length < 6) { // Если в строке меньше 6 полей
            throw new IllegalArgumentException("Invalid CSV line: " + line); // Проблема с форматом строки
        }
        return fields[4]; // Название департамента - пятый элемент
    }
}
