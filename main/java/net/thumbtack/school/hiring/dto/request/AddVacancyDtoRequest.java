package net.thumbtack.school.hiring.dto.request;

import net.thumbtack.school.hiring.data.Skill;
import net.thumbtack.school.hiring.utils.DataTransferException;
import net.thumbtack.school.hiring.utils.ValidationUtils;

import java.util.ArrayList;

public class AddVacancyDtoRequest {
    private String token;
    private String vacancyName;
    private int salary;
    private ArrayList<Skill> requiredSkills;
    private ArrayList<Skill> extraSkills;

    public AddVacancyDtoRequest(String token, String vacancyName, int salary, ArrayList<Skill> requiredSkills, ArrayList<Skill> extraSkills) {
        this.token = token;
        this.vacancyName = vacancyName;
        this.salary = salary;
        this.requiredSkills = requiredSkills;
        this.extraSkills = extraSkills;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getVacancyName() {
        return vacancyName;
    }

    public void setVacancyName(String vacancyName) {
        this.vacancyName = vacancyName;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public ArrayList<Skill> getRequiredSkills() {
        return requiredSkills;
    }

    public void setRequiredSkills(ArrayList<Skill> requiredSkills) {
        this.requiredSkills = requiredSkills;
    }

    public ArrayList<Skill> getExtraSkills() {
        return extraSkills;
    }

    public void setExtraSkills(ArrayList<Skill> extraSkills) {
        this.extraSkills = extraSkills;
    }

    public void validate() throws DataTransferException {
        ValidationUtils.isNoneBlank(token, vacancyName);
        ValidationUtils.validateRequirements(requiredSkills, extraSkills);
    }
}
