package com.jimecc.diaryserver.service.impl;

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

    public List<Note> getDirectories(){
        return noteMapper.GetList4SideIndexAll(UserUtils.getId());
    }

    public List<Note> getDirectoriesByColor(String color) {
        Note note = new Note(UserUtils.getId());
        note.setColor(color);
        return noteMapper.GetList4SideIndex(note);
    }

    public List<Note> getCollectionDirectories(){
        Note note = new Note(UserUtils.getId());
        note.setCollection(1);
        return noteMapper.GetList4SideIndex(note);
    }

    public List<Note> getDirectoriesByScore(int score) {
        Note note = new Note(UserUtils.getId());
        note.setScore(score);
        return noteMapper.GetList4SideIndex(note);
    }

    public List<Note> searchDirectory(String title){
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
        return noteMapper.Search4SideIndex(note);
    }

    public List<File> getFilesList4Article(int note_id){
        return fileMapper.Files4SideIndex(UserUtils.getId(),note_id);
    }

}
