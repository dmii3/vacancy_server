package net.thumbtack.school.hiring.database;

public class DataBaseException extends Exception {
    private DataBaseErrorCode errorCode;

    public DataBaseException(DataBaseErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public DataBaseErrorCode getErrorCode() {
        return errorCode;
    }
}
