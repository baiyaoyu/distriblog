package com.hiddencat.picsmgt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hiddencat.picsmgt.entity.PicInfoEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Author: baiyaoyu
 * @Date: 2022/5/10
 * @Description:
 */
@Mapper
@Repository
public interface IBizPicInfoMapper extends BaseMapper<PicInfoEntity> {
}
