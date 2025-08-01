package com.easy.query.test.common;

import com.easy.query.core.exception.EasyQueryConcurrentException;
import org.checkerframework.checker.units.qual.C;
import org.jetbrains.annotations.NotNull;
import com.easy.query.core.basic.api.select.Query;
import com.easy.query.core.exception.AssertExceptionFactory;
import com.easy.query.core.metadata.EntityMetadata;
import com.easy.query.core.metadata.EntityMetadataManager;
import com.easy.query.core.metadata.ErrorMessage;

/**
 * create time 2023/12/1 13:16
 * 文件说明
 *
 * @author xuejiaming
 */
public class MyAssertExceptionFactory implements AssertExceptionFactory {
    private final EntityMetadataManager entityMetadataManager;

    public MyAssertExceptionFactory(EntityMetadataManager entityMetadataManager) {

        this.entityMetadataManager = entityMetadataManager;
    }

    @Override
    @NotNull
    public <T> RuntimeException createFindNotNullException(Query<T> query, String msg, String code) {
        if (msg == null && code == null) {
            EntityMetadata entityMetadata = entityMetadataManager.getEntityMetadata(query.queryClass());
            ErrorMessage errorMessage = entityMetadata.getErrorMessage();
            return new BusinessException(errorMessage.getNotNull());
        }
        return new BusinessException(code,msg);
    }

    @Override
    @NotNull
    public <T> RuntimeException createRequiredException(Query<T> query, String msg, String code) {
        if (msg == null && code == null) {
            EntityMetadata entityMetadata = entityMetadataManager.getEntityMetadata(query.queryClass());
            ErrorMessage errorMessage = entityMetadata.getErrorMessage();
            return new BusinessException(errorMessage.getNotNull());
        }
        return new BusinessException(code,msg);
    }

    @Override
    @NotNull
    public <T> RuntimeException createFirstNotNullException(Query<T> query, String msg, String code) {
        if (msg == null && code == null) {
            EntityMetadata entityMetadata = entityMetadataManager.getEntityMetadata(query.queryClass());
            ErrorMessage errorMessage = entityMetadata.getErrorMessage();
            return new BusinessException(errorMessage.getNotNull());
        }
        return new BusinessException(code,msg);
    }

    @Override
    @NotNull
    public <T> RuntimeException createSingleNotNullException(Query<T> query, String msg, String code) {
        if (msg == null && code == null) {
            EntityMetadata entityMetadata = entityMetadataManager.getEntityMetadata(query.queryClass());
            ErrorMessage errorMessage = entityMetadata.getErrorMessage();
            return new BusinessException(errorMessage.getNotNull());
        }
        return new BusinessException(code,msg);
    }

    @Override
    @NotNull
    public <T> RuntimeException createSingleMoreElementException(Query<T> query) {
        return new BusinessException("查询结果大于1条");
    }

    @Override
    @NotNull
    public RuntimeException createExecuteConcurrentException(long expectRows, long realRows, String msg, String code) {
        return new EasyQueryConcurrentException(msg, code);
    }
}
