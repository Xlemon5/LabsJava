package org.example;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

public class CsvReader {

    public static List<Person> parsePersons(String sourcePath) throws IOException{
        preprocessCSV(path);
        return generateListOfPersons(getPath(sourcePath));
    }

    public static String getPath(){

    }

    private static List<Person> generateListOfPersons(String path) throws IOException{

    }

    private static void preprocessCSV(String path) throws  IOException{

    }


}
