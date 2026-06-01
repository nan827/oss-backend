package com.oss.controller;

import com.oss.utils.OssUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UploadController {

    @Autowired
    private OssUtil ossUtil;

    /**
     * 单文件上传接口
     */
    @PostMapping("/upload")
    public Map<String, Object> upload(@RequestParam("file") MultipartFile file) {
        Map<String, Object> result = new HashMap<>();
        try {
            if (file.isEmpty()) {
                result.put("success", false);
                result.put("msg", "文件不能为空");
                return result;
            }
            String url = ossUtil.upload(file);
            result.put("success", true);
            result.put("msg", "上传成功");
            result.put("url", url);
        } catch (IOException e) {
            result.put("success", false);
            result.put("msg", "上传失败：" + e.getMessage());
        }
        return result;
    }
}
