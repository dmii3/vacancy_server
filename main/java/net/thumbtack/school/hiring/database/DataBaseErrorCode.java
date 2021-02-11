package net.thumbtack.school.hiring.database;

public enum DataBaseErrorCode {
    LOGIN_ALREADY_EXISTS("Пользователь с таким логином уже зарегистрирован"),
    LOGIN_DOES_NOT_MATCH("Логины не совпадают"),
    USER_NOT_FOUND("Пользователь не найден"),
    USER_ALREADY_LOGGED_IN("Этот пользователь уже авторизован на сервере"),
    VACANCY_NOT_FOUND("Вакансия не найдена"),
    VACANCY_ACCESS_DENIED("У Вас нет доступа к этой вакансии"),
    SKILL_ALREADY_EXISTS("Такой навык уже есть"),
    SKILL_NOT_FOUND("Навык не найден");

    private String errorString;

    DataBaseErrorCode(String errorString) {
        this.errorString = errorString;
    }

    public String getErrorString() {
        return errorString;
    }
}
