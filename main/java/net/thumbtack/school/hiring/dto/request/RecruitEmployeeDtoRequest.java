package net.thumbtack.school.hiring.dto.request;

import net.thumbtack.school.hiring.data.Employee;
import net.thumbtack.school.hiring.utils.DataTransferErrorCode;
import net.thumbtack.school.hiring.utils.DataTransferException;
import net.thumbtack.school.hiring.utils.ValidationUtils;

import java.util.ArrayList;

public class RecruitEmployeeDtoRequest {
    private String token;
    private String vacancyID;
    private String employeeId;

    public RecruitEmployeeDtoRequest(String token, String vacancyID, String employeeId) {
        this.token = token;
        this.vacancyID = vacancyID;
        this.employeeId = employeeId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getVacancyID() {
        return vacancyID;
    }

    public void setVacancyID(String vacancyID) {
        this.vacancyID = vacancyID;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public void validate() throws DataTransferException {
        ValidationUtils.isNoneBlank(token, vacancyID, employeeId);
    }
}
