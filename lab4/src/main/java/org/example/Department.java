package org.example;

import java.util.UUID;

public class Department {

    private String departmentName;
    private UUID departmentID = UUID.randomUUID();

    public Department(String departmentName, UUID departmentID){
        this.departmentName = departmentName;
        this.departmentID = departmentID;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public UUID getDepartmentID() {
        return departmentID;
    }

    @Override
    public String toString() {
        return
                "departmentName=" + departmentName + '\'' +
                        ", departmentID=" + departmentID
                ;
    }
}