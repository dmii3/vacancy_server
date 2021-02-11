package net.thumbtack.school.hiring.dao;

import net.thumbtack.school.hiring.data.*;
import net.thumbtack.school.hiring.database.DataBaseException;

import java.util.ArrayList;

public interface EmployeeDao {
    String insert(Employee employee) throws DataBaseException;
    void change(String token, Employee employee) throws DataBaseException;
    void setProfileVisibility(String token, Visibility visibility) throws DataBaseException;
    void addSkill(String token, Skill skill) throws DataBaseException;
    void changeSkill(String token, Skill skill) throws DataBaseException;
    void deleteSkill(String token, Skill skill) throws DataBaseException;
    ArrayList<Vacancy> getVacncies(String token, SearchMode searchMode) throws DataBaseException;
}