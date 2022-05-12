package com.hiddencat.common;

import lombok.Data;

/**
 * @Author: baiyaoyu
 * @Date: 2022/5/11
 * @Description:
 */
@Data
public class ResponseResult<T> {
    private String success;
    private String msg;
    private Integer code;
    private T data;
}
