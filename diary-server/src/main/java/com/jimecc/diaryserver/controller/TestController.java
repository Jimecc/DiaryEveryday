package com.jimecc.diaryserver.controller;

import com.jimecc.diaryserver.mapper.SettingMapper;
import com.jimecc.diaryserver.service.SettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jim
 * @Description
 * @createTime 2023年02月22日
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private SettingMapper settingMapper;

    @Autowired
    private SettingsService settingsService;

    @GetMapping("/hello")
    public String hello(){
        return "Hello World";
    }

    @GetMapping("/defaultmarkdown")
    public void defaultmarkdown(){
        System.out.println(settingMapper.UpdateDefaultMarkdown(1,0));
    }

}
