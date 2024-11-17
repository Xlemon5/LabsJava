package org.example;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

public class Main {
    public static void main(String[] args) throws IOException {
        Path sourcePath = Path.of("src", "foreign_names.csv");
        CSVReader.parsePersons(sourcePath);
    }
}