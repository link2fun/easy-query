package com.easy.query.test.entity.proxy;

import com.easy.query.core.expression.parser.core.available.TableAvailable;
import com.easy.query.core.proxy.AbstractProxyEntity;
import com.easy.query.core.proxy.SQLColumn;
import com.easy.query.core.proxy.SQLSelectAsExpression;
import com.easy.query.core.proxy.fetcher.AbstractFetcher;
import com.easy.query.core.proxy.core.EntitySQLContext;
import com.easy.query.test.entity.BlogEntity;
import com.easy.query.core.proxy.columns.types.SQLStringTypeColumn;
import com.easy.query.core.proxy.columns.types.SQLIntegerTypeColumn;
import com.easy.query.core.proxy.columns.types.SQLLocalDateTimeTypeColumn;
import com.easy.query.core.proxy.columns.types.SQLBigDecimalTypeColumn;
import com.easy.query.core.proxy.columns.types.SQLBooleanTypeColumn;
import com.easy.query.core.proxy.columns.SQLNavigateColumn;
import com.easy.query.core.proxy.columns.SQLManyQueryable;
import com.easy.query.core.proxy.columns.types.SQLAnyTypeColumn;

/**
 * this file automatically generated by easy-query, don't modify it
 * 当前文件是easy-query自动生成的请不要随意修改
 * 如果出现属性冲突请使用@ProxyProperty进行重命名
 *
 * @author easy-query
 */
public class BlogEntityProxy extends AbstractProxyEntity<BlogEntityProxy, BlogEntity> {

    private static final Class<BlogEntity> entityClass = BlogEntity.class;

    public static final BlogEntityProxy TABLE = createTable().createEmpty();

    public static BlogEntityProxy createTable() {
        return new BlogEntityProxy();
    }

    public BlogEntityProxy() {
    }

    /**
     * 标题
     * {@link BlogEntity#getTitle}
     */
    public SQLStringTypeColumn<BlogEntityProxy> title() {
        return getStringTypeColumn("title");
    }

    /**
     * 内容
     * {@link BlogEntity#getContent}
     */
    public SQLStringTypeColumn<BlogEntityProxy> content() {
        return getStringTypeColumn("content");
    }

    /**
     * 博客链接
     * {@link BlogEntity#getUrl}
     */
    public SQLStringTypeColumn<BlogEntityProxy> url() {
        return getStringTypeColumn("url");
    }

    /**
     * 点赞数
     * {@link BlogEntity#getStar}
     */
    public SQLIntegerTypeColumn<BlogEntityProxy> star() {
        return getIntegerTypeColumn("star");
    }

    /**
     * 发布时间
     * {@link BlogEntity#getPublishTime}
     */
    public SQLLocalDateTimeTypeColumn<BlogEntityProxy> publishTime() {
        return getLocalDateTimeTypeColumn("publishTime");
    }

    /**
     * 评分
     * {@link BlogEntity#getScore}
     */
    public SQLBigDecimalTypeColumn<BlogEntityProxy> score() {
        return getBigDecimalTypeColumn("score");
    }

    /**
     * 状态
     * {@link BlogEntity#getStatus}
     */
    public SQLIntegerTypeColumn<BlogEntityProxy> status() {
        return getIntegerTypeColumn("status");
    }

    /**
     * 排序
     * {@link BlogEntity#getOrder}
     */
    public SQLBigDecimalTypeColumn<BlogEntityProxy> order() {
        return getBigDecimalTypeColumn("order");
    }

    /**
     * 是否置顶
     * {@link BlogEntity#getIsTop}
     */
    public SQLBooleanTypeColumn<BlogEntityProxy> isTop() {
        return getBooleanTypeColumn("isTop");
    }

    /**
     * 是否置顶
     * {@link BlogEntity#getTop}
     */
    public SQLBooleanTypeColumn<BlogEntityProxy> top() {
        return getBooleanTypeColumn("top");
    }

    /**
     * {@link BlogEntity#getUsers}
     */
    public SQLManyQueryable<BlogEntityProxy, com.easy.query.test.entity.proxy.SysUserProxy, com.easy.query.test.entity.SysUser> users() {
        return getNavigateMany("users", new com.easy.query.test.entity.proxy.SysUserProxy());
    }

    /**
     * {@link BlogEntity#getId}
     */
    public SQLStringTypeColumn<BlogEntityProxy> id() {
        return getStringTypeColumn("id");
    }

    /**
     * 创建时间;创建时间
     * {@link BlogEntity#getCreateTime}
     */
    public SQLLocalDateTimeTypeColumn<BlogEntityProxy> createTime() {
        return getLocalDateTimeTypeColumn("createTime");
    }

    /**
     * 修改时间;修改时间
     * {@link BlogEntity#getUpdateTime}
     */
    public SQLLocalDateTimeTypeColumn<BlogEntityProxy> updateTime() {
        return getLocalDateTimeTypeColumn("updateTime");
    }

    /**
     * 创建人;创建人
     * {@link BlogEntity#getCreateBy}
     */
    public SQLStringTypeColumn<BlogEntityProxy> createBy() {
        return getStringTypeColumn("createBy");
    }

    /**
     * 修改人;修改人
     * {@link BlogEntity#getUpdateBy}
     */
    public SQLStringTypeColumn<BlogEntityProxy> updateBy() {
        return getStringTypeColumn("updateBy");
    }

    /**
     * 是否删除;是否删除
     * {@link BlogEntity#getDeleted}
     */
    public SQLBooleanTypeColumn<BlogEntityProxy> deleted() {
        return getBooleanTypeColumn("deleted");
    }


    @Override
    public Class<BlogEntity> getEntityClass() {
        return entityClass;
    }


    /**
     * 数据库列的简单获取
     *
     * @return
     */
    public BlogEntityProxyFetcher FETCHER = new BlogEntityProxyFetcher(this, null, SQLSelectAsExpression.empty);


    public static class BlogEntityProxyFetcher extends AbstractFetcher<BlogEntityProxy, BlogEntity, BlogEntityProxyFetcher> {

        public BlogEntityProxyFetcher(BlogEntityProxy proxy, BlogEntityProxyFetcher prev, SQLSelectAsExpression sqlSelectAsExpression) {
            super(proxy, prev, sqlSelectAsExpression);
        }


        /**
         * 标题
         * {@link BlogEntity#getTitle}
         */
        public BlogEntityProxyFetcher title() {
            return add(getProxy().title());
        }

        /**
         * 内容
         * {@link BlogEntity#getContent}
         */
        public BlogEntityProxyFetcher content() {
            return add(getProxy().content());
        }

        /**
         * 博客链接
         * {@link BlogEntity#getUrl}
         */
        public BlogEntityProxyFetcher url() {
            return add(getProxy().url());
        }

        /**
         * 点赞数
         * {@link BlogEntity#getStar}
         */
        public BlogEntityProxyFetcher star() {
            return add(getProxy().star());
        }

        /**
         * 发布时间
         * {@link BlogEntity#getPublishTime}
         */
        public BlogEntityProxyFetcher publishTime() {
            return add(getProxy().publishTime());
        }

        /**
         * 评分
         * {@link BlogEntity#getScore}
         */
        public BlogEntityProxyFetcher score() {
            return add(getProxy().score());
        }

        /**
         * 状态
         * {@link BlogEntity#getStatus}
         */
        public BlogEntityProxyFetcher status() {
            return add(getProxy().status());
        }

        /**
         * 排序
         * {@link BlogEntity#getOrder}
         */
        public BlogEntityProxyFetcher order() {
            return add(getProxy().order());
        }

        /**
         * 是否置顶
         * {@link BlogEntity#getIsTop}
         */
        public BlogEntityProxyFetcher isTop() {
            return add(getProxy().isTop());
        }

        /**
         * 是否置顶
         * {@link BlogEntity#getTop}
         */
        public BlogEntityProxyFetcher top() {
            return add(getProxy().top());
        }

        /**
         * {@link BlogEntity#getId}
         */
        public BlogEntityProxyFetcher id() {
            return add(getProxy().id());
        }

        /**
         * 创建时间;创建时间
         * {@link BlogEntity#getCreateTime}
         */
        public BlogEntityProxyFetcher createTime() {
            return add(getProxy().createTime());
        }

        /**
         * 修改时间;修改时间
         * {@link BlogEntity#getUpdateTime}
         */
        public BlogEntityProxyFetcher updateTime() {
            return add(getProxy().updateTime());
        }

        /**
         * 创建人;创建人
         * {@link BlogEntity#getCreateBy}
         */
        public BlogEntityProxyFetcher createBy() {
            return add(getProxy().createBy());
        }

        /**
         * 修改人;修改人
         * {@link BlogEntity#getUpdateBy}
         */
        public BlogEntityProxyFetcher updateBy() {
            return add(getProxy().updateBy());
        }

        /**
         * 是否删除;是否删除
         * {@link BlogEntity#getDeleted}
         */
        public BlogEntityProxyFetcher deleted() {
            return add(getProxy().deleted());
        }


        @Override
        protected BlogEntityProxyFetcher createFetcher(BlogEntityProxy cp, AbstractFetcher<BlogEntityProxy, BlogEntity, BlogEntityProxyFetcher> prev, SQLSelectAsExpression sqlSelectExpression) {
            return new BlogEntityProxyFetcher(cp, this, sqlSelectExpression);
        }
    }

}
