package net.thumbtack.school.hiring.data;



import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;

public class Employee extends User {
    private String surname;
    private String name;
    private String patronymic;
    private String eMail;
    private ArrayList<Skill> skills;

    public Employee(String login,
                    String password,
                    String surname,
                    String name,
                    String patronymic,
                    String eMail) {
        super(login, password);
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.eMail = eMail;
        skills = new ArrayList<>();
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public ArrayList<Skill> getSkills() {
        return skills;
    }

    public boolean addSkill(Skill newSkill) {
        for (Skill skill : skills) {
            if (newSkill.getSkillName().equals(skill.getSkillName())) {
                return false;
            }
        }
        skills.add(newSkill);
        skills.sort(Comparator.comparing(Skill::getSkillName));
        return true;
    }

    public boolean changeSkill(Skill skillUpd) {
        for (Skill skill : skills) {
            if (skillUpd.getSkillName().equals(skill.getSkillName())) {
                skill.setSkillLevel(skillUpd.getSkillLevel());
                return true;
            }
        }
        return false;
    }

    public boolean removeSkill(Skill skill) {
        return skills.remove(skill);
    }

    public void copy(Employee employee) {
        setPassword(employee.getPassword());
        this.surname = employee.surname;
        this.name = employee.name;
        this.patronymic = employee.patronymic;
        this.eMail = employee.eMail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Employee employee = (Employee) o;
        return Objects.equals(surname, employee.surname) &&
                Objects.equals(name, employee.name) &&
                Objects.equals(patronymic, employee.patronymic) &&
                Objects.equals(eMail, employee.eMail) &&
                Objects.equals(skills, employee.skills);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), surname, name, patronymic, eMail, skills);
    }
}
