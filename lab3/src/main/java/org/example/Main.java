package org.example;


public class Main {
    public static void main(String[] args){
        int operationCount = 150000;
        measurePerformance performance = new measurePerformance(operationCount);
        performance.printTimeComparison();
    }
}