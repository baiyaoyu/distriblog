package fileservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: baiyaoyu
 * @Date: 2022/5/11
 * @Description:
 */
@Controller
@RequestMapping("/file")
public class FileController {
    @PostMapping("/upload")
    public void uploadFile(HttpServletRequest request){
//        request.get
    }

}
