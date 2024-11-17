package org.example;

import java.io.*;
import java.nio.file.*;
import java.text.ParseException;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

public class CSVReader {
    private static final String DELIMITER = ";";

    public static List<Person> parsePersons(Path sourcePath) throws IOException {
        PreprocessCSV(sourcePath);
        return GenerateListOfPersons(getProcessedPath(sourcePath));
    }

    private static Path getProcessedPath(Path sourcePath) {
        return Path.of(sourcePath.getParent().toString(), "edited_" + sourcePath.getFileName());
    }

    private static List<Person> GenerateListOfPersons(Path path) throws IOException {
        return Files.lines(path)
                .skip(1) // Пропуск заголовков
                .map(line -> {
                    try {
                        return getPerson(line);
                    } catch (ParseException e) {
                        System.err.println("Error parsing line: " + line + ", Error: " + e.getMessage());
                        return null; // Пропустить строки с ошибками
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private static void PreprocessCSV(Path sourcePath) throws IOException {
        if (!Files.exists(sourcePath)) {
            throw new IOException("File not found: " + sourcePath);
        }

        HashMap<String, UUID> departmentsWithKeys = new HashMap<>();
        Path outputPath = getProcessedPath(sourcePath);
        final AtomicBoolean isFirstLine = new AtomicBoolean(true);

        try (BufferedWriter bw = Files.newBufferedWriter(outputPath)) {
            Files.lines(sourcePath).forEach(line -> {
                try {
                    if (line.trim().isEmpty()) return; // Пропуск пустых строк
                    if (isFirstLine.get()) {
                        bw.write(line + DELIMITER + "department UUID");
                        isFirstLine.set(false);
                    } else {
                        String depName = getDepartmentNameFrom(line);
                        departmentsWithKeys.computeIfAbsent(depName, key -> UUID.randomUUID());
                        bw.write(line + DELIMITER + departmentsWithKeys.get(depName));
                    }
                    bw.newLine();
                } catch (Exception e) {
                    System.err.println("Error processing line: " + line + ", Error: " + e.getMessage());
                }
            });
        }
    }

    private static Person getPerson(String line) throws ParseException {
        String[] fields = line.split(DELIMITER);
        if (fields.length != 7) {
            throw new IllegalArgumentException("Invalid CSV line: " + line);
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

    private static String getDepartmentNameFrom(String line) throws ParseException {
        String[] fields = line.split(DELIMITER);
        if (fields.length < 6) {
            throw new IllegalArgumentException("Invalid CSV line: " + line);
        }
        return fields[4];
    }
}
