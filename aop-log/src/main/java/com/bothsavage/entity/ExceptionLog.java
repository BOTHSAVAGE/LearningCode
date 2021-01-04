package com.bothsavage.entity;

import lombok.Data;

import java.util.Date;

@Data
public class ExceptionLog {
    private String excId;
    private String operModul;
    private String operType;
    private String operDesc;
    private String OperMethod;
    private String excRequParam;
    private String OperRespParam;
    private String OperUri;
    private Date OperCreateTime;
    private String excName;
    private String excMessage;
}
