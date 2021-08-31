package com.project.swordsmanhouse.utils;

/**
 * 自定义异常类
 *
 * @author wy
 * @version 1.0
 */
public class GenericException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public GenericException(String message) {
        super(message);
    }
}
