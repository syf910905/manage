package com.demo.yunfei.common;

import lombok.Builder;
import lombok.Data;

/**
 * @Author Kylin
 * @Team Atplan
 * @Date 2018/4/20 11:25
 */

@Data
@Builder
public class HttpMessageResult<T> {


    public static final String DEFAULT_SUCCESS_MESSAGE = "success";

    public static final int DEFAULT_SUCCESS_CODE = 0;

    public static final String DEFAULT_ERROR_MESSAGE = "failure";

    public static final int DEFAULT_FAILURE_CODE = 500;

    private int code;

    private String message;

    private String englishMessage;

    private T data;

    /**
     * 默认成功的方法无返回数据
     *
     * @return
     */
    public static HttpMessageResult success() {
        return HttpMessageResult
                .builder()
                .code(DEFAULT_SUCCESS_CODE)
                .message(DEFAULT_SUCCESS_MESSAGE)
                .englishMessage(DEFAULT_SUCCESS_MESSAGE)
                .build();
    }

    /**
     * 快捷返回成功的方法
     *
     * @return
     */
    public static <T> HttpMessageResult success(T data) {
        return HttpMessageResult
                .builder()
                .code(DEFAULT_SUCCESS_CODE)
                .message(DEFAULT_SUCCESS_MESSAGE)
                .englishMessage(DEFAULT_SUCCESS_MESSAGE)
                .data(data)
                .build();
    }

    /**
     * 自定义返回结果方法
     *
     * @param code
     * @param message
     * @param data
     * @param <T>
     * @return
     */
    public static <T> HttpMessageResult success(int code, String message, T data) {
        return HttpMessageResult
                .builder()
                .code(code)
                .message(message)
                .data(data)
                .build();
    }

    /**
     * 自定义返回结果方法
     *
     * @param code
     * @param message
     * @param englishMessage
     * @param data
     * @param <T>
     * @return
     */
    public static <T> HttpMessageResult success(int code, String message, String englishMessage, T data) {
        return HttpMessageResult
                .builder()
                .code(code)
                .message(message)
                .englishMessage(englishMessage)
                .data(data)
                .build();
    }


    /**
     * 默认失败的方法
     *
     * @return
     */
    public static HttpMessageResult failure() {
        return HttpMessageResult
                .builder()
                .code(DEFAULT_FAILURE_CODE)
                .message(DEFAULT_ERROR_MESSAGE)
                .englishMessage(DEFAULT_ERROR_MESSAGE)
                .build();
    }

    /**
     * 自定义失败方法
     *
     * @param code
     * @param message
     * @return
     */
    public static HttpMessageResult failure(int code, String message) {
        return HttpMessageResult
                .builder()
                .code(code)
                .message(message)
                .build();
    }

    /**
     * 自定义失败方法
     *
     * @param code
     * @param message
     * @param englishMessage
     * @return
     */
    public static HttpMessageResult failure(int code, String message, String englishMessage) {
        return HttpMessageResult
                .builder()
                .code(code)
                .message(message)
                .englishMessage(englishMessage)
                .build();
    }

    /**
     * 自定义失败方法
     *
     * @param code
     * @param message
     * @return
     */
    public static <T> HttpMessageResult failure(int code, String message, T data) {
        return HttpMessageResult
                .builder()
                .code(code)
                .message(message)
                .data(data)
                .build();
    }

    /**
     * 自定义失败方法
     *
     * @param code
     * @param message
     * @param englishMessage
     * @param data
     * @param <T>
     * @return
     */
    public static <T> HttpMessageResult failure(int code, String message, String englishMessage, T data) {
        return HttpMessageResult
                .builder()
                .code(code)
                .message(message)
                .englishMessage(englishMessage)
                .data(data)
                .build();
    }


}
