package net.thumbtack.school.hiring.services;

import net.thumbtack.school.hiring.dao.DataAccessObject;
import net.thumbtack.school.hiring.daoimpl.DaoImpl;

import java.io.*;

public class DataBaseService {
    private static DataAccessObject dao = new DaoImpl();

    public static void initDataBase(String savedDataFileName) throws IOException {
        if (savedDataFileName == null) {
            dao.initDataBase();
            return;
        }
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(savedDataFileName), "UTF-8"))) {
            dao.initDataBase(br);
        }
    }

    public static void saveDataBase(String saveDataFileName) throws IOException {
        if (saveDataFileName == null) {
            return;
        }
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(saveDataFileName), "UTF-8"))) {
            dao.saveDataBase(bw);
        }
    }
}
