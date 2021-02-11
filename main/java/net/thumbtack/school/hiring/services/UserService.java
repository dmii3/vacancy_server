package net.thumbtack.school.hiring.services;

import com.google.gson.Gson;
import net.thumbtack.school.hiring.daoimpl.DaoImpl;
import net.thumbtack.school.hiring.database.DataBaseException;
import net.thumbtack.school.hiring.dto.request.LeaveServerDtoRequest;
import net.thumbtack.school.hiring.dto.request.LogInDtoRequest;
import net.thumbtack.school.hiring.dto.request.LogOutDtoRequest;
import net.thumbtack.school.hiring.dto.response.EmptySuccessDtoResponse;
import net.thumbtack.school.hiring.dto.response.ErrorDtoResponse;
import net.thumbtack.school.hiring.dto.response.LogInDtoResponse;

public class UserService {
    private static DaoImpl dao = new DaoImpl();

    public static String logIn(String requestJsonString) {
        try {
            LogInDtoRequest dtoRequest = new Gson().fromJson(requestJsonString, LogInDtoRequest.class);
            String dataBaseResponse = dao.logIn(dtoRequest.getLogin(), dtoRequest.getPassword());
            LogInDtoResponse dtoResponse = new LogInDtoResponse(dataBaseResponse);
            return new Gson().toJson(dtoResponse);
        } catch (DataBaseException e) {
            ErrorDtoResponse dtoResponse = new ErrorDtoResponse(e.getErrorCode().getErrorString());
            return new Gson().toJson(dtoResponse);
        }
    }

    public static String logOut(String requestJsonString) {
        try {
            LogOutDtoRequest dtoRequest = new Gson().fromJson(requestJsonString, LogOutDtoRequest.class);
            dao.logOut(dtoRequest.getToken());
            return new Gson().toJson(new EmptySuccessDtoResponse());
        } catch (DataBaseException e) {
            ErrorDtoResponse dtoResponse = new ErrorDtoResponse(e.getErrorCode().getErrorString());
            return new Gson().toJson(dtoResponse);
        }
    }

    public static String leaveServer(String requestJsonString) {
        try {
            LeaveServerDtoRequest dtoRequest = new Gson().fromJson(requestJsonString, LeaveServerDtoRequest.class);
            dao.leave(dtoRequest.getToken());
            return new Gson().toJson(new EmptySuccessDtoResponse());
        } catch (DataBaseException e) {
            ErrorDtoResponse dtoResponse = new ErrorDtoResponse(e.getErrorCode().getErrorString());
            return new Gson().toJson(dtoResponse);
        }
    }
}
