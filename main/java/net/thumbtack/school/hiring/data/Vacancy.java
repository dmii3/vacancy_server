package net.thumbtack.school.hiring.data;

import java.util.ArrayList;
import java.util.Objects;

public class Vacancy {
    private Visibility visibility;
    private String vacancyName;
    private int salary;
    private ArrayList<Skill> requiredSkills;
    private ArrayList<Skill> extraSkills;

    public Vacancy(Visibility visibility, String vacancyName, int salary, ArrayList<Skill> requiredSkills, ArrayList<Skill> extraSkills) {
        this.visibility = visibility;
        this.vacancyName = vacancyName;
        this.salary = salary;
        this.requiredSkills = requiredSkills;
        this.extraSkills = extraSkills;
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
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

    public void copy(Vacancy vacancy) {
        visibility = vacancy.visibility;
        vacancyName = vacancy.vacancyName;
        salary = vacancy.salary;
        requiredSkills = vacancy.requiredSkills;
        extraSkills = vacancy.extraSkills;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vacancy vacancy = (Vacancy) o;
        return salary == vacancy.salary &&
                visibility == vacancy.visibility &&
                Objects.equals(vacancyName, vacancy.vacancyName) &&
                Objects.equals(requiredSkills, vacancy.requiredSkills) &&
                Objects.equals(extraSkills, vacancy.extraSkills);
    }

    @Override
    public int hashCode() {

        return Objects.hash(visibility, vacancyName, salary, requiredSkills, extraSkills);
    }
}
