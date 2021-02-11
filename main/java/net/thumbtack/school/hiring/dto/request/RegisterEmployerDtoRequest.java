package net.thumbtack.school.hiring.dto.request;

import net.thumbtack.school.hiring.utils.DataTransferException;
import net.thumbtack.school.hiring.utils.ValidationUtils;


public class RegisterEmployerDtoRequest {
    private String login;
    private String password;
    private String companyName;
    private String address;
    private String eMail;
    private String contactPersonSurname;
    private String contactPersonName;
    private String contactPersonPatronymic;

    public RegisterEmployerDtoRequest(String login,
                                      String password,
                                      String companyName,
                                      String address,
                                      String eMail,
                                      String contactPersonSurname,
                                      String contactPersonName,
                                      String contactPersonPatronymic) {
        this.login = login;
        this.password = password;
        this.companyName = companyName;
        this.address = address;
        this.eMail = eMail;
        this.contactPersonSurname = contactPersonSurname;
        this.contactPersonName = contactPersonName;
        this.contactPersonPatronymic = contactPersonPatronymic;
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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getContactPersonSurname() {
        return contactPersonSurname;
    }

    public void setContactPersonSurname(String contactPersonSurname) {
        this.contactPersonSurname = contactPersonSurname;
    }

    public String getContactPersonName() {
        return contactPersonName;
    }

    public void setContactPersonName(String contactPersonName) {
        this.contactPersonName = contactPersonName;
    }

    public String getContactPersonPatronymic() {
        return contactPersonPatronymic;
    }

    public void setContactPersonPatronymic(String contactPersonPatronymic) {
        this.contactPersonPatronymic = contactPersonPatronymic;
    }

    public void validate() throws DataTransferException {
        ValidationUtils.isNoneBlank(
                companyName,
                address,
                eMail,
                contactPersonSurname,
                contactPersonName,
                login,
                password
        );
        ValidationUtils.validateLogin(login);
        ValidationUtils.validatePassword(password);
        ValidationUtils.validateEmail(eMail);
    }
}
