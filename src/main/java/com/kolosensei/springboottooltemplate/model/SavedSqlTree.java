package com.kolosensei.springboottooltemplate.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author kolosensei
 * @since 2020-11-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("saved_sql_tree")
public class SavedSqlTree extends Model<SavedSqlTree> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 父节点id
     */
    @TableField("parent_id")
    private Long parentId;

    /**
     * 用户id
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 是否为目录 0：否 1：是
     */
    @TableField("is_folder")
    private Boolean folder;

    /**
     * 目录/sql路径
     */
    @TableField("path")
    private String path;

    /**
     * 节点名称
     */
    @TableField("node_name")
    private String nodeName;

    /**
     * sql信息 （针对sql数据）
     */
    @TableField("sql_info")
    private String sqlInfo;

    /**
     * 层级
     */
    @TableField("level")
    private Integer level;

    /**
     * sql类型：1 phoenix sql 2 spark sql
     */
    @TableField("sql_type")
    private Integer sqlType;

    /**
     * 入库时间
     */
    @TableField("entry_time")
    private LocalDateTime entryTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private LocalDateTime updateTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
