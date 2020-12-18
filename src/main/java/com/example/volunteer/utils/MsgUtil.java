package com.example.volunteer.utils;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @param: none
 * @description:
 * @author: KingJ
 **/
@Component
public class MsgUtil {
    private static final Logger logger = LoggerFactory.getLogger(MsgUtil.class);

    private static final String REGION_ID = "cn-hangzhou";
    private static final String SIGN_NAME = "";

    public String sendSignUpMsgCode(String tel) {
        return null;
    }

    private void sendMessgae(String tel, String templateCode, Map<String, String> paramMap) {
        DefaultProfile profile = DefaultProfile.getProfile(REGION_ID, "LTAIyhcFfw8FjAjq", "o7N7ff5ZdtDlrvpAisMtgCoKhgpv9i");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", REGION_ID);
        request.putQueryParameter("SignName", SIGN_NAME);
        request.putQueryParameter("PhoneNumbers", tel);
        request.putQueryParameter("TemplateCode", templateCode);
        if (MapUtils.isNotEmpty(paramMap)) {
            request.putQueryParameter("TemplateParam", com.example.volunteer.utils.SerialUtil.toJsonStr(paramMap));
        }

        try {
            CommonResponse response = client.getCommonResponse(request);
            logger.info("[SendMessage], request: {}, response: {}", com.example.volunteer.utils.SerialUtil.toJsonStr(request), com.example.volunteer.utils.SerialUtil.toJsonStr(response));
        } catch (Exception e) {
            logger.error("[SendMessage Ex], request: {}", com.example.volunteer.utils.SerialUtil.toJsonStr(request), e);

        }
    }
}
