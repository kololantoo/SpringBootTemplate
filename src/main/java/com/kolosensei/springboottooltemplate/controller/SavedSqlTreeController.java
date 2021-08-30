package com.kolosensei.springboottooltemplate.controller;


import com.kolosensei.springboottooltemplate.annotation.ResponseResultBody;
import com.kolosensei.springboottooltemplate.model.SavedSqlTree;
import com.kolosensei.springboottooltemplate.service.SavedSqlTreeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author kolosensei
 * @since 2020-11-25
 */
@RestController
@RequestMapping("/savedSqlTree")
@Api(tags = "保存的sql")
@ResponseResultBody
public class SavedSqlTreeController {

    @Autowired
    private SavedSqlTreeService savedSqlTreeService;

    @GetMapping("/sql/{id}")
    @ApiOperation("根据id获取sql")
    SavedSqlTree getSavedSql(@PathVariable long id) {
        return savedSqlTreeService.getSavedSql(id);
    }

    @GetMapping("/excp/{id}")
    @ApiOperation("根据id获取sql")
    SavedSqlTree getexception(@PathVariable long id) throws Exception {
        throw new Exception();
    }
}

