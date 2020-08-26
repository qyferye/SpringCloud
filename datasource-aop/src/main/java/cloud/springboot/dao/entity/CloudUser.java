package cloud.springboot.dao.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author cloud
 * @since 2019-11-04
 */
@Data
public class CloudUser {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */

    private Long id;

    /**
     * 用户名
     */

    private String name;


    /**
     * 创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date date;

    /**
     * 备注
     */

    private String address;

}
