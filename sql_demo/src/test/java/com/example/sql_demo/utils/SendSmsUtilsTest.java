package com.example.sql_demo.utils;


import ch.qos.logback.core.util.FileUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SendSmsUtilsTest {

    @Test
    public void sendSms() {


        ExcelReader reader;

        reader = ExcelUtil.getReader(FileUtil.file("test.xlsx"), 0);
    }
}