package org.example;

import java.util.ArrayList;
import java.util.List;

public class ArrayListResult implements PerformanceResult {
    List<Integer> workArrayList = new ArrayList<>();
    int operationCount;

    public ArrayListResult(int givenOperationCount){
        operationCount = givenOperationCount;
    }

    private void fillArrayList(){
        workArrayList.clear();
        for (int i = 0; i < operationCount; i++){
            workArrayList.add(i);
        }
    }

    @Override
    public long AddPerformanceTime(){
        return measureTime(() -> {
           fillArrayList();
        });
    }

    @Override
    public long GetPerformanceTime(){
        return measureTime(() ->{
           for (int i = 0; i < operationCount; i++){
               workArrayList.get(i);
           }
        });
    }

    @Override
    public long DeletePerformanceTime(){
        fillArrayList();
        return measureTime(() ->{
            for(int i = operationCount - 1; i >= 0;i-- ){
                workArrayList.remove(i);
            }
        });
    }
}
