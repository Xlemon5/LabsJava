package org.example;

public class measurePerformance {
    public int givenOperationCount;

    public PerformanceResult performResultArrayList;
    public PerformanceResult performResultLinkedArrayList;


    public measurePerformance(int givenOperationCount){
        this.givenOperationCount = givenOperationCount;
        this.performResultArrayList = new ArrayListResult(givenOperationCount);
        this.performResultLinkedArrayList = new LinkedListResult(givenOperationCount);
    }

    public void printTimeComparison(){
        long addArrayListTime = performResultArrayList.AddPerformanceTime();
        long getArrayListTime = performResultArrayList.GetPerformanceTime();
        long deleteArrayListTime = performResultArrayList.DeletePerformanceTime();

        long addLinkedListTime = performResultLinkedArrayList.AddPerformanceTime();
        long getLinkedListTime = performResultLinkedArrayList.GetPerformanceTime();
        long deleteLinkedListTime = performResultLinkedArrayList.DeletePerformanceTime();

        System.out.printf("%-10s | %-15s | %-20s | %-20s | %-20s\n", "Collection", "Number of items", "ms adding", "ms getting", "ms deleting");
        System.out.println("-----------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-10s | %-15d | %-20d | %-20d | %-20d\n", "ArrayList", givenOperationCount, addArrayListTime,getArrayListTime, deleteArrayListTime);
        System.out.printf("%-10s | %-15d | %-20d | %-20d | %-20d\n", "LinkedList", givenOperationCount, addLinkedListTime,getLinkedListTime, deleteLinkedListTime);
        System.out.print("\n\n");
    }

}
