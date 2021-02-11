package net.thumbtack.school.hiring.utils;

public enum DataTransferErrorCode {
    NOT_ENOUGH_DATA("Заполните все необходимые поля"),
    INVALID_EMAIL("Неверно указан eMail"),
    LOGIN_TOO_SHORT("Логин слишком короткий"),
    INVALID_LOGIN("Недопустимый логин"),
    PASSWORD_TOO_SHORT("Пароль слишком короткий"),
    INVALID_REQUIREMENT("Проверьте правильность требований"),
    INVALID_REQUIREMENTS("Уровень необязательного навыка должен быть больше уровня обязательного"),
    DUPLICATE_SKILL("Навык указан несколько раз"),
    INVALID_SKILL_LEVEL("Неверно указан уровень навыка"),
    INVALID_EMPLOYEE_INDEX("Неверно указан работник");


    private String errorString;

    DataTransferErrorCode(String errorString) {
        this.errorString = errorString;
    }

    public String getErrorString() {
        return errorString;
    }
}
