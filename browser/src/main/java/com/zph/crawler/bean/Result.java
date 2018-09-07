package com.zph.crawler.bean;

import java.io.Serializable;
import java.util.HashMap;

/**
 * 接口返回数据封装类
 *
 * @author zrk
 * @date 2017年5月4日 16:40:56
 */
public class Result implements Serializable {
    private static final long serialVersionUID = 1L;

    private int code;
    private String desc;
    private Object results;

    private Result() {
        super();
    }

    private Result(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private Result(int code, String desc, Object results) {
        super();
        this.code = code;
        this.desc = desc;
        this.results = results;
    }

    private Result(IResultEnum resultEnum) {
        this.code = resultEnum.getCode();
        this.desc = resultEnum.getDesc();
    }

    private Result(IResultEnum resultEnum, Object results) {
        this.code = resultEnum.getCode();
        this.desc = resultEnum.getDesc();
        this.results = results;
    }

    private Result(Builder builder) {
        setCode(builder.code);
        setDesc(builder.desc);
        setResults(builder.results);
    }


    //    @Deprecated
    public static Result okMsg(String desc) {
        return new Result(Enum.SUCESS.getCode(), desc);
    }

    public static Result ok() {
        return new Result(Enum.SUCESS);
    }

    public static Result ok(Object results) {
        return new Result(Enum.SUCESS, results);
    }

    public static Result ok(String desc, Object results) {
        return new Result(Enum.SUCESS.getCode(), desc, results);
    }

    public static Result okSingle(String desc, final String key, final Object results) {
        return new Result(Enum.SUCESS.getCode(), desc, new HashMap<String, Object>() {
            {
                put(key, results);
            }
        });
    }

    public static Result okSingle(final String key, final Object results) {
        return okSingle(Enum.SUCESS.getDesc(), key, results);
    }

    public static Result fail(int code) {
        return new Result(code, null, null);
    }

    public static Result fail(int code, String desc) {
        return new Result(code, desc);
    }

    public static Result fail(int code, String desc, Object results) {
        return new Result(code, desc, results);
    }

    public static Result result(IResultEnum resultEnum) {
        return new Result(resultEnum);

    }

    public static Result result(IResultEnum resultEnum, Object object) {
        return new Result(resultEnum, object);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setResults(Object results) {
        this.results = results;
    }

    public Object getResults() {
        return results;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", desc='" + desc + '\'' +
                ", results=" + results +
                '}';
    }

    public enum Enum implements IResultEnum {
        SUCESS(1, "请求成功");

        Enum(Integer code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        private Integer code;
        private String desc;

        @Override
        public Integer getCode() {
            return code;
        }

        @Override
        public String getDesc() {
            return desc;
        }
    }


    public static final class Builder {
        private int code;
        private String desc;
        private Object results;

        public Builder() {
        }

        public Builder code(int val) {
            code = val;
            return this;
        }

        public Builder desc(String val) {
            desc = val;
            return this;
        }

        public Builder results(Object val) {
            results = val;
            return this;
        }

        public Result build() {
            return new Result(this);
        }
    }
}
