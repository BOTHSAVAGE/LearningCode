package com.example.sql_demo.pojo;

import com.example.sql_demo.validator.annotion.checkDate;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.Email;
import java.util.Date;

@Data
@NoArgsConstructor
public class Facility {

    private Long id;

    @Email(message = "请输入正确的邮箱")
    private String email;

    @AssertFalse
    private boolean flag;

    @checkDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date date;
}