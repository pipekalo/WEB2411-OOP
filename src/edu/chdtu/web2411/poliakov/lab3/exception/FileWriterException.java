package edu.chdtu.web2411.poliakov.lab3.exception;

public class FileWriterException extends RuntimeException {

    public FileWriterException(String file, Throwable cause) {
        super("Ошибка при записи в файл " + file, cause);
    }

    public FileWriterException(String message) {
        super(message);
    }
}
