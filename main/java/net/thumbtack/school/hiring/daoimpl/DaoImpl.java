package net.thumbtack.school.hiring.daoimpl;

import net.thumbtack.school.hiring.dao.DataAccessObject;
import net.thumbtack.school.hiring.database.DataBase;
import net.thumbtack.school.hiring.database.DataBaseException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class DaoImpl implements DataAccessObject {

    @Override
    public String logIn(String login, String password) throws DataBaseException {
        return DataBase.insertAuthorized(login, password);
    }

    @Override
    public void logOut(String token) throws DataBaseException {
        DataBase.removeAuthorized(token);
    }

    @Override
    public void leave(String token) throws DataBaseException {
        DataBase.remove(token);
    }

    @Override
    public void initDataBase(BufferedReader br) throws IOException {
        DataBase.initDataBase(br);
    }

    @Override
    public void saveDataBase(BufferedWriter bw) throws IOException {
        DataBase.saveDataBase(bw);
    }

    @Override
    public void initDataBase() {
        DataBase.initDataBase();
    }
}
