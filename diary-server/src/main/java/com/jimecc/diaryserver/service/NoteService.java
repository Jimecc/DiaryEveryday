package com.jimecc.diaryserver.service;

import com.jimecc.diaryserver.entity.CommonResponse;
import com.jimecc.diaryserver.entity.Note;

/**
 * @author Jim
 * @Description 笔记 / 日记
 * @createTime 2023年02月22日
 */
public interface NoteService {
    CommonResponse deleteById(int id);

    CommonResponse createNote(Note note);

    CommonResponse getNoteById(int id);

    CommonResponse getLatestNote();
}
