package net.thumbtack.school.hiring.dto.response;

import net.thumbtack.school.hiring.data.Employee;

import java.util.ArrayList;

public class GetEmployeesDtoResponse {
    private ArrayList<String> employees;

    public GetEmployeesDtoResponse(ArrayList<String> employees) {
        this.employees = employees;
    }

    public ArrayList<String> getEmployees() {
        return employees;
    }

    public void setEmployees(ArrayList<String> employees) {
        this.employees = employees;
    }
}
