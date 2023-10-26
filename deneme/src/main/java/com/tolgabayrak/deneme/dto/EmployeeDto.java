package com.tolgabayrak.deneme.dto;



public class EmployeeDto {
    private String name;

    public EmployeeDto(String name) {
        this.name = name;
    }

    public EmployeeDto(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
