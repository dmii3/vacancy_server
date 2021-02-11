package net.thumbtack.school.hiring.dao;

import net.thumbtack.school.hiring.database.DataBaseException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public interface DataAccessObject {
    String logIn(String login, String password) throws DataBaseException;
    void logOut(String token) throws DataBaseException;
    void leave(String token) throws DataBaseException;

    void initDataBase(BufferedReader br) throws IOException;
    void saveDataBase(BufferedWriter bw) throws IOException;
    void initDataBase();
}
