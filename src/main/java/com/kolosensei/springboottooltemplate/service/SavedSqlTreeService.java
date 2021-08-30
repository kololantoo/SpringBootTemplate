package com.kolosensei.springboottooltemplate.service;

import com.kolosensei.springboottooltemplate.model.SavedSqlTree;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author kolosensei
 * @since 2020-11-25
 */
public interface SavedSqlTreeService extends IService<SavedSqlTree> {

    /**
     * 根据id获取sql记录
     *
     * @param id
     * @return
     */
    SavedSqlTree getSavedSql(long id);
}
