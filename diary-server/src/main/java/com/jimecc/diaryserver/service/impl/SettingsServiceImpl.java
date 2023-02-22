package com.jimecc.diaryserver.service.impl;

import com.jimecc.diaryserver.mapper.SettingMapper;
import com.jimecc.diaryserver.service.SettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Jim
 * @Description 设置
 * @createTime 2023年02月22日
 */
@Service
public class SettingsServiceImpl implements SettingsService {

    @Autowired
    private SettingMapper settingMapper;

    @Override
    public void DefaultMarkdown(int user_id) {
        System.out.println(settingMapper.DefaultMarkdown(user_id));
    }
}
