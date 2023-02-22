package com.jimecc.diaryserver.mapper;

import com.jimecc.diaryserver.entity.File;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Jim
 * @Description 文件
 * @createTime 2023年02月22日
 */
@Mapper
public interface FileMapper {

    List<File> Files4SideIndex(int user_id,int note_id);

    File Info4DelInFastDFS(int id,int user_id);

    int InsertFile(File file);

    int DeleteFileById(int id);

}
