package com.jimecc.diaryserver.controller;

import com.jimecc.diaryserver.entity.CommonResponse;
import com.jimecc.diaryserver.entity.Note;
import com.jimecc.diaryserver.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Jim
 * @Description
 * @createTime 2023年02月09日
 */
@RestController
@RequestMapping("/note")
public class NoteController {

    @Autowired
    private NoteService noteService;

    // 查找最新的一篇文章
    @GetMapping("/latest")
    public CommonResponse getLatestNote(){
        return noteService.getLatestNote();
    }

    // 有 manager-id 判断
    @GetMapping("/article/{id}")
    public CommonResponse getArticle(@PathVariable int id){
        return noteService.getNoteById(id);
    }

    // 有 manager-id 判断
    @PostMapping("/insert")
    public CommonResponse insert(@RequestBody Note note){
        return noteService.createNote(note);
    }

    @DeleteMapping("/delete/{id}")
    public CommonResponse delete(@PathVariable int id){
        return noteService.deleteById(id);
    }

}
