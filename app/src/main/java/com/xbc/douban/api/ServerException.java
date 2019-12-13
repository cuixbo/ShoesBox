package com.xbc.douban.api;

/**
 * @author xiaobocui
 * @date 2019-12-09
 */
public class ServerException extends RuntimeException {

    public int code;
    public String message;

    public ServerException(int code) {
        this.code = code;
    }
}
