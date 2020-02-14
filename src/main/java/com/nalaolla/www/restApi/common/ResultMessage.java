package com.nalaolla.www.restApi.common;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResultMessage<T> {

    /** 성공 여부 */
    private String successYn;

    /** HttpStatus code */
    private String statusCode;

    /** 업무성 코드 */
    private String code;

    /** 데이터 객체 */
    private T data;

    /** 메세지 */
    private String message;

    /** 개발자용 메세지 */
    private String devMessage;

    /** 실패 메시지 */
    private String failMessage;

    /** 목록 총 갯수 */
    private int totalCount;

    public ResultMessage() {
    }

    /**
     * ResultMessage
     *
     * @param successYn successYn
     */
    public ResultMessage(String successYn) {
        this.successYn = successYn;
    }

    /**
     * ResultMessage
     *
     * @param successYn successYn
     * @param message message
     */
    public ResultMessage(String successYn, String message) {
        this.successYn = successYn;
        this.message = message;
    }

    /**
     * ResultMessage
     *
     * @param data data
     * @param successYn successYn
     */
    public ResultMessage(T data, String successYn) {
        this.successYn = successYn;
        this.data = data;
        this.totalCount = 1;
    }

    /**
     * ResultMessage
     *
     * @param data data
     * @param totalCount totalCount
     * @param message message
     */
    public ResultMessage(T data, int totalCount, String message) {
        this.data = data;
        this.message = message;
        this.totalCount = totalCount;
    }

    /**
     * ResultMessage
     *
     * @param successYn successYn
     * @param code code
     * @param data data
     * @param message message
     */
    public ResultMessage(String successYn, String code, T data, String message) {
        this.successYn = successYn;
        this.code = code;
        this.data = data;
        this.message = message;
    }

    /**
     * ResultMessage
     *
     * @param successYn successYn
     * @param statusCode statusCode
     * @param data data
     * @param message message
     */
    public ResultMessage(String successYn, String statusCode, String code, T data, String message) {
        this.successYn = successYn;
        this.statusCode = statusCode;
        this.code = code;
        this.data = data;
        this.message = message;
    }

}
