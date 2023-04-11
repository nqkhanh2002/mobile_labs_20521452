package com.example.mobileproject;

public class Person {
    private String fullName;
    private double grossSalary;
    private double netSalary;

    public Person(String fullName, double grossSalary) {
        this.fullName = fullName;
        this.grossSalary = grossSalary;
        this.netSalary = calculateNetSalary();
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public double getGrossSalary() {
        return grossSalary;
    }

    public void setGrossSalary(double grossSalary) {
        this.grossSalary = grossSalary;
        this.netSalary = calculateNetSalary();
    }

    public double getNetSalary() {
        return netSalary;
    }

    public void setNetSalary(double netSalary) {
        this.netSalary = netSalary;
    }

    private double calculateNetSalary() {
        double taxableIncome = grossSalary - grossSalary * 0.105;
        if (taxableIncome <= 11000000) {
            netSalary = taxableIncome;
        } else {
            netSalary = 11000000 + (taxableIncome - 11000000) * (1 - 0.05);
        }
        return netSalary;
    }

    @Override
    public String toString() {
        return fullName + ": " + netSalary;
    }
}
