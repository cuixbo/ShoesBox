package com.xbc.douban.api;

import android.net.ParseException;

import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.net.ConnectException;

import retrofit2.HttpException;

public class ExceptionEngine {
    //对应HTTP的状态码
    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int BAD_GATEWAY = 502;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int GATEWAY_TIMEOUT = 504;

    public static ApiException handleException(Throwable e) {
        ApiException ex;
        if (e instanceof HttpException) {//HTTP错误
            HttpException httpException = (HttpException) e;
            ex = new ApiException(e, ERROR.HTTP_ERROR);
            switch (httpException.code()) {
                case UNAUTHORIZED:
                case FORBIDDEN:
                case NOT_FOUND:
                case REQUEST_TIMEOUT:
                case GATEWAY_TIMEOUT:
                case INTERNAL_SERVER_ERROR:
                case BAD_GATEWAY:
                case SERVICE_UNAVAILABLE:
                default:
                    ex.message = "网络错误";  //均视为网络错误
                    break;
            }
            return ex;
        } else if (e instanceof ServerException) {//服务器返回的错误 code!=0
            ServerException resultException = (ServerException) e;
            ex = new ApiException(resultException, resultException.code);
            ex.message = resultException.message;
            return ex;
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {//均视为解析错误
            ex = new ApiException(e, ERROR.PARSE_ERROR);
            ex.message = "解析错误";
            return ex;
        } else if (e instanceof ConnectException) {//均视为网络错误
            ex = new ApiException(e, ERROR.NETWORK_ERROR);
            ex.message = "连接失败";
            return ex;
        } else {                                    //未知错误
            ex = new ApiException(e, ERROR.UNKNOWN);
            ex.message = "未知错误";
            return ex;
        }
    }

    /**
     * 约定异常
     */
    public static class ERROR {
        public static final int UNKNOWN = 1000;//未知错误
        public static final int PARSE_ERROR = 1001;//解析错误
        public static final int NETWORK_ERROR = 1002;//网络错误
        public static final int HTTP_ERROR = 1003;//协议出错
    }
}
