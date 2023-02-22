package com.jimecc.diaryserver.controller;

import com.jimecc.diaryserver.entity.CommonResponse;
import com.jimecc.diaryserver.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Jim
 * @Description 文件操作
 * @createTime 2023年02月14日
 */
@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;

    // 上传文件
    @PostMapping("/upload")
    public CommonResponse upload(MultipartFile file, int note_id){
        return fileService.upload(file,note_id);
    }

    // 删除文件
    @DeleteMapping("/delete/{id}")
    public CommonResponse delete(@PathVariable int id){
        return fileService.deleteByid(id);
    }


}
