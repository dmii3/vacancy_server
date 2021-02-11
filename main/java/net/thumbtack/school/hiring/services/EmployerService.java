package net.thumbtack.school.hiring.services;

import com.google.gson.Gson;
import net.thumbtack.school.hiring.dao.EmployerDao;
import net.thumbtack.school.hiring.daoimpl.EmployerDaoImpl;
import net.thumbtack.school.hiring.data.Employee;
import net.thumbtack.school.hiring.data.Vacancy;
import net.thumbtack.school.hiring.data.Visibility;
import net.thumbtack.school.hiring.database.DataBaseException;
import net.thumbtack.school.hiring.dto.request.*;
import net.thumbtack.school.hiring.dto.response.*;
import net.thumbtack.school.hiring.utils.DataTransferException;
import net.thumbtack.school.hiring.data.Employer;

import java.util.ArrayList;

public class EmployerService {
    private static EmployerDao dao = new EmployerDaoImpl();

    public static String register(String requestJsonString) {
        try {
            RegisterEmployerDtoRequest dtoRequest = new Gson().fromJson(requestJsonString, RegisterEmployerDtoRequest.class);
            dtoRequest.validate();
            Employer employer = new Employer(
                    dtoRequest.getLogin(),
                    dtoRequest.getPassword(),
                    dtoRequest.getCompanyName(),
                    dtoRequest.getAddress(),
                    dtoRequest.geteMail(),
                    dtoRequest.getContactPersonSurname(),
                    dtoRequest.getContactPersonName(),
                    dtoRequest.getContactPersonPatronymic()
            );
            String dataBaseResponse = dao.insert(employer);
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
            ChangeEmployerDtoRequest dtoRequest = new Gson().fromJson(requestJsonString, ChangeEmployerDtoRequest.class);
            dtoRequest.validate();
            Employer employer = new Employer(
                    dtoRequest.getLogin(),
                    dtoRequest.getPassword(),
                    dtoRequest.getCompanyName(),
                    dtoRequest.getAddress(),
                    dtoRequest.geteMail(),
                    dtoRequest.getContactPersonSurname(),
                    dtoRequest.getContactPersonName(),
                    dtoRequest.getContactPersonPatronymic()
            );
            dao.change(dtoRequest.getToken(), employer);
            return new Gson().toJson(new EmptySuccessDtoResponse());
        } catch (DataTransferException e) {
            ErrorDtoResponse dtoResponse = new ErrorDtoResponse(e.getErrorCode().getErrorString());
            return new Gson().toJson(dtoResponse);
        } catch (DataBaseException e) {
            ErrorDtoResponse dtoResponse = new ErrorDtoResponse(e.getErrorCode().getErrorString());
            return new Gson().toJson(dtoResponse);
        }
    }

    public static String addVacancy(String requestJsonString) {
        try {
            AddVacancyDtoRequest dtoRequest = new Gson().fromJson(requestJsonString, AddVacancyDtoRequest.class);
            dtoRequest.validate();
            Vacancy vacancy = new Vacancy(
                    Visibility.PUBLIC,
                    dtoRequest.getVacancyName(),
                    dtoRequest.getSalary(),
                    dtoRequest.getRequiredSkills(),
                    dtoRequest.getExtraSkills()
            );
            String dataBaseResponse = dao.insert(dtoRequest.getToken(), vacancy);
            AddVacancyDtoResponse dtoResponse = new AddVacancyDtoResponse(dataBaseResponse);
            return new Gson().toJson(dtoResponse);
        } catch (DataTransferException e) {
            ErrorDtoResponse dtoResponse = new ErrorDtoResponse(e.getErrorCode().getErrorString());
            return new Gson().toJson(dtoResponse);
        } catch (DataBaseException e) {
            ErrorDtoResponse dtoResponse = new ErrorDtoResponse(e.getErrorCode().getErrorString());
            return new Gson().toJson(dtoResponse);
        }
    }

    public static String changeVacancy(String requestJsonString) {
        try {
            ChangeVacancyDtoRequest dtoRequest = new Gson().fromJson(requestJsonString, ChangeVacancyDtoRequest.class);
            dtoRequest.validate();
            Vacancy vacancy = new Vacancy(
                    dtoRequest.getVisibility(),
                    dtoRequest.getVacancyName(),
                    dtoRequest.getSalary(),
                    dtoRequest.getRequiredSkills(),
                    dtoRequest.getExtraSkills()
            );
            dao.change(dtoRequest.getToken(), dtoRequest.getVacancyID(), vacancy);
            return new Gson().toJson(new EmptySuccessDtoResponse());
        } catch (DataTransferException e) {
            ErrorDtoResponse dtoResponse = new ErrorDtoResponse(e.getErrorCode().getErrorString());
            return new Gson().toJson(dtoResponse);
        } catch (DataBaseException e) {
            ErrorDtoResponse dtoResponse = new ErrorDtoResponse(e.getErrorCode().getErrorString());
            return new Gson().toJson(dtoResponse);
        }
    }

    public static String deleteVacancy(String requestJsonString) {
        try {
            DeleteVacancyDtoRequest dtoRequest = new Gson().fromJson(requestJsonString, DeleteVacancyDtoRequest.class);
            dtoRequest.validate();
            dao.remove(dtoRequest.getToken(), dtoRequest.getVacancyID());
            return new Gson().toJson(new EmptySuccessDtoResponse());
        } catch (DataTransferException e) {
            ErrorDtoResponse dtoResponse = new ErrorDtoResponse(e.getErrorCode().getErrorString());
            return new Gson().toJson(dtoResponse);
        } catch (DataBaseException e) {
            ErrorDtoResponse dtoResponse = new ErrorDtoResponse(e.getErrorCode().getErrorString());
            return new Gson().toJson(dtoResponse);
        }
    }

    public static String getEmployees(String requestJsonString) {
        try {
            GetEmployeesDtoRequest dtoRequest = new Gson().fromJson(requestJsonString, GetEmployeesDtoRequest.class);
            dtoRequest.validate();
            ArrayList<String> dataBaseResponse = dao.getEmployees(dtoRequest.getToken(), dtoRequest.getVacancyID(), dtoRequest.getSearchMode());
            GetEmployeesDtoResponse dtoResponse = new GetEmployeesDtoResponse(dataBaseResponse);
            return new Gson().toJson(dtoResponse);
        } catch (DataTransferException e) {
            ErrorDtoResponse dtoResponse = new ErrorDtoResponse(e.getErrorCode().getErrorString());
            return new Gson().toJson(dtoResponse);
        } catch (DataBaseException e) {
            ErrorDtoResponse dtoResponse = new ErrorDtoResponse(e.getErrorCode().getErrorString());
            return new Gson().toJson(dtoResponse);
        }
    }

    public static String recruitEmployee(String requestJsonString) {
        try {
            RecruitEmployeeDtoRequest dtoRequest = new Gson().fromJson(requestJsonString, RecruitEmployeeDtoRequest.class);
            dtoRequest.validate();
            dao.recruit(dtoRequest.getToken(), dtoRequest.getVacancyID(), dtoRequest.getEmployeeId());
            return new Gson().toJson(new EmptySuccessDtoResponse());
        } catch (DataTransferException e) {
            ErrorDtoResponse dtoResponse = new ErrorDtoResponse(e.getErrorCode().getErrorString());
            return new Gson().toJson(dtoResponse);
        } catch (DataBaseException e) {
            ErrorDtoResponse dtoResponse = new ErrorDtoResponse(e.getErrorCode().getErrorString());
            return new Gson().toJson(dtoResponse);
        }
    }
}
