package net.thumbtack.school.hiring.dto.request;

import net.thumbtack.school.hiring.data.SearchMode;
import net.thumbtack.school.hiring.utils.DataTransferErrorCode;
import net.thumbtack.school.hiring.utils.DataTransferException;
import net.thumbtack.school.hiring.utils.ValidationUtils;

public class GetEmployeesDtoRequest {
    private String token;
    private String vacancyID;
    private SearchMode searchMode;

    public GetEmployeesDtoRequest(String token, String vacancyID, SearchMode searchMode) {
        this.token = token;
        this.vacancyID = vacancyID;
        this.searchMode = searchMode;
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

    public SearchMode getSearchMode() {
        return searchMode;
    }

    public void setSearchMode(SearchMode searchMode) {
        this.searchMode = searchMode;
    }

    public void validate() throws DataTransferException {
        ValidationUtils.isNoneBlank(token, vacancyID);
        if (searchMode == null) {
            throw new DataTransferException(DataTransferErrorCode.NOT_ENOUGH_DATA);
        }
    }
}
