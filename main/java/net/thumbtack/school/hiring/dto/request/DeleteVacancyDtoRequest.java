package net.thumbtack.school.hiring.dto.request;


import net.thumbtack.school.hiring.utils.DataTransferException;
import net.thumbtack.school.hiring.utils.ValidationUtils;

public class DeleteVacancyDtoRequest {
    private String token;
    private String vacancyID;

    public DeleteVacancyDtoRequest(String token, String vacancyID) {
        this.token = token;
        this.vacancyID = vacancyID;
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

    public void validate() throws DataTransferException {
        ValidationUtils.isNoneBlank(token, vacancyID);
    }
}
