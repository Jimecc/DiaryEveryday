package com.jimecc.diaryserver.mapper;

import com.jimecc.diaryserver.entity.Note;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Jim
 * @Description 笔记
 * @createTime 2023年02月22日
 */
@Mapper
public interface NoteMapper {

    int DeleteById(int id);

    int Insert2Note(Note note);

    int UpdateById(Note note);

    Note SelectNoteById(int id,int user_id);

    Note GetLatestNote(int user_id);

    List<Note> GetList4SideIndex(Note note);

    List<Note> GetList4SideIndexAll(int user_id);

    List<Note> Search4SideIndex(Note note);
}
