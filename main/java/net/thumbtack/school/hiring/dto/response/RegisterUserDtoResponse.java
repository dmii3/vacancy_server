package net.thumbtack.school.hiring.dto.response;

public class RegisterUserDtoResponse {
    private final String token;

    public RegisterUserDtoResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
