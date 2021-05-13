package com.example.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 
 * </p>
 *
 * @author wang
 * @since 2021-04-19
 */
@Data
@ApiModel
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class TRoleMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    //@TableId(value = "roleid", type = IdType.AUTO)
    private Integer roleid;

    private Integer menuid;


}
