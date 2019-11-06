package com.cloud.core.common.constant;

import com.cloud.core.common.baseInterface.ResultEnumService;
import org.apache.commons.lang3.StringUtils;

public enum DefaultResultEnum implements ResultEnumService {

    SUCCESS("100000", "SUCCESS"),
    FAIL("100001", "FAIL"),
    TEST("000001","异常测试")




;










    private String code;
    private String message;

    private DefaultResultEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }


    public static String getMsgByCode(String code) {
        if (StringUtils.isNotBlank(code)) {
            for (DefaultResultEnum defaultResultEnum : DefaultResultEnum.values()) {
                if (defaultResultEnum.getCode().equals(code)) {
                    return defaultResultEnum.getMessage();
                }
            }
        }
        return " ";
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }

}
