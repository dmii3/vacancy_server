package net.thumbtack.school.hiring.dto.response;

public class LogInDtoResponse {
    private final String token;

    public LogInDtoResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}