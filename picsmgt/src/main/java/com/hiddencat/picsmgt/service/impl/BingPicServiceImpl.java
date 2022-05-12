package com.hiddencat.picsmgt.service.impl;

import com.alibaba.fastjson.JSON;
import com.hiddencat.picsmgt.dto.BingPicDTO;
import com.hiddencat.picsmgt.service.IBingPicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: baiyaoyu
 * @Date: 2022/5/10
 * @Description:
 */
@Service
public class BingPicServiceImpl implements IBingPicService {
    @Autowired
    RestTemplate restTemplate;
    private final static String BING_PIC_API = "https://www.bing.com/HPImageArchive.aspx?format={format}&n={n}&idx={idx}";
    @Override
    public List<BingPicDTO> fetchAllPic(int n, int idx, String format) {
        List<BingPicDTO> res = new ArrayList<>();
        if(n < 1){
           return res;
        }
        Map<String, Object> params = new LinkedHashMap<>();
        params.put("idx",idx);
        params.put("format",format);
        params.put("n",n);
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(BING_PIC_API,String.class,params);
        if(responseEntity.getStatusCode().is2xxSuccessful()){
            String imagesJson = JSON.parseObject(responseEntity.getBody()).getString("images");
            res.addAll(JSON.parseArray(imagesJson,BingPicDTO.class));
        }
        return res;
    }
}
