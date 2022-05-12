package fileservice.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @Author: baiyaoyu
 * @Date: 2022/5/11
 * @Description:
 */
@Data
@TableName("file_record_entity")
public class FileRecordEntity {
    @TableId
    private String id;
    private int size;
    private String path;
    @TableField("update_time")
    private String updateTime;
    @TableField("create_time")
    private String createTime;
    @TableField("is_deleted")
    private String idDeleted;
}
