package com.jimecc.diaryserver.service;

import com.jimecc.diaryserver.entity.CommonResponse;
import org.springframework.web.multipart.MultipartFile;


/**
 * @author Jim
 * @Description 文件
 * @createTime 2023年02月22日
 */
public interface FileService {

    CommonResponse deleteByid(int id);

    CommonResponse upload(MultipartFile file, int note_id);
}
