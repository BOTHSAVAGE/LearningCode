package com.bothsavage.entity;

import lombok.Data;

import java.util.Date;

@Data
public class OperationLog {
    private String operId;
    private String operModul;
    private String operType;
    private String operDesc;
    private String OperMethod;
    private String OperRequParam;
    private String OperRespParam;
    private String OperUserId;
    private String OperUserName;
    private String OperIp;
    private String OperUri;
    private Date OperCreateTime;
    private String OperVer;
}
