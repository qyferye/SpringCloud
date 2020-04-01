package cloud.springboot.dao.entity;

import lombok.Data;


@Data
public class PayLog {
    private Long id;

    private String orderNo;

    private String merchants;

    private String transactionNo;

    private String payChannel;

    private String orderType;

    private String payPlatformName;

    private String source;

    private String payStatus;


    private String remark;

    private String version;

    private String isSynch;

    private String intputResult;

    private String outputResult;

    private String callbackResult;

    private static final long serialVersionUID = 1L;




}