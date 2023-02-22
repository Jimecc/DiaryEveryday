package com.jimecc.diaryserver.service.impl;

import com.jimecc.diaryserver.entity.CommonResponse;
import com.jimecc.diaryserver.entity.Note;
import com.jimecc.diaryserver.mapper.NoteMapper;
import com.jimecc.diaryserver.service.NoteService;
import com.jimecc.diaryserver.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author Jim
 * @Description
 * @createTime 2023年02月22日
 */
@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteMapper noteMapper;

    public CommonResponse deleteById(int id){
        if(noteMapper.DeleteById(id) == 1){
            return CommonResponse.success("删除成功");
        }
        return CommonResponse.error("删除失败");
    }

    public CommonResponse createNote(Note note){
        if(note.getId() != 0) {
            if (noteMapper.SelectNoteById(note.getId(),UserUtils.getId()) != null) {
                if (noteMapper.UpdateById(note) == 1) {
                    return CommonResponse.success("提交成功", note);
                } else {
                    return CommonResponse.error("提交失败");
                }
            }
        }
        note.setUser_id(UserUtils.getId());
        if(noteMapper.Insert2Note(note) == 0){
            return CommonResponse.error("插入失败");
        }
        return CommonResponse.success("插入成功",note);
    }

    public CommonResponse getNoteById(int id){
        Note note = noteMapper.SelectNoteById(id,UserUtils.getId());
        return CommonResponse.success("success",note);
    }

    public CommonResponse getLatestNote() {
        Note note = noteMapper.GetLatestNote(UserUtils.getId());
        return CommonResponse.success("success",note);
    }

}
