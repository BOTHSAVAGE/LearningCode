package com.example.sql_demo.utils;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import org.springframework.beans.factory.annotation.Value;

public class SendSmsUtils {

    @Value(value = "${aliyun.sms.regionId}")
    private String regionId;
    @Value(value = "${aliyun.sms.accessKey}")
    private String accessKey;
    @Value(value = "${aliyun.sms.accessSecret}")
    private String accessSecret;
    @Value(value = "${aliyun.sms.signName}")
    private String signName;

    public   void  SendSms(String phoneNumber, String jsonStr, String TemlateCode) {
        try {
            DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKey, accessSecret);
            IAcsClient client = new DefaultAcsClient(profile);
            CommonRequest request = new CommonRequest();
            request.setSysMethod(MethodType.POST);
            request.setSysDomain("dysmsapi.aliyuncs.com");
            request.setSysVersion("2017-05-25");
            request.setSysAction("SendSms");
            request.putQueryParameter("RegionId", "cn-hangzhou");
            request.putQueryParameter("PhoneNumbers", phoneNumber);
            request.putQueryParameter("SignName", signName);
            request.putQueryParameter("TemplateCode", TemlateCode);
            request.putQueryParameter("TemplateParam", jsonStr);
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        }
        catch (ClientException e) {
            e.printStackTrace();
        }
    }

}
