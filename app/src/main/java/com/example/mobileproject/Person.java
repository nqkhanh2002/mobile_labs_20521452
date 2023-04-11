package com.example.mobileproject;

public class Person {
    private String fullName;
    private double gSalary;
    private double nSalary;

    public Person (String fullName, double gSalary) {
        this.fullName = fullName;
        this.gSalary = gSalary;
        this.nSalary = calSalary();
    }

    public String getFullName(){
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public double getGSalary(){
        return gSalary;
    }
    public void setGSalary(double gSalary){
        this.gSalary = gSalary;
    }

    public double getNSalary(){
        return nSalary;
    }
    public void setNSalary(double nSalary){
        this.nSalary = nSalary;
    }

    public double calSalary(){
        double salary;
        salary = gSalary - gSalary * 0.105;
        if (salary <= 11000000) {
            nSalary = salary;
        }
        else {
            nSalary = 11000000 + (salary - 11000000) * (1 - 0.05);
        }
        return nSalary;
    }


    @Override
    public String toString(){
        return fullName + ": " + nSalary;
    }
}