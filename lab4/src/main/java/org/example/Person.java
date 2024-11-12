package org.example;

public class Person {
    private final int personID;
    private final String name;
    private final String sex;
    private final Department department;
    private final int salary;
    private final String birthday;

    public Person(int personID, String name, String sex, Department department, int salary, String birthday){
        this.personID = personID;
        this.name = name;
        this.sex = sex;
        this.department = department;
        this.salary = salary;
        this.birthday = birthday;
    }

    public int getPersonID(){
        return personID;
    }

    public String getName(){
        return name;
    }

    public String getSex(){
        return sex;
    }

    public int getSalary() {
        return salary;
    }

    public Department getDepartment() {
        return department;
    }

    public String getBirthday() {
        return birthday;
    }
}
