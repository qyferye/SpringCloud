package com.cloud.core.exception;

import com.cloud.core.common.baseInterface.ResultEnumService;
import com.cloud.core.common.util.EnumUtil;
import lombok.Data;

@Data
public class BusinessException extends RuntimeException {
    private String code;
    private String msg;


    public  <R extends ResultEnumService> BusinessException(R resultEnum) {
        this.code = EnumUtil.getCode(resultEnum);
        this.msg = EnumUtil.getMessage(resultEnum);
    }


}
