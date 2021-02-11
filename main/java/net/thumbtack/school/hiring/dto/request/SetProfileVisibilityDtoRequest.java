package net.thumbtack.school.hiring.dto.request;

import net.thumbtack.school.hiring.data.Visibility;
import net.thumbtack.school.hiring.utils.DataTransferErrorCode;
import net.thumbtack.school.hiring.utils.DataTransferException;
import net.thumbtack.school.hiring.utils.ValidationUtils;

public class SetProfileVisibilityDtoRequest {
    private String token;
    private Visibility visibility;

    public SetProfileVisibilityDtoRequest(String token, Visibility visibility) {
        this.token = token;
        this.visibility = visibility;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
    }

    public void validate() throws DataTransferException {
        ValidationUtils.isNoneBlank(token);
        if (visibility == null) {
            throw new DataTransferException(DataTransferErrorCode.NOT_ENOUGH_DATA);
        }
    }
}
