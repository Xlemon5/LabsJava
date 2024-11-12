package org.example;

import java.util.UUID;

public class Department {

    private String departmentName;
    private UUID uuid = UUID.randomUUID();

    public Department(String departmentName, UUID uuid){
        this.departmentName = departmentName;
        this.uuid = uuid;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public UUID getUuid() {
        return uuid;
    }

}
