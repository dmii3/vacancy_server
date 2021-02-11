package net.thumbtack.school.hiring.dto.request;

import net.thumbtack.school.hiring.utils.DataTransferException;
import net.thumbtack.school.hiring.utils.ValidationUtils;

public class RegisterEmployeeDtoRequest {
    private String login;
    private String password;
    private String surname;
    private String name;
    private String patronymic;
    private String eMail;

    public RegisterEmployeeDtoRequest(String login,
                                      String password,
                                      String surname,
                                      String name,
                                      String patronymic,
                                      String eMail) {
        this.login = login;
        this.password = password;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.eMail = eMail;
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public void validate() throws DataTransferException {
        ValidationUtils.isNoneBlank(
                surname,
                name,
                eMail,
                login,
                password
        );
        ValidationUtils.validateLogin(login);
        ValidationUtils.validatePassword(password);
        ValidationUtils.validateEmail(eMail);
    }
}
