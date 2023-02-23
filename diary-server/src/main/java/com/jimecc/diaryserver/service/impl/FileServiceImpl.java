package com.jimecc.diaryserver.service.impl;

import com.jimecc.diaryserver.entity.CommonResponse;
import com.jimecc.diaryserver.entity.File;
import com.jimecc.diaryserver.mapper.FileMapper;
import com.jimecc.diaryserver.service.FileService;
import com.jimecc.diaryserver.utils.FastDFSUtils;
import com.jimecc.diaryserver.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * @author Jim
 * @Description 文件
 * @createTime 2023年02月22日
 */
@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileMapper fileMapper;

    public CommonResponse deleteByid(int id){
        File file = fileMapper.Info4DelInFastDFS(id,UserUtils.getId());
        int result;
        try{
            FastDFSUtils.deleteFile(file.getGroupName(),file.getRemoteFileName());
            result = fileMapper.DeleteFileById(id);
            if(result == 1){
                return CommonResponse.delSuc("删除成功！");
            }
        }catch (Exception e){
            return CommonResponse.delErr("删除失败..出错了..");
        }
        return CommonResponse.delErr("删除失败");
    }

    public int insertFile(File file){
        return fileMapper.InsertFile(file);
    }

    public CommonResponse upload(MultipartFile file, int note_id){
        String[] filePath = FastDFSUtils.upload(file);
        String url = FastDFSUtils.getTrackerUrl()+filePath[0]+"/"+filePath[1];
        System.out.println(url);
        File insertfile = new File();
        insertfile.setUser_id(UserUtils.getId());
        insertfile.setUrl(url);
        insertfile.setNote_id(note_id);
        insertfile.setName(file.getOriginalFilename());
        insertfile.setGroupName(filePath[0]);
        insertfile.setRemoteFileName(filePath[1]);
        java.util.Date date1 = new Date();
        java.sql.Date  date2 = new java.sql.Date(date1.getTime());
        insertfile.setCreate_date(date2);
        int result = insertFile(insertfile);
        if(result == 1){
            return CommonResponse.putSuc("文件上传成功");
        }
        return CommonResponse.putErr("文件上传失败");
    }
    
}
