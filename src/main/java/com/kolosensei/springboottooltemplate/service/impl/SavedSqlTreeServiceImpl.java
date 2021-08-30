package com.kolosensei.springboottooltemplate.service.impl;

import com.kolosensei.springboottooltemplate.model.SavedSqlTree;
import com.kolosensei.springboottooltemplate.mapper.SavedSqlTreeMapper;
import com.kolosensei.springboottooltemplate.service.SavedSqlTreeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author kolosensei
 * @since 2020-11-25
 */
@Service
public class SavedSqlTreeServiceImpl extends ServiceImpl<SavedSqlTreeMapper, SavedSqlTree> implements SavedSqlTreeService {

    @Override
    public SavedSqlTree getSavedSql(long id) {

        return getById(id);
    }
}
