package com.jimecc.diaryserver.service;

import com.jimecc.diaryserver.entity.CommonResponse;
import com.jimecc.diaryserver.entity.File;
import com.jimecc.diaryserver.entity.Note;

import java.util.List;

/**
 * @author Jim
 * @Description 文章目录
 * @createTime 2023年02月23日
 */
public interface IndexService {
    CommonResponse getDirectories();

    CommonResponse getDirectoriesByColor(String color);

    CommonResponse getCollectionDirectories();

    CommonResponse getDirectoriesByScore(int score);

    CommonResponse searchDirectory(String title);

    CommonResponse getFilesList4Article(int note_id);
}
