package net.thumbtack.school.hiring.dto.request;

public class LeaveServerDtoRequest {
    private String token;

    public LeaveServerDtoRequest(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
