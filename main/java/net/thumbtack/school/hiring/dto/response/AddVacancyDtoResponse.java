package net.thumbtack.school.hiring.dto.response;

public class AddVacancyDtoResponse {
    private String vacancyID;

    public AddVacancyDtoResponse(String vacancyID) {
        this.vacancyID = vacancyID;
    }

    public String getVacancyID() {
        return vacancyID;
    }

    public void setVacancyID(String vacancyID) {
        this.vacancyID = vacancyID;
    }
}
