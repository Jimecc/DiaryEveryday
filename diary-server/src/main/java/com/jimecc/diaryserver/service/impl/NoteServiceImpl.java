package com.jimecc.diaryserver.service.impl;

import com.jimecc.diaryserver.entity.CommonResponse;
import com.jimecc.diaryserver.entity.File;
import com.jimecc.diaryserver.entity.Note;
import com.jimecc.diaryserver.mapper.FileMapper;
import com.jimecc.diaryserver.mapper.NoteMapper;
import com.jimecc.diaryserver.service.IndexService;
import com.jimecc.diaryserver.service.NoteService;
import com.jimecc.diaryserver.utils.FastDFSUtils;
import com.jimecc.diaryserver.utils.UserUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author Jim
 * @Description
 * @createTime 2023年02月22日
 */
@Slf4j
@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteMapper noteMapper;

    @Autowired
    private FileMapper fileMapper;

    public CommonResponse deleteById(int id){
        try {
            List<File> dellist = fileMapper.Info4DeleteFileWhenDeleteNote(UserUtils.getId(), id);
            for (File file : dellist) {
                FastDFSUtils.deleteFile(file.getGroupName(), file.getRemoteFileName());
            }
        }catch (Exception e){
            log.error("删除文章失败，因为删除附属相册失败");
            return CommonResponse.delErr("删除文章失败，因为删除附属相册失败");
        }
        int delfile = fileMapper.DeleteFileByNoteId(id,UserUtils.getId());
        if(noteMapper.DeleteById(id) == 1){
            return CommonResponse.delSuc("删除成功，并且同时删除了该相册携带的「"+delfile+"」张照片");
        }
        return CommonResponse.delErr("删除失败");
    }

    public CommonResponse createNote(Note note){
        if(note.getId() != 0) {
            if (noteMapper.SelectNoteById(note.getId(),UserUtils.getId()) != null) {
                if (noteMapper.UpdateById(note) == 1) {
                    return CommonResponse.putSuc("提交成功", note);
                } else {
                    return CommonResponse.putErr("提交失败");
                }
            }
        }
        note.setUser_id(UserUtils.getId());
        if(noteMapper.Insert2Note(note) == 0){
            return CommonResponse.putErr("插入失败");
        }
        return CommonResponse.putSuc("插入成功",note);
    }

    public CommonResponse getNoteById(int id){
        Note note = noteMapper.SelectNoteById(id,UserUtils.getId());
        return CommonResponse.selSuc("success",note);
    }

    public CommonResponse getLatestNote() {
        Note note = noteMapper.GetLatestNote(UserUtils.getId());
        return CommonResponse.selSuc("success",note);
    }

}
