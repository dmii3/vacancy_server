package net.thumbtack.school.hiring.dao;

import net.thumbtack.school.hiring.data.Employee;
import net.thumbtack.school.hiring.data.Employer;
import net.thumbtack.school.hiring.data.SearchMode;
import net.thumbtack.school.hiring.data.Vacancy;
import net.thumbtack.school.hiring.database.DataBaseException;

import java.util.ArrayList;

public interface EmployerDao {
    String insert(Employer employer) throws DataBaseException;
    void change(String token, Employer employer) throws DataBaseException;
    String insert(String token ,Vacancy vacancy) throws DataBaseException;
    void change(String token, String vacancyID, Vacancy vacancyUpd) throws DataBaseException;
    void remove(String token, String vacancyID) throws DataBaseException;
    ArrayList<String> getEmployees(String token, String vacancyID, SearchMode searchMode) throws DataBaseException;
    void recruit(String token, String vacancyID, String employeeId) throws DataBaseException;
}
