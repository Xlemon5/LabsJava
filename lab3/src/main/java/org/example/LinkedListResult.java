package org.example;

import java.util.LinkedList;
import java.util.List;

public class LinkedListResult implements PerformanceResult{
    List<Integer> workLinkedList = new LinkedList<>();
    int operationCount;

    public LinkedListResult(int givenOperationCount){
        operationCount = givenOperationCount;
    }

    private void fillArrayList(){
        workLinkedList.clear();
        for (int i = 0; i < operationCount; i++){
            workLinkedList.add(i);
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
                workLinkedList.get(i);
            }
        });
    }

    @Override
    public long DeletePerformanceTime(){
        fillArrayList();
        return measureTime(() ->{
            for(int i = operationCount - 1; i >= 0;i-- ){
                workLinkedList.remove(i);
            }
        });
    }
}
