package org.example;

public class Person {
    private final int personID;
    private final String name;
    private final String sex;
    private final String birthday;
    private final Department department;
    private  final  int salary;


    public Person(int personID, String name, String sex, String date, int salary, Department department) {
        this.personID = personID;
        this.name = name;
        this.sex = sex;
        this.birthday = date;
        this.salary = salary;
        this.department = department;
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

    @Override
    public String toString() {
        return "Person{" +
                "personID=" + personID +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday=" + birthday+ '\''
                + department.toString()+
                ", salary=" + salary + '}';
    }
}