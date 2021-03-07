package com.nijunyang.shiro.entity;

import lombok.Data;

import java.time.Instant;

/**
 * @author nijunyang
 * @since 2021/3/5
 */
@Data
public class BaseEntity {

    protected String createBy;

    protected Instant createDate;

    protected String updateBy;

    protected Instant updateDate;

    protected Integer version;
    /**
     * 数据逻辑删除标记
     */
    protected Boolean deleted;

}
