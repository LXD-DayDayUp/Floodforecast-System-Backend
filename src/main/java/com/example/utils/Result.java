package com.example.utils;

import lombok.Data;

@Data
public class Result<T> {
    private Boolean success;
    private Integer code;
    private String message;
    private T data;

    //    私有化构造方法
    private Result() {
    }

    //    成功执行不返回数据
    public static<T> Result<T> ok() {
        Result<T> result = new Result<T>();
        result.setSuccess(true);
        result.setCode(ResultCode.SUCCESS);
        result.setMessage("执行成功");
        return result;
    }

    //    成功执行返回数据
    public static<T> Result<T> ok(T data) {
        Result<T> result = new Result<T>();
        result.setSuccess(true);
        result.setCode(ResultCode.SUCCESS);
        result.setMessage("执行成功");
        result.setData(data);
        return result;
    }

    //    执行失败
    public static<T> Result<T> error() {
        Result<T> result = new Result<T>();
        result.setSuccess(false);
        result.setCode(ResultCode.ERROR);
        result.setMessage("执行失败");
        return result;
    }

    //    设置是否成功
    public Result<T> success(Boolean success) {
        this.setSuccess(success);
        return this;
    }

    //    设置状态码
    public Result<T> code(Integer code) {
        this.setCode(code);
        return this;
    }

    //    设置返回消息
    public Result<T> message(String message) {
        this.setMessage(message);
        return this;
    }

    //    是否存在
    public static<T> Result<T> exist() {
        Result<T> result = new Result<T>();
        result.setSuccess(false);
        result.setCode(ResultCode.SUCCESS);
        result.setMessage("数据存在");
        return result;
    }
}
