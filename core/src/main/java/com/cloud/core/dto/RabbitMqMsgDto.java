package com.cloud.core.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ Description   :  RabbitMaMsgDto
 * @ Author        :  秦云峰（cloud）
 * @ CreateDate    :  2019-11-09 9:50
 */
@Data
public class RabbitMqMsgDto implements Serializable {
   private String msgBody;
   @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
   private Date   receiveDate = new Date();

}
