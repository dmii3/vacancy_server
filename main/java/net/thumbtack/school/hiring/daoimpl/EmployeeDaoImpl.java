package net.thumbtack.school.hiring.daoimpl;

import net.thumbtack.school.hiring.dao.EmployeeDao;
import net.thumbtack.school.hiring.data.*;
import net.thumbtack.school.hiring.database.DataBase;
import net.thumbtack.school.hiring.database.DataBaseException;

import java.util.ArrayList;

public class EmployeeDaoImpl extends DaoImpl implements EmployeeDao {

    @Override
    public String insert(Employee employee) throws DataBaseException {
        return DataBase.insert(employee);
    }

    @Override
    public void change(String token, Employee employee) throws DataBaseException {
        DataBase.change(token, employee);
    }

    @Override
    public void setProfileVisibility(String token, Visibility visibility) throws DataBaseException {
        DataBase.insert(token, visibility);
    }

    @Override
    public void addSkill(String token, Skill skill) throws DataBaseException {
        DataBase.insert(token, skill);
    }

    @Override
    public void changeSkill(String token, Skill skill) throws DataBaseException {
        DataBase.change(token, skill);
    }

    @Override
    public void deleteSkill(String token, Skill skill) throws DataBaseException {
        DataBase.remove(token, skill);
    }

    @Override
    public ArrayList<Vacancy> getVacncies(String token, SearchMode searchMode) throws DataBaseException {
        return DataBase.getVacancies(token, searchMode);
    }
}
