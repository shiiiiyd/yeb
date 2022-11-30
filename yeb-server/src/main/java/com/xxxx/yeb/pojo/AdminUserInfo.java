package com.xxxx.yeb.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "用户信息实体类", description = "")
public class AdminUserInfo implements Serializable {
    private static final long serialVersionUID = 3432209210090010579L;
    @ApiModelProperty(value = "用户名")
    @Getter@Setter
    private  String name;

    @ApiModelProperty(value = "手机号码")
    @Getter@Setter
    private String phone;

    @ApiModelProperty(value = "住宅电话")
    @Getter@Setter
    private String telephone;

    @ApiModelProperty(value = "联系地址")
    @Getter@Setter
    private String address;

    @ApiModelProperty(value = "id")
    @Getter@Setter
    private Integer id;
}
