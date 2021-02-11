package net.thumbtack.school.hiring.services;

import com.google.gson.Gson;
import net.thumbtack.school.hiring.dao.EmployeeDao;
import net.thumbtack.school.hiring.daoimpl.EmployeeDaoImpl;
import net.thumbtack.school.hiring.data.Skill;
import net.thumbtack.school.hiring.data.Vacancy;
import net.thumbtack.school.hiring.database.DataBaseException;
import net.thumbtack.school.hiring.dto.request.*;
import net.thumbtack.school.hiring.dto.response.EmptySuccessDtoResponse;
import net.thumbtack.school.hiring.dto.response.ErrorDtoResponse;
import net.thumbtack.school.hiring.dto.response.GetVacanciesDtoResponse;
import net.thumbtack.school.hiring.utils.DataTransferException;
import net.thumbtack.school.hiring.dto.response.RegisterUserDtoResponse;
import net.thumbtack.school.hiring.data.Employee;

import java.util.ArrayList;

public class EmployeeService {
    private static EmployeeDao dao = new EmployeeDaoImpl();

    public static String register(String requestJsonString) {
        try {
            RegisterEmployeeDtoRequest dtoRequest = new Gson().fromJson(requestJsonString, RegisterEmployeeDtoRequest.class);
            dtoRequest.validate();
            Employee employee = new Employee(
                    dtoRequest.getLogin(),
                    dtoRequest.getPassword(),
                    dtoRequest.getSurname(),
                    dtoRequest.getName(),
                    dtoRequest.getPatronymic(),
                    dtoRequest.geteMail()
            );
            // REVU make class member, do not create every time
            // also, use interface, i.e.
            // EmployeeDao dao = new EmployeeDaoImpl();
            String dataBaseResponse = dao.insert(employee);
            RegisterUserDtoResponse dtoResponse = new RegisterUserDtoResponse(dataBaseResponse);
            return new Gson().toJson(dtoResponse);
        } catch (DataTransferException e) {
            ErrorDtoResponse dtoResponse = new ErrorDtoResponse(e.getErrorCode().getErrorString());
            return new Gson().toJson(dtoResponse);
        } catch (DataBaseException e) {
            ErrorDtoResponse dtoResponse = new ErrorDtoResponse(e.getErrorCode().getErrorString());
            return new Gson().toJson(dtoResponse);
        }
    }

    public static String changeData(String requestJsonString) {
        try {
            ChangeEmployeeDtoRequest dtoRequest = new Gson().fromJson(requestJsonString, ChangeEmployeeDtoRequest.class);
            dtoRequest.validate();
            Employee employee = new Employee(
                    dtoRequest.getLogin(),
                    dtoRequest.getPassword(),
                    dtoRequest.getSurname(),
                    dtoRequest.getName(),
                    dtoRequest.getPatronymic(),
                    dtoRequest.geteMail()
            );
            dao.change(dtoRequest.getToken(), employee);
            // REVU do not use hardcoded strings
            // Better create class EmptySuccessDtoResponse {}
            // may be, in future something will be added to it - who knows ?
            return new Gson().toJson(new EmptySuccessDtoResponse());
        } catch (DataTransferException e) {
            ErrorDtoResponse dtoResponse = new ErrorDtoResponse(e.getErrorCode().getErrorString());
            return new Gson().toJson(dtoResponse);
        } catch (DataBaseException e) {
            ErrorDtoResponse dtoResponse = new ErrorDtoResponse(e.getErrorCode().getErrorString());
            return new Gson().toJson(dtoResponse);
        }
    }

    public static String SetProfileVisibility(String requestJsonString) {
        try {
            SetProfileVisibilityDtoRequest dtoRequest = new Gson().fromJson(requestJsonString, SetProfileVisibilityDtoRequest.class);
            dtoRequest.validate();
            dao.setProfileVisibility(dtoRequest.getToken(), dtoRequest.getVisibility());
            return new Gson().toJson(new EmptySuccessDtoResponse());
        } catch (DataTransferException e) {
            ErrorDtoResponse dtoResponse = new ErrorDtoResponse(e.getErrorCode().getErrorString());
            return new Gson().toJson(dtoResponse);
        } catch (DataBaseException e) {
            ErrorDtoResponse dtoResponse = new ErrorDtoResponse(e.getErrorCode().getErrorString());
            return new Gson().toJson(dtoResponse);
        }
    }

    public static String addSkill(String requestJsonString) {
        try {
            AddSkillDtoRequest dtoRequest = new Gson().fromJson(requestJsonString, AddSkillDtoRequest.class);
            dtoRequest.validate();
            Skill skill = new Skill(dtoRequest.getSkillName(), dtoRequest.getSkillLevel());
            dao.addSkill(dtoRequest.getToken(), skill);
            return new Gson().toJson(new EmptySuccessDtoResponse());
        } catch (DataTransferException e) {
            ErrorDtoResponse dtoResponse = new ErrorDtoResponse(e.getErrorCode().getErrorString());
            return new Gson().toJson(dtoResponse);
        } catch (DataBaseException e) {
            ErrorDtoResponse dtoResponse = new ErrorDtoResponse(e.getErrorCode().getErrorString());
            return new Gson().toJson(dtoResponse);
        }
    }

    public static String changeSkill(String requestJsonString) {
        try {
            ChangeSkillDtoRequest dtoRequest = new Gson().fromJson(requestJsonString, ChangeSkillDtoRequest.class);
            dtoRequest.validate();
            Skill skill = new Skill(dtoRequest.getSkillName(), dtoRequest.getSkillLevel());
            dao.changeSkill(dtoRequest.getToken(), skill);
            return new Gson().toJson(new EmptySuccessDtoResponse());
        } catch (DataTransferException e) {
            ErrorDtoResponse dtoResponse = new ErrorDtoResponse(e.getErrorCode().getErrorString());
            return new Gson().toJson(dtoResponse);
        } catch (DataBaseException e) {
            ErrorDtoResponse dtoResponse = new ErrorDtoResponse(e.getErrorCode().getErrorString());
            return new Gson().toJson(dtoResponse);
        }
    }

    public static String deleteSkill(String requestJsonString) {
        try {
            DeleteSkillDtoRequest dtoRequest = new Gson().fromJson(requestJsonString, DeleteSkillDtoRequest.class);
            dtoRequest.validate();
            Skill skill = new Skill(dtoRequest.getSkillName(), dtoRequest.getSkillLevel());
            dao.deleteSkill(dtoRequest.getToken(), skill);
            return new Gson().toJson(new EmptySuccessDtoResponse());
        } catch (DataTransferException e) {
            ErrorDtoResponse dtoResponse = new ErrorDtoResponse(e.getErrorCode().getErrorString());
            return new Gson().toJson(dtoResponse);
        } catch (DataBaseException e) {
            ErrorDtoResponse dtoResponse = new ErrorDtoResponse(e.getErrorCode().getErrorString());
            return new Gson().toJson(dtoResponse);
        }
    }

    public static String getVacancies(String requestJsonString) {
        try {
            GetVacanciesDtoRequest dtoRequest = new Gson().fromJson(requestJsonString, GetVacanciesDtoRequest.class);
            dtoRequest.validate();
            ArrayList<Vacancy> dataBaseResponse = dao.getVacncies(dtoRequest.getToken(), dtoRequest.getSearchMode());
            GetVacanciesDtoResponse dtoResponse = new GetVacanciesDtoResponse(dataBaseResponse);
            return new Gson().toJson(dtoResponse);
        } catch (DataTransferException e) {
            ErrorDtoResponse dtoResponse = new ErrorDtoResponse(e.getErrorCode().getErrorString());
            return new Gson().toJson(dtoResponse);
        } catch (DataBaseException e) {
            ErrorDtoResponse dtoResponse = new ErrorDtoResponse(e.getErrorCode().getErrorString());
            return new Gson().toJson(dtoResponse);
        }
    }
}
