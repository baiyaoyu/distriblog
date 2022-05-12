package com.hiddencat.picsmgt.service;

import com.hiddencat.picsmgt.dto.BingPicDTO;

import java.util.List;

/**
 * @Author: baiyaoyu
 * @Date: 2022/5/10
 * @Description:
 */
public interface IBingPicService {
    List<BingPicDTO> fetchAllPic(int n, int idx, String format);
}
