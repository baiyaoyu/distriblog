package com.hiddencat.picsmgt;

import com.hiddencat.picsmgt.dto.BingPicDTO;
import com.hiddencat.picsmgt.service.IBingPicService;
import lombok.val;
import org.checkerframework.checker.units.qual.A;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @Author: baiyaoyu
 * @Date: 2022/5/10
 * @Description:
 */
@SpringBootTest
public class PicsmgtApplicationTest {
    @Autowired
    IBingPicService bingPicService;
    @Test
    public void testBingApi(){
        List<BingPicDTO> res1 = bingPicService.fetchAllPic(1, 6, "js");
        List<BingPicDTO> res2 = bingPicService.fetchAllPic(0,1,"js");
        Assert.assertTrue(res2.isEmpty());
    }
}
