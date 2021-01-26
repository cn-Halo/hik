package com.hik.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class ListParam {


//    @ApiModelProperty(value = "起始时间")
//    @NotBlank(message = "起始时间不能为空")
//    @Pattern(message = "格式如：2016-01-01 00:00:00", regexp = "(http|https):\\/\\/\\S*")
//    private String originDate;

}
