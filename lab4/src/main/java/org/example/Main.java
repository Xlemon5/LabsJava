package org.example;

import java.io.FileReader;
import java.io.IOException;

import com.opencsv.CSVReader;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

public class Main {
    public static void main(String[] args) {
        String filePath = "/Users/ilya/desktop/labsJava/lab4/src/foreign_names.csv";
        try (CSVReader csvReader = new CSVReaderBuilder(new FileReader(filePath))
                .withCSVParser(new CSVParserBuilder().withSeparator(';').build()) // Устанавливаем двоеточие как разделитель
                .build()) {
            String[] lines;
            while ((lines = csvReader.readNext()) != null) {
                for (String line : lines) {
                    System.out.print(line + " ");
                }
                System.out.println();  // переход на новую строку после каждой строки CSV
            }
        } catch (IOException | CsvValidationException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }
}
