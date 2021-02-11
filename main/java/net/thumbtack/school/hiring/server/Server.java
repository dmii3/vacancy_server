package net.thumbtack.school.hiring.server;

import net.thumbtack.school.hiring.services.DataBaseService;
import net.thumbtack.school.hiring.services.EmployeeService;
import net.thumbtack.school.hiring.services.EmployerService;
import net.thumbtack.school.hiring.services.UserService;

import java.io.IOException;

public class Server {

    public void startServer(String savedDataFileName) throws IOException {
        DataBaseService.initDataBase(savedDataFileName);
    }

    public void stopServer(String saveDataFileName) throws IOException {
        DataBaseService.saveDataBase(saveDataFileName);
    }

    public String registerEmployer(String requestJsonString) {
        return EmployerService.register(requestJsonString);
    }

    public String registerEmployee(String requestJsonString) {
        return EmployeeService.register(requestJsonString);
    }

    public String changeEmployerData(String requestJsonString) {
        return EmployerService.changeData(requestJsonString);
    }

    public String changeEmployeeData(String requestJsonString) {
        return EmployeeService.changeData(requestJsonString);
    }

    public String logIn(String requestJsonString) {
        return UserService.logIn(requestJsonString);
    }

    public String logOut(String requestJsonString) {
        return UserService.logOut(requestJsonString);
    }

    public String leaveServer(String requestJsonString) {
        return UserService.leaveServer(requestJsonString);
    }

    public String setProfileVisibility(String requestJsonString) { return EmployeeService.SetProfileVisibility(requestJsonString); }

    public String addVacancy(String requestJsonString) {
        return EmployerService.addVacancy(requestJsonString);
    }

    public String changeVacancy(String requestJsonString) {
        return EmployerService.changeVacancy(requestJsonString);
    }

    public String deleteVacancy(String requestJsonString) {
        return EmployerService.deleteVacancy(requestJsonString);
    }

    public String addSkill(String requestJsonString) {
        return EmployeeService.addSkill(requestJsonString);
    }

    public String changeSkill(String requestJsonString) {
        return EmployeeService.changeSkill(requestJsonString);
    }

    public String deleteSkill(String requestJsonString) {
        return EmployeeService.deleteSkill(requestJsonString);
    }

    public String getEmployees(String requestJsonString) {
        return EmployerService.getEmployees(requestJsonString);
    }

    public String getVacancies(String requestJsonString) {
        return EmployeeService.getVacancies(requestJsonString);
    }

    public String recruitEmployee(String requestJsonString) {
        return EmployerService.recruitEmployee(requestJsonString);
    }
}
