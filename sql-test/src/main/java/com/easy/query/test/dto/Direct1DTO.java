package com.easy.query.test.dto;


import com.easy.query.core.annotation.NavigateFlat;
import lombok.Data;
import com.easy.query.core.annotation.Column;
import com.easy.query.core.annotation.Navigate;
import com.easy.query.core.enums.RelationTypeEnum;
import lombok.Data;

/**
 * this file automatically generated by easy-query struct dto mapping
 * 当前文件是easy-query自动生成的 结构化dto 映射
 * {@link com.easy.query.test.entity.direct.Direct1 }
 *
 * @author xuejiaming
 * @easy-query-dto schema: normal
 */
@Data
public class Direct1DTO {


    private String c1;
    private String c2;
    private String c3;
    private String c4;
    @NavigateFlat(pathAlias = "direct2.direct3")
    private InternalDirect3 direct3;


    /**
     * {@link com.easy.query.test.entity.direct.Direct3 }
     */
    @Data
    public static class InternalDirect3 {

        private String c11;
        private String c12;
        private String c13;


        @NavigateFlat(pathAlias = "direct4.c20")
        private String c20;
    }

}
