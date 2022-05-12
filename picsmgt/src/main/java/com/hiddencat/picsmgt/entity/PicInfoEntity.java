package com.hiddencat.picsmgt.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author: baiyaoyu
 * @Date: 2022/5/10
 * @Description:
 */
@Data
@TableName(value = "biz_pic_info")
public class PicInfoEntity {

    private String id;

    private int length;

    private int width;

    private int picSize;

    private String description;

    private String origion;

    private String createTime;

    private String modifyTime;

    private boolean isDelete;

}
