package com.au.example.fileupload.exception;

/**
 * Created by Ayhan.Ugurlu on 18/10/2018
 */

public class FileStorageException extends RuntimeException {
    public FileStorageException(String message) {
        super(message);
    }

    public FileStorageException(String message, Throwable cause) {
        super(message, cause);
    }
}