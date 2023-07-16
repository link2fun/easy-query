package com.easy.query.test.entity.school;

import com.easy.query.core.annotation.Column;
import com.easy.query.core.annotation.Navigate;
import com.easy.query.core.annotation.Table;
import com.easy.query.core.enums.RelationTypeEnum;
import lombok.Data;

/**
 * create time 2023/7/16 11:28
 * 文件说明
 *
 * @author xuejiaming
 */
@Table("school_student")
@Data
public class SchoolStudent {
    @Column(primaryKey = true)
    private String id;
    private String classId;
    private String name;
    @Navigate(value = RelationTypeEnum.ManyToOne,selfProperty = "classId",targetProperty = "id")
    private SchoolClass schoolClass;
    @Navigate(value = RelationTypeEnum.OneToOne,targetProperty = "studentId")
    private SchoolStudentAddress schoolStudentAddress;
}
