package net.thumbtack.school.hiring.utils;

public class DataTransferException extends Exception {
    private DataTransferErrorCode errorCode;

    public DataTransferException(DataTransferErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public DataTransferErrorCode getErrorCode() {
        return errorCode;
    }
}
