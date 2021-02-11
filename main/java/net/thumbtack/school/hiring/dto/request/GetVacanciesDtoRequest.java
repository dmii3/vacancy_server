package net.thumbtack.school.hiring.dto.request;

import net.thumbtack.school.hiring.data.SearchMode;
import net.thumbtack.school.hiring.utils.DataTransferErrorCode;
import net.thumbtack.school.hiring.utils.DataTransferException;
import net.thumbtack.school.hiring.utils.ValidationUtils;

public class GetVacanciesDtoRequest {
    private String token;
    private SearchMode searchMode;

    public GetVacanciesDtoRequest(String token, SearchMode searchMode) {
        this.token = token;
        this.searchMode = searchMode;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public SearchMode getSearchMode() {
        return searchMode;
    }

    public void setSearchMode(SearchMode searchMode) {
        this.searchMode = searchMode;
    }

    public void validate() throws DataTransferException {
        ValidationUtils.isNoneBlank(token);
        if (searchMode == null) {
            throw new DataTransferException(DataTransferErrorCode.NOT_ENOUGH_DATA);
        }
    }
}
