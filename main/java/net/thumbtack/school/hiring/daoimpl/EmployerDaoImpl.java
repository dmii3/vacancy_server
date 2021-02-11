package net.thumbtack.school.hiring.daoimpl;

import net.thumbtack.school.hiring.dao.EmployerDao;
import net.thumbtack.school.hiring.data.Employee;
import net.thumbtack.school.hiring.data.Employer;
import net.thumbtack.school.hiring.data.SearchMode;
import net.thumbtack.school.hiring.data.Vacancy;
import net.thumbtack.school.hiring.database.DataBase;
import net.thumbtack.school.hiring.database.DataBaseException;

import java.util.ArrayList;

public class EmployerDaoImpl extends DaoImpl implements EmployerDao {
    @Override
    public String insert(Employer employer) throws DataBaseException {
        return DataBase.insert(employer);
    }

    @Override
    public void change(String token, Employer employer) throws DataBaseException {
        DataBase.change(token, employer);
    }

    @Override
    public String insert(String token, Vacancy vacancy) throws DataBaseException {
        return DataBase.insert(token, vacancy);
    }

    @Override
    public void change(String token, String vacancyID, Vacancy vacancyUpd) throws DataBaseException {
        DataBase.change(token, vacancyID, vacancyUpd);
    }

    @Override
    public void remove(String token, String vacancyID) throws DataBaseException {
        DataBase.remove(token, vacancyID);
    }

    @Override
    public ArrayList<String> getEmployees(String token, String vacancyID, SearchMode searchMode) throws DataBaseException {
        return DataBase.getEmployees(token, vacancyID, searchMode);
    }

    @Override
    public void recruit(String token, String vacancyID, String employeeId) throws DataBaseException {
        DataBase.recruit(token, vacancyID, employeeId);
    }
}
