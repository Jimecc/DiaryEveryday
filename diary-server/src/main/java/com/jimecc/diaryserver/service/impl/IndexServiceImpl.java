package com.jimecc.diaryserver.service.impl;

import com.jimecc.diaryserver.entity.CommonResponse;
import com.jimecc.diaryserver.entity.File;
import com.jimecc.diaryserver.entity.Note;
import com.jimecc.diaryserver.mapper.FileMapper;
import com.jimecc.diaryserver.mapper.NoteMapper;
import com.jimecc.diaryserver.service.IndexService;
import com.jimecc.diaryserver.utils.UserUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Jim
 * @Description
 * @createTime 2023年02月23日
 */
@Slf4j
@Service
public class IndexServiceImpl implements IndexService {

    @Autowired
    NoteMapper noteMapper;

    @Autowired
    FileMapper fileMapper;

    public CommonResponse getDirectories(){
        List<Note> list = noteMapper.GetList4SideIndexAll(UserUtils.getId());
        return CommonList(list);
    }

    public CommonResponse getDirectoriesByColor(String color) {
        Note note = new Note(UserUtils.getId());
        note.setColor(color);
        List<Note> list = noteMapper.GetList4SideIndex(note);
        return CommonList(list);
    }

    public CommonResponse getCollectionDirectories(){
        Note note = new Note(UserUtils.getId());
        note.setCollection(1);
        List<Note> list = noteMapper.GetList4SideIndex(note);
        return CommonList(list);
    }

    public CommonResponse getDirectoriesByScore(int score) {
        Note note = new Note(UserUtils.getId());
        note.setScore(score);
        List<Note> list = noteMapper.GetList4SideIndex(note);
        return CommonList(list);
    }

    public CommonResponse searchDirectory(String title){
        Note note = new Note(UserUtils.getId());
        try{
            SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
            Date date = sdf.parse(title);
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            note.setCreate_date(sqlDate);
        } catch (ParseException e) {
            log.warn(title+"无法转换为时间--search");
        }
        note.setTitle(title);
        List<Note> list =  noteMapper.Search4SideIndex(note);
        if(list.size()>0){
            return CommonResponse.success("文章查找成功",list);
        }
        return CommonResponse.error("查找失败~没有符合的文件~");
    }

    public CommonResponse getFilesList4Article(int note_id){
        List<File> resultList = fileMapper.Files4SideIndex(UserUtils.getId(),note_id);
        if(resultList.size()>0){
            return CommonResponse.success("噔噔噔~一大波照片正在来袭~~",resultList);
        }
        return CommonResponse.error("该日记还没有创建相册哦，请上传照片保存美好回忆吧~");
    }

    public CommonResponse CommonList(List list){
        if(list.size()>0){
            return CommonResponse.success("「目录」加载完成",list);
        }
        return CommonResponse.error("「目录」加载失败，未找到符合内容的文章");
    }

}
