package com.jimecc.diaryserver.controller;

import com.jimecc.diaryserver.entity.CommonResponse;
import com.jimecc.diaryserver.entity.File;
import com.jimecc.diaryserver.entity.Note;
import com.jimecc.diaryserver.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Jim
 * @Description 文件列表
 * @createTime 2023年02月23日
 */
@RestController
@RequestMapping("/index")
public class IndexController {

    @Autowired
    private IndexService indexService;

    // 查找文章的图片
    @GetMapping("/file/{note_id}")
    public CommonResponse getFiles4article(@PathVariable int note_id){
        return indexService.getFilesList4Article(note_id);
    }
    
    // 搜索
    @GetMapping("/search/{str}")
    public List<Note> search(@PathVariable String str){
        return indexService.searchDirectory(str);
    }

    // 查找该用户的所有文章
    @GetMapping("/note/all")
    public List<Note> getIndexAll(){
        return indexService.getDirectories();
    }

    // 按照颜色查找目录
    @GetMapping("/note/color/{color}")
    public List<Note> getIndexByColor(@PathVariable String color){
        return indexService.getDirectoriesByColor(color);
    }

    @GetMapping("/note/collection")
    public List<Note> getIndexByColletc(){
        return indexService.getCollectionDirectories();
    }

    @GetMapping("/note/score/{score}")
    public List<Note> getIndexByScore(@PathVariable int score){
        return indexService.getDirectoriesByScore(score);
    }

}
