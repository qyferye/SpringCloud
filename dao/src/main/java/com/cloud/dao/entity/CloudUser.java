package com.cloud.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
* <p>
    * 
    * </p>
*
* @author cloud
* @since 2019-11-04
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    public class CloudUser extends Model<CloudUser> {

    private static final long serialVersionUID = 1L;

            /**
            * ID
            */
            @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

            /**
            * 用户名
            */
        @TableField("USER_NAME")
    private String userName;

            /**
            * 删除
            */
        @TableField("IS_DELETED")
    private Integer isDeleted;

            /**
            * 创建人
            */
        @TableField("CREATED_BY")
    private String createdBy;

            /**
            * 修改人
            */
        @TableField("UPDATED_BY")
    private String updatedBy;

            /**
            * 创建时间
            */
        @TableField("DATE_CREATED")
    private LocalDateTime dateCreated;

            /**
            * 修改时间
            */
        @TableField("DATE_UPDATED")
    private LocalDateTime dateUpdated;

            /**
            * 备注
            */
        @TableField("REMARK")
    private String remark;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
