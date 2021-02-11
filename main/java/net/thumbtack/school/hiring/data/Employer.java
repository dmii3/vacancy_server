package net.thumbtack.school.hiring.data;

import java.util.Objects;

public class Employer extends User {
    private String companyName;
    private String address;
    private String eMail;
    private String contactPersonSurname;
    private String contactPersonName;
    private String contactPersonPatronymic;

    public Employer(String login,
                    String password,
                    String companyName,
                    String address,
                    String eMail,
                    String contactPersonSurname,
                    String contactPersonName,
                    String contactPersonPatronymic) {
        super(login, password);
        this.companyName = companyName;
        this.address = address;
        this.eMail = eMail;
        this.contactPersonSurname = contactPersonSurname;
        this.contactPersonName = contactPersonName;
        this.contactPersonPatronymic = contactPersonPatronymic;
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

    public void copy(Employer employer) {
        setPassword(employer.getPassword());
        this.companyName = employer.companyName;
        this.address = employer.address;
        this.eMail = employer.eMail;
        this.contactPersonSurname = employer.contactPersonSurname;
        this.contactPersonName = employer.contactPersonName;
        this.contactPersonPatronymic = employer.contactPersonPatronymic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Employer employer = (Employer) o;
        return Objects.equals(companyName, employer.companyName) &&
                Objects.equals(address, employer.address) &&
                Objects.equals(eMail, employer.eMail) &&
                Objects.equals(contactPersonSurname, employer.contactPersonSurname) &&
                Objects.equals(contactPersonName, employer.contactPersonName) &&
                Objects.equals(contactPersonPatronymic, employer.contactPersonPatronymic);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), companyName, address, eMail, contactPersonSurname, contactPersonName, contactPersonPatronymic);
    }
}
