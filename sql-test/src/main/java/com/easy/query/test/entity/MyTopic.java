package com.easy.query.test.entity;

import com.easy.query.core.annotation.Column;
import com.easy.query.core.annotation.EntityProxy;
import com.easy.query.core.annotation.Table;
import com.easy.query.test.entity.proxy.MyTopicProxy;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * create time 2024/5/25 20:12
 * 文件说明
 *
 * @author xuejiaming
 */
@Data
@Table("t_topic")
@EntityProxy
public class MyTopic extends BaseModel3<MyTopic,MyTopicProxy> {
    @Column(primaryKey = true)
    private String id;
    private Integer stars;
    private String title;
    private LocalDateTime createTime;
}
