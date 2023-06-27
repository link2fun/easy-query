package com.easy.query.test;

import com.easy.query.core.api.pagination.EasyPageResult;
import com.easy.query.test.dto.BlogEntityTest;
import com.easy.query.test.dto.proxy.BlogEntityTestProxy;
import com.easy.query.test.entity.BlogEntity;
import com.easy.query.test.entity.Topic;
import com.easy.query.test.entity.TopicAuto;
import com.easy.query.test.entity.proxy.BlogEntityProxy;
import com.easy.query.test.entity.proxy.TopicAutoProxy;
import com.easy.query.test.entity.proxy.TopicProxy;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static com.easy.query.test.entity.base.TopicProxy.TOPIC_PROXY;
import static com.easy.query.test.entity.base.TopicTestProxy.TOPIC_TEST_PROXY;

/**
 * create time 2023/6/8 21:38
 * 文件说明
 *
 * @author xuejiaming
 */
public class QueryTest3 extends BaseTest {

    @Test
    public void query124() {
        String toSql = easyQuery
                .queryable(Topic.class)
                .leftJoin(BlogEntity.class, (t, t1) -> t.eq(t1, Topic::getId, BlogEntity::getId))
                .leftJoin(BlogEntity.class, (t, t1, t2) -> t.eq(t2, Topic::getId, BlogEntity::getId))
                .leftJoin(BlogEntity.class, (t, t1, t2, t3) -> t.eq(t3, Topic::getId, BlogEntity::getId))
                .where(o -> o.eq(Topic::getId, "3"))
                .limit(1, 2)
                .toSQL();
        Assert.assertEquals("SELECT t.`id`,t.`stars`,t.`title`,t.`create_time` FROM `t_topic` t LEFT JOIN `t_blog` t1 ON t1.`deleted` = ? AND t.`id` = t1.`id` LEFT JOIN `t_blog` t2 ON t2.`deleted` = ? AND t.`id` = t2.`id` LEFT JOIN `t_blog` t3 ON t3.`deleted` = ? AND t.`id` = t3.`id` WHERE t.`id` = ? LIMIT 2 OFFSET 1", toSql);
    }

    @Test
    public void query125() {
        String toSql = easyQuery
                .queryable(Topic.class)
                .leftJoin(BlogEntity.class, (t, t1) -> t.eq(t1, Topic::getId, BlogEntity::getId))
                .innerJoin(BlogEntity.class, (t, t1, t2) -> t.eq(t2, Topic::getId, BlogEntity::getId))
                .innerJoin(BlogEntity.class, (t, t1, t2, t3) -> t.eq(t3, Topic::getId, BlogEntity::getId))
                .where(o -> o.eq(Topic::getId, "3"))
                .select(BlogEntity.class, (t, t1, t2, t3) -> t.column(Topic::getId)
                        .then(t1)
                        .column(BlogEntity::getTitle)
                        .then(t2)
                        .column(BlogEntity::getStar))
                .limit(1, 2)
                .toSQL();
        Assert.assertEquals("SELECT t.`id`,t1.`title`,t2.`star` FROM `t_topic` t LEFT JOIN `t_blog` t1 ON t1.`deleted` = ? AND t.`id` = t1.`id` INNER JOIN `t_blog` t2 ON t2.`deleted` = ? AND t.`id` = t2.`id` INNER JOIN `t_blog` t3 ON t3.`deleted` = ? AND t.`id` = t3.`id` WHERE t.`id` = ? LIMIT 2 OFFSET 1", toSql);
    }

    @Test
    public void query126() {
        {

            List<BlogEntity> list = easyQuery
                    .queryable(Topic.class)
                    .leftJoin(BlogEntity.class, (t, t1) -> t.eq(t1, Topic::getId, BlogEntity::getId))
                    .innerJoin(BlogEntity.class, (t, t1, t2) -> t.eq(t2, Topic::getId, BlogEntity::getId))
                    .rightJoin(BlogEntity.class, (t, t1, t2, t3) -> t.eq(t3, Topic::getId, BlogEntity::getId))
                    .where(o -> o.eq(Topic::getId, "3"))
                    .select(BlogEntity.class, (t, t1, t2, t3) -> t.column(Topic::getId)
                            .then(t1)
                            .column(BlogEntity::getTitle)
                            .then(t2)
                            .column(BlogEntity::getStar))
                    .limit(1, 2)
                    .toList();
            Assert.assertEquals(0, list.size());
        }
        {

            List<BlogEntity> list = easyQuery
                    .queryable(Topic.class)
                    .leftJoin(BlogEntity.class, (t, t1) -> t.eq(t1, Topic::getId, BlogEntity::getId))
                    .innerJoin(BlogEntity.class, (t, t1, t2) -> t.eq(t2, Topic::getId, BlogEntity::getId))
                    .leftJoin(BlogEntity.class, (t, t1, t2, t3) -> t.eq(t3, Topic::getId, BlogEntity::getId))
                    .where(o -> o.eq(Topic::getId, "3"))
                    .select(BlogEntity.class, (t, t1, t2, t3) -> t.column(Topic::getId)
                            .then(t1)
                            .column(BlogEntity::getTitle)
                            .then(t2)
                            .column(BlogEntity::getStar))
                    .limit(1)
                    .toList();
            Assert.assertEquals(1, list.size());
        }
    }

    @Test
    public void query112() {
        BigDecimal bigDecimal = easyQuery
                .queryable(Topic.class)
                .leftJoin(BlogEntity.class, (t, t1) -> t.eq(t1, Topic::getId, BlogEntity::getId))
                .innerJoin(BlogEntity.class, (t, t1, t2) -> t.eq(t2, Topic::getId, BlogEntity::getId))
                .leftJoin(BlogEntity.class, (t, t1, t2, t3) -> t.eq(t3, Topic::getId, BlogEntity::getId))
                .where(o -> o.eq(Topic::getId, "3"))
                .sumOrDefault((t, t1, t2, t3) -> t1.column(BlogEntity::getScore), BigDecimal.ZERO);
        Assert.assertTrue(new BigDecimal("1.2").compareTo(bigDecimal) == 0);
    }

    @Test
    public void query113() {
        BigDecimal bigDecimal = easyQuery
                .queryable(Topic.class)
                .leftJoin(BlogEntity.class, (t, t1) -> t.eq(t1, Topic::getId, BlogEntity::getId))
                .leftJoin(BlogEntity.class, (t, t1, t2) -> t.eq(t2, Topic::getId, BlogEntity::getId))
                .leftJoin(BlogEntity.class, (t, t1, t2, t3) -> t.eq(t3, Topic::getId, BlogEntity::getId))
                .where(o -> o.eq(Topic::getId, "3x"))
                .sumOrNull((t, t1, t2, t3) -> t1.column(BlogEntity::getScore));
        Assert.assertTrue(bigDecimal == null);
    }

    @Test
    public void query114() {
        BigDecimal bigDecimal = easyQuery
                .queryable(Topic.class)
                .leftJoin(BlogEntity.class, (t, t1) -> t.eq(t1, Topic::getId, BlogEntity::getId))
                .leftJoin(BlogEntity.class, (t, t1, t2) -> t.eq(t2, Topic::getId, BlogEntity::getId))
                .leftJoin(BlogEntity.class, (t, t1, t2, t3) -> t.eq(t3, Topic::getId, BlogEntity::getId))
                .where(o -> o.eq(Topic::getId, "3"))
                .sumBigDecimalOrNull((t, t1, t2, t3) -> t3.column(BlogEntity::getScore));
        Assert.assertTrue(new BigDecimal("1.2").compareTo(bigDecimal) == 0);
    }

    @Test
    public void query115() {
        BigDecimal bigDecimal = easyQuery
                .queryable(Topic.class)
                .leftJoin(BlogEntity.class, (t, t1) -> t.eq(t1, Topic::getId, BlogEntity::getId))
                .leftJoin(BlogEntity.class, (t, t1, t2) -> t.eq(t2, Topic::getId, BlogEntity::getId))
                .leftJoin(BlogEntity.class, (t, t1, t2, t3) -> t.eq(t3, Topic::getId, BlogEntity::getId))
                .where(o -> o.eq(Topic::getId, "3x"))
                .sumBigDecimalOrDefault((t, t1, t2, t3) -> t3.column(BlogEntity::getScore), null);
        Assert.assertTrue(bigDecimal == null);
        BigDecimal bigDecimal1 = easyQuery
                .queryable(Topic.class)
                .leftJoin(BlogEntity.class, (t, t1) -> t.eq(t1, Topic::getId, BlogEntity::getId))
                .innerJoin(BlogEntity.class, (t, t1, t2) -> t.eq(t2, Topic::getId, BlogEntity::getId))
                .leftJoin(BlogEntity.class, (t, t1, t2, t3) -> t.eq(t3, Topic::getId, BlogEntity::getId))
                .where(o -> o.eq(Topic::getId, "3x"))
                .sumBigDecimalOrDefault((t, t1, t2, t3) -> t3.column(BlogEntity::getScore), BigDecimal.ZERO);
        Assert.assertTrue(BigDecimal.ZERO.compareTo(bigDecimal1) == 0);
    }

    @Test
    public void query116() {
        BigDecimal bigDecimal = easyQuery
                .queryable(Topic.class)
                .leftJoin(BlogEntity.class, (t, t1) -> t.eq(t1, Topic::getId, BlogEntity::getId))
                .leftJoin(BlogEntity.class, (t, t1, t2) -> t.eq(t2, Topic::getId, BlogEntity::getId))
                .leftJoin(BlogEntity.class, (t, t1, t2, t3) -> t.eq(t3, Topic::getId, BlogEntity::getId))
                .where(o -> o.eq(Topic::getId, "3x"))
                .maxOrNull((t, t1, t2, t3) -> t3.column(BlogEntity::getScore));
        Assert.assertTrue(bigDecimal == null);
        BigDecimal bigDecimal1 = easyQuery
                .queryable(Topic.class)
                .leftJoin(BlogEntity.class, (t, t1) -> t.eq(t1, Topic::getId, BlogEntity::getId))
                .leftJoin(BlogEntity.class, (t, t1, t2) -> t.eq(t2, Topic::getId, BlogEntity::getId))
                .leftJoin(BlogEntity.class, (t, t1, t2, t3) -> t.eq(t3, Topic::getId, BlogEntity::getId))
                .where(o -> o.eq(Topic::getId, "3x"))
                .maxOrDefault((t, t1, t2, t3) -> t1.column(BlogEntity::getScore), BigDecimal.ZERO);
        Assert.assertTrue(BigDecimal.ZERO.compareTo(bigDecimal1) == 0);
    }

    @Test
    public void query117() {
        BigDecimal bigDecimal = easyQuery
                .queryable(Topic.class)
                .leftJoin(BlogEntity.class, (t, t1) -> t.eq(t1, Topic::getId, BlogEntity::getId))
                .leftJoin(BlogEntity.class, (t, t1, t2) -> t.eq(t2, Topic::getId, BlogEntity::getId))
                .innerJoin(BlogEntity.class, (t, t1, t2, t3) -> t.eq(t3, Topic::getId, BlogEntity::getId))
                .where(o -> o.in(Topic::getId, Arrays.asList("3x", "3")))
                .maxOrNull((t, t1, t2, t3) -> t3.column(BlogEntity::getScore));
        Assert.assertTrue(new BigDecimal("1.2").compareTo(bigDecimal) == 0);
    }

    @Test
    public void query118() {
        BigDecimal bigDecimal = easyQuery
                .queryable(Topic.class)
                .leftJoin(BlogEntity.class, (t, t1) -> t.eq(t1, Topic::getId, BlogEntity::getId))
                .innerJoin(BlogEntity.class, (t, t1, t2) -> t.eq(t2, Topic::getId, BlogEntity::getId))
                .rightJoin(BlogEntity.class, (t, t1, t2, t3) -> t.eq(t3, Topic::getId, BlogEntity::getId))
                .where(o -> o.eq(Topic::getId, "3x"))
                .minOrNull((t, t1, t2, t3) -> t3.column(BlogEntity::getScore));
        Assert.assertTrue(bigDecimal == null);
        BigDecimal bigDecimal1 = easyQuery
                .queryable(Topic.class)
                .leftJoin(BlogEntity.class, (t, t1) -> t.eq(t1, Topic::getId, BlogEntity::getId))
                .innerJoin(BlogEntity.class, (t, t1, t2) -> t.eq(t2, Topic::getId, BlogEntity::getId))
                .rightJoin(BlogEntity.class, (t, t1, t2, t3) -> t.eq(t3, Topic::getId, BlogEntity::getId))
                .where(o -> o.eq(Topic::getId, "3x"))
                .minOrDefault((t, t1, t2, t3) -> t3.column(BlogEntity::getScore), BigDecimal.ZERO);
        Assert.assertTrue(BigDecimal.ZERO.compareTo(bigDecimal1) == 0);
    }

    @Test
    public void query119() {
        BigDecimal bigDecimal = easyQuery
                .queryable(Topic.class)
                .leftJoin(BlogEntity.class, (t, t1) -> t.eq(t1, Topic::getId, BlogEntity::getId))
                .innerJoin(BlogEntity.class, (t, t1, t2) -> t.eq(t2, Topic::getId, BlogEntity::getId))
                .leftJoin(BlogEntity.class, (t, t1, t2, t3) -> t.eq(t3, Topic::getId, BlogEntity::getId))
                .where(o -> o.in(Topic::getId, Arrays.asList("3x", "3")))
                .minOrNull((t, t1, t2, t3) -> t3.column(BlogEntity::getScore));
        Assert.assertTrue(new BigDecimal("1.2").compareTo(bigDecimal) == 0);
    }

    @Test
    public void query120() {
        {
            BigDecimal bigDecimal = easyQuery
                    .queryable(Topic.class)
                    .leftJoin(BlogEntity.class, (t, t1) -> t.eq(t1, Topic::getId, BlogEntity::getId))
                    .innerJoin(BlogEntity.class, (t, t1, t2) -> t.eq(t2, Topic::getId, BlogEntity::getId))
                    .leftJoin(BlogEntity.class, (t, t1, t2, t3) -> t.eq(t3, Topic::getId, BlogEntity::getId))
                    .where(o -> o.in(Topic::getId, Arrays.asList("3", "2", "5")))
                    .avgBigDecimalOrNull((t, t1, t2, t3) -> t1.column(BlogEntity::getScore));
            Assert.assertTrue(new BigDecimal("1.2").compareTo(bigDecimal) == 0);
        }
        {
            BigDecimal bigDecimal = easyQuery
                    .queryable(Topic.class)
                    .leftJoin(BlogEntity.class, (t, t1) -> t.eq(t1, Topic::getId, BlogEntity::getId))
                    .innerJoin(BlogEntity.class, (t, t1, t2) -> t.eq(t2, Topic::getId, BlogEntity::getId))
                    .leftJoin(BlogEntity.class, (t, t1, t2, t3) -> t.eq(t3, Topic::getId, BlogEntity::getId))
                    .where(o -> o.in(Topic::getId, Arrays.asList("3x", "2x", "5x")))
                    .avgBigDecimalOrNull((t, t1, t2, t3) -> t3.column(BlogEntity::getScore));
            Assert.assertTrue(bigDecimal == null);
        }
        {
            BigDecimal bigDecimal = easyQuery
                    .queryable(Topic.class)
                    .leftJoin(BlogEntity.class, (t, t1) -> t.eq(t1, Topic::getId, BlogEntity::getId))
                    .innerJoin(BlogEntity.class, (t, t1, t2) -> t.eq(t2, Topic::getId, BlogEntity::getId))
                    .leftJoin(BlogEntity.class, (t, t1, t2, t3) -> t.eq(t3, Topic::getId, BlogEntity::getId))
                    .where(o -> o.in(Topic::getId, Arrays.asList("3x", "2x", "5")))
                    .avgBigDecimalOrDefault((t, t1, t2, t3) -> t3.column(BlogEntity::getScore), BigDecimal.ZERO);
            Assert.assertTrue(new BigDecimal("1.2").compareTo(bigDecimal) == 0);
        }
        {
            Double bigDecimal = easyQuery
                    .queryable(Topic.class)
                    .leftJoin(BlogEntity.class, (t, t1) -> t.eq(t1, Topic::getId, BlogEntity::getId))
                    .leftJoin(BlogEntity.class, (t, t1, t2) -> t.eq(t2, Topic::getId, BlogEntity::getId))
                    .leftJoin(BlogEntity.class, (t, t1, t2, t3) -> t.eq(t3, Topic::getId, BlogEntity::getId))
                    .where(o -> o.in(Topic::getId, Arrays.asList("3x", "2x", "5")))
                    .avgOrDefault((t, t1, t2, t3) -> t3.column(BlogEntity::getStar), null);
            Assert.assertTrue(5d == bigDecimal);
        }
        {
            BigDecimal bigDecimal = easyQuery
                    .queryable(Topic.class)
                    .leftJoin(BlogEntity.class, (t, t1) -> t.eq(t1, Topic::getId, BlogEntity::getId))
                    .leftJoin(BlogEntity.class, (t, t1, t2) -> t.eq(t2, Topic::getId, BlogEntity::getId))
                    .leftJoin(BlogEntity.class, (t, t1, t2, t3) -> t.eq(t3, Topic::getId, BlogEntity::getId))
                    .where(o -> o.in(Topic::getId, Arrays.asList("3x", "2x", "5")))
                    .avgOrDefault((t, t1, t2, t3) -> t3.column(BlogEntity::getStar), null, BigDecimal.class);
            Assert.assertTrue(new BigDecimal("5").compareTo(bigDecimal) == 0);
        }
        {
            Float bigDecimal = easyQuery
                    .queryable(Topic.class)
                    .leftJoin(BlogEntity.class, (t, t1) -> t.eq(t1, Topic::getId, BlogEntity::getId))
                    .leftJoin(BlogEntity.class, (t, t1, t2) -> t.eq(t2, Topic::getId, BlogEntity::getId))
                    .leftJoin(BlogEntity.class, (t, t1, t2, t3) -> t.eq(t3, Topic::getId, BlogEntity::getId))
                    .where(o -> o.in(Topic::getId, Arrays.asList("3x", "2x", "5")))
                    .avgOrDefault((t, t1, t2, t3) -> t1.column(BlogEntity::getStar), null, Float.class);
            Assert.assertTrue(5f == bigDecimal);
        }
    }

    @Test
    public void query121() {
        {
            String sql = easyQuery
                    .queryable(Topic.class)
                    .leftJoin(BlogEntity.class, (t, t1) -> t.eq(t1, Topic::getId, BlogEntity::getId))
                    .leftJoin(BlogEntity.class, (t, t1, t2) -> t.eq(t2, Topic::getId, BlogEntity::getId))
                    .leftJoin(BlogEntity.class, (t, t1, t2, t3) -> t.eq(t3, Topic::getId, BlogEntity::getId))
                    .where(o -> o.in(Topic::getId, Arrays.asList("3", "2", "5")))
                    .orderByAsc((t, t1, t2, t3) -> t1.column(BlogEntity::getOrder)).toSQL();
            Assert.assertEquals("SELECT t.`id`,t.`stars`,t.`title`,t.`create_time` FROM `t_topic` t LEFT JOIN `t_blog` t1 ON t1.`deleted` = ? AND t.`id` = t1.`id` LEFT JOIN `t_blog` t2 ON t2.`deleted` = ? AND t.`id` = t2.`id` LEFT JOIN `t_blog` t3 ON t3.`deleted` = ? AND t.`id` = t3.`id` WHERE t.`id` IN (?,?,?) ORDER BY t1.`order` ASC", sql);
        }
        {
            String sql = easyQuery
                    .queryable(Topic.class)
                    .leftJoin(BlogEntity.class, (t, t1) -> t.eq(t1, Topic::getId, BlogEntity::getId))
                    .leftJoin(BlogEntity.class, (t, t1, t2) -> t.eq(t2, Topic::getId, BlogEntity::getId))
                    .innerJoin(BlogEntity.class, (t, t1, t2, t3) -> t.eq(t3, Topic::getId, BlogEntity::getId))
                    .where(o -> o.in(Topic::getId, Arrays.asList("3", "2", "5")))
                    .orderByAsc(false, (t, t1, t2, t3) -> t1.column(BlogEntity::getOrder))
                    .orderByAsc(true, (t, t1, t2, t3) -> t3.column(BlogEntity::getId)).toSQL();
            Assert.assertEquals("SELECT t.`id`,t.`stars`,t.`title`,t.`create_time` FROM `t_topic` t LEFT JOIN `t_blog` t1 ON t1.`deleted` = ? AND t.`id` = t1.`id` LEFT JOIN `t_blog` t2 ON t2.`deleted` = ? AND t.`id` = t2.`id` INNER JOIN `t_blog` t3 ON t3.`deleted` = ? AND t.`id` = t3.`id` WHERE t.`id` IN (?,?,?) ORDER BY t3.`id` ASC", sql);
        }
        {
            String sql = easyQuery
                    .queryable(Topic.class)
                    .leftJoin(BlogEntity.class, (t, t1) -> t.eq(t1, Topic::getId, BlogEntity::getId))
                    .leftJoin(BlogEntity.class, (t, t1, t2) -> t.eq(t2, Topic::getId, BlogEntity::getId))
                    .rightJoin(BlogEntity.class, (t, t1, t2, t3) -> t.eq(t3, Topic::getId, BlogEntity::getId))
                    .where(o -> o.in(Topic::getId, Arrays.asList("3", "2", "5")))
                    .orderByDesc((t, t1, t2, t3) -> t3.column(BlogEntity::getOrder)).toSQL();
            Assert.assertEquals("SELECT t.`id`,t.`stars`,t.`title`,t.`create_time` FROM `t_topic` t LEFT JOIN `t_blog` t1 ON t1.`deleted` = ? AND t.`id` = t1.`id` LEFT JOIN `t_blog` t2 ON t2.`deleted` = ? AND t.`id` = t2.`id` RIGHT JOIN `t_blog` t3 ON t3.`deleted` = ? AND t.`id` = t3.`id` WHERE t.`id` IN (?,?,?) ORDER BY t3.`order` DESC", sql);
        }
        {
            String sql = easyQuery
                    .queryable(Topic.class)
                    .leftJoin(BlogEntity.class, (t, t1) -> t.eq(t1, Topic::getId, BlogEntity::getId))
                    .leftJoin(BlogEntity.class, (t, t1, t2) -> t.eq(t2, Topic::getId, BlogEntity::getId))
                    .leftJoin(BlogEntity.class, (t, t1, t2, t3) -> t.eq(t3, Topic::getId, BlogEntity::getId))
                    .where(o -> o.in(Topic::getId, Arrays.asList("3", "2", "5")))
                    .orderByDesc(false, (t, t1, t2, t3) -> t3.column(BlogEntity::getOrder))
                    .orderByDesc(true, (t, t1, t2, t3) -> t3.column(BlogEntity::getId).column(BlogEntity::getOrder)).toSQL();
            Assert.assertEquals("SELECT t.`id`,t.`stars`,t.`title`,t.`create_time` FROM `t_topic` t LEFT JOIN `t_blog` t1 ON t1.`deleted` = ? AND t.`id` = t1.`id` LEFT JOIN `t_blog` t2 ON t2.`deleted` = ? AND t.`id` = t2.`id` LEFT JOIN `t_blog` t3 ON t3.`deleted` = ? AND t.`id` = t3.`id` WHERE t.`id` IN (?,?,?) ORDER BY t3.`id` DESC,t3.`order` DESC", sql);
        }
    }

    @Test
    public void query122() {
        {
            String sql = easyQuery
                    .queryable(Topic.class)
                    .leftJoin(BlogEntity.class, (t, t1) -> t.eq(t1, Topic::getId, BlogEntity::getId))
                    .leftJoin(BlogEntity.class, (t, t1, t2) -> t.eq(t2, Topic::getId, BlogEntity::getId))
                    .leftJoin(BlogEntity.class, (t, t1, t2, t3) -> t.eq(t3, Topic::getId, BlogEntity::getId))
                    .where(o -> o.in(Topic::getId, Arrays.asList("3", "2", "5")))
                    .groupBy(o -> o.column(Topic::getId))
                    .orderByAsc((t, t1, t2, t3) -> t1.column(BlogEntity::getOrder)).toSQL();
            Assert.assertEquals("SELECT t.`id` FROM `t_topic` t LEFT JOIN `t_blog` t1 ON t1.`deleted` = ? AND t.`id` = t1.`id` LEFT JOIN `t_blog` t2 ON t2.`deleted` = ? AND t.`id` = t2.`id` LEFT JOIN `t_blog` t3 ON t3.`deleted` = ? AND t.`id` = t3.`id` WHERE t.`id` IN (?,?,?) GROUP BY t.`id` ORDER BY t1.`order` ASC", sql);
        }
        {
            String sql = easyQuery
                    .queryable(Topic.class)
                    .leftJoin(BlogEntity.class, (t, t1) -> t.eq(t1, Topic::getId, BlogEntity::getId))
                    .leftJoin(BlogEntity.class, (t, t1, t2) -> t.eq(t2, Topic::getId, BlogEntity::getId))
                    .innerJoin(BlogEntity.class, (t, t1, t2, t3) -> t.eq(t3, Topic::getId, BlogEntity::getId))
                    .where(o -> o.in(Topic::getId, Arrays.asList("3", "2", "5")))
                    .groupBy((t, t1, t2, t3) -> t3.column(BlogEntity::getId))
                    .orderByAsc((t, t1, t2, t3) -> t3.column(BlogEntity::getOrder)).toSQL();
            Assert.assertEquals("SELECT t3.`id` FROM `t_topic` t LEFT JOIN `t_blog` t1 ON t1.`deleted` = ? AND t.`id` = t1.`id` LEFT JOIN `t_blog` t2 ON t2.`deleted` = ? AND t.`id` = t2.`id` INNER JOIN `t_blog` t3 ON t3.`deleted` = ? AND t.`id` = t3.`id` WHERE t.`id` IN (?,?,?) GROUP BY t3.`id` ORDER BY t3.`order` ASC", sql);
        }
        {
            String sql = easyQuery
                    .queryable(Topic.class)
                    .leftJoin(BlogEntity.class, (t, t1) -> t.eq(t1, Topic::getId, BlogEntity::getId))
                    .innerJoin(BlogEntity.class, (t, t1, t2) -> t.eq(t2, Topic::getId, BlogEntity::getId))
                    .rightJoin(BlogEntity.class, (t, t1, t2, t3) -> t.eq(t3, Topic::getId, BlogEntity::getId))
                    .where(o -> o.in(Topic::getId, Arrays.asList("3", "2", "5")))
                    .groupBy(false, (t, t1, t2, t3) -> t.column(Topic::getStars))
                    .groupBy(true, (t, t1, t2, t3) -> t.column(Topic::getId))
                    .orderByAsc((t, t1, t2, t3) -> t1.column(BlogEntity::getOrder)).toSQL();
            Assert.assertEquals("SELECT t.`id` FROM `t_topic` t LEFT JOIN `t_blog` t1 ON t1.`deleted` = ? AND t.`id` = t1.`id` INNER JOIN `t_blog` t2 ON t2.`deleted` = ? AND t.`id` = t2.`id` RIGHT JOIN `t_blog` t3 ON t3.`deleted` = ? AND t.`id` = t3.`id` WHERE t.`id` IN (?,?,?) GROUP BY t.`id` ORDER BY t1.`order` ASC", sql);
        }
        {
            String sql = easyQuery
                    .queryable(Topic.class)
                    .innerJoin(BlogEntity.class, (t, t1) -> t.eq(t1, Topic::getId, BlogEntity::getId))
                    .innerJoin(BlogEntity.class, (t, t1, t2) -> t.eq(t2, Topic::getId, BlogEntity::getId))
                    .rightJoin(BlogEntity.class, (t, t1, t2, t3) -> t.eq(t3, Topic::getId, BlogEntity::getId))
                    .where(o -> o.in(Topic::getId, Arrays.asList("3", "2", "5")))
                    .groupBy(false, (t, t1, t2, t3) -> t.column(Topic::getStars))
                    .groupBy(true, (t, t1, t2, t3) -> t3.column(BlogEntity::getId))
                    .orderByAsc((t, t1, t2, t3) -> t3.column(BlogEntity::getOrder)).toSQL();
            Assert.assertEquals("SELECT t3.`id` FROM `t_topic` t INNER JOIN `t_blog` t1 ON t1.`deleted` = ? AND t.`id` = t1.`id` INNER JOIN `t_blog` t2 ON t2.`deleted` = ? AND t.`id` = t2.`id` RIGHT JOIN `t_blog` t3 ON t3.`deleted` = ? AND t.`id` = t3.`id` WHERE t.`id` IN (?,?,?) GROUP BY t3.`id` ORDER BY t3.`order` ASC", sql);
        }
    }

    @Test
    public void query123() {
        {
            String sql = easyQuery
                    .queryable(Topic.class)
                    .leftJoin(BlogEntity.class, (t, t1) -> t.eq(t1, Topic::getId, BlogEntity::getId))
                    .innerJoin(BlogEntity.class, (t, t1, t2) -> t.eq(t2, Topic::getId, BlogEntity::getId))
                    .rightJoin(BlogEntity.class, (t, t1, t2, t3) -> t.eq(t3, Topic::getId, BlogEntity::getId))
                    .where(o -> o.in(Topic::getId, Arrays.asList("3", "2", "5")))
                    .groupBy(o -> o.column(Topic::getId))
                    .distinct()
                    .orderByAsc((t, t1, t2, t3) -> t3.column(BlogEntity::getOrder))
                    .limit(10).toSQL();
            Assert.assertEquals("SELECT DISTINCT t.`id` FROM `t_topic` t LEFT JOIN `t_blog` t1 ON t1.`deleted` = ? AND t.`id` = t1.`id` INNER JOIN `t_blog` t2 ON t2.`deleted` = ? AND t.`id` = t2.`id` RIGHT JOIN `t_blog` t3 ON t3.`deleted` = ? AND t.`id` = t3.`id` WHERE t.`id` IN (?,?,?) GROUP BY t.`id` ORDER BY t3.`order` ASC LIMIT 10", sql);
        }
        {
            String sql = easyQuery
                    .queryable(Topic.class)
                    .leftJoin(BlogEntity.class, (t, t1) -> t.eq(t1, Topic::getId, BlogEntity::getId))
                    .innerJoin(BlogEntity.class, (t, t1, t2) -> t.eq(t2, Topic::getId, BlogEntity::getId))
                    .leftJoin(BlogEntity.class, (t, t1, t2, t3) -> t.eq(t3, Topic::getId, BlogEntity::getId))
                    .where(o -> o.in(Topic::getId, Arrays.asList("3", "2", "5")))
                    .groupBy(o -> o.column(Topic::getId))
                    .orderByAsc((t, t1, t2, t3) -> t3.column(BlogEntity::getOrder))
                    .distinct()
                    .select(Topic.class, o -> o.column(Topic::getId))
                    .where(o -> o.eq(Topic::getId, "x"))
                    .select(Long.class, o -> o.columnCount(Topic::getId)).toSQL();
            Assert.assertEquals("SELECT COUNT(t4.`id`) AS `id` FROM (SELECT DISTINCT t.`id` FROM `t_topic` t LEFT JOIN `t_blog` t1 ON t1.`deleted` = ? AND t.`id` = t1.`id` INNER JOIN `t_blog` t2 ON t2.`deleted` = ? AND t.`id` = t2.`id` LEFT JOIN `t_blog` t3 ON t3.`deleted` = ? AND t.`id` = t3.`id` WHERE t.`id` IN (?,?,?) GROUP BY t.`id` ORDER BY t3.`order` ASC) t4 WHERE t4.`id` = ?", sql);
        }
        {
            String sql = easyQuery
                    .queryable(Topic.class)
                    .leftJoin(BlogEntity.class, (t, t1) -> t.eq(t1, Topic::getId, BlogEntity::getId))
                    .leftJoin(BlogEntity.class, (t, t1, t2) -> t.eq(t2, Topic::getId, BlogEntity::getId))
                    .leftJoin(BlogEntity.class, (t, t1, t2, t3) -> t.eq(t3, Topic::getId, BlogEntity::getId))
                    .where(o -> o.in(Topic::getId, Arrays.asList("3", "2", "5")))
                    .groupBy(o -> o.column(Topic::getId))
                    .orderByAsc((t, t1, t2, t3) -> t3.column(BlogEntity::getOrder))
                    .distinct()
                    .select(Topic.class, o -> o.column(Topic::getId))
                    .select("count(1)").toSQL();
            Assert.assertEquals("SELECT count(1) FROM (SELECT DISTINCT t.`id` FROM `t_topic` t LEFT JOIN `t_blog` t1 ON t1.`deleted` = ? AND t.`id` = t1.`id` LEFT JOIN `t_blog` t2 ON t2.`deleted` = ? AND t.`id` = t2.`id` LEFT JOIN `t_blog` t3 ON t3.`deleted` = ? AND t.`id` = t3.`id` WHERE t.`id` IN (?,?,?) GROUP BY t.`id` ORDER BY t3.`order` ASC) t4", sql);
        }
        {
            String sql = easyQuery
                    .queryable(Topic.class)
                    .leftJoin(BlogEntity.class, (t, t1) -> t.eq(t1, Topic::getId, BlogEntity::getId))
                    .leftJoin(BlogEntity.class, (t, t1, t2) -> t.eq(t2, Topic::getId, BlogEntity::getId))
                    .rightJoin(BlogEntity.class, (t, t1, t2, t3) -> t.eq(t3, Topic::getId, BlogEntity::getId))
                    .where(o -> o.in(Topic::getId, Arrays.asList("3", "2", "5")))
                    .groupBy(o -> o.column(Topic::getId))
                    .orderByDesc((t) -> t.column(Topic::getStars))
                    .distinct()
                    .select(Topic.class, o -> o.column(Topic::getId))
                    .select("count(1)").toSQL();
            Assert.assertEquals("SELECT count(1) FROM (SELECT DISTINCT t.`id` FROM `t_topic` t LEFT JOIN `t_blog` t1 ON t1.`deleted` = ? AND t.`id` = t1.`id` LEFT JOIN `t_blog` t2 ON t2.`deleted` = ? AND t.`id` = t2.`id` RIGHT JOIN `t_blog` t3 ON t3.`deleted` = ? AND t.`id` = t3.`id` WHERE t.`id` IN (?,?,?) GROUP BY t.`id` ORDER BY t.`stars` DESC) t4", sql);
        }
        {
            String sql = easyQuery
                    .queryable(Topic.class)
                    .leftJoin(BlogEntity.class, (t, t1) -> t.eq(t1, Topic::getId, BlogEntity::getId))
                    .leftJoin(BlogEntity.class, (t, t1, t2) -> t.eq(t2, Topic::getId, BlogEntity::getId))
                    .rightJoin(BlogEntity.class, (t, t1, t2, t3) -> t.eq(t3, Topic::getId, BlogEntity::getId))
                    .where(o -> o.in(Topic::getId, Arrays.asList("3", "2", "5")))
                    .groupBy(o -> o.column(Topic::getId))
                    .orderByAsc((t) -> t.column(Topic::getStars))
                    .distinct()
                    .select(Topic.class, o -> o.column(Topic::getId))
                    .select("count(1)").toSQL();
            Assert.assertEquals("SELECT count(1) FROM (SELECT DISTINCT t.`id` FROM `t_topic` t LEFT JOIN `t_blog` t1 ON t1.`deleted` = ? AND t.`id` = t1.`id` LEFT JOIN `t_blog` t2 ON t2.`deleted` = ? AND t.`id` = t2.`id` RIGHT JOIN `t_blog` t3 ON t3.`deleted` = ? AND t.`id` = t3.`id` WHERE t.`id` IN (?,?,?) GROUP BY t.`id` ORDER BY t.`stars` ASC) t4", sql);
        }
    }

    @Test
    public void testProxy1() {

        List<Topic> list1 = easyProxyQuery
                .queryable(TOPIC_PROXY)
                .where((filter, t) -> filter.eq(t.id, "123").like(t.title, "xxx"))
                .where((filter, t) -> filter.eq(t.id, "123").like(t.title, "xxx"))
                .select((selector, t) -> selector.columns(t.id, t.title))
                .toList();


        EasyPageResult<Topic> topicPageResult = easyProxyQuery
                .queryable(TOPIC_PROXY)
                .where((filter, o) -> filter.isNotNull(o.id))
                .toPageResult(3, 10);
        List<Topic> data = topicPageResult.getData();
        Assert.assertEquals(10, data.size());
    }
    @Test
    public void testProxy2() {

        List<Topic> list1 = easyProxyQuery
                .queryable(TOPIC_TEST_PROXY)
                .where((filter, t) -> filter.eq(t.id(), "123").like(t.title(), "xxx"))
                .where((filter, t) -> filter.eq(t.id(), "123").like(t.title(), "xxx"))
                .select((selector, t) -> selector.columns(t.id(), t.title()))
                .toList();
        TopicAuto topicAuto = easyProxyQuery.queryable(TopicAutoProxy.DEFAULT)
                .where((filter, t) -> filter.eq(t.title(), "123"))
                .firstOrNull();
        List<BlogEntityTest> list = easyProxyQuery.queryable(BlogEntityProxy.DEFAULT)
                .leftJoin(TopicAutoProxy.DEFAULT, (filter, t, t1) -> filter.eq(t.id(), t1.id()))
                .where((filter, t, t1) -> filter.eq(t1.title(), "123").like(t.id(), "22"))
                .select(BlogEntityTestProxy.DEFAULT, (selector, t, t1) ->
                        selector.columns(t.id(), t1.title())
                        .columnAs(t.content(), r -> r.content())
                        .columnAs(t.isTop(), r -> r.isTop())
                ).toList();

        String sql = easyProxyQuery.queryable(BlogEntityProxy.DEFAULT)
                .leftJoin(TopicAutoProxy.DEFAULT, (filter, t, t1) -> filter.eq(t.id(), t1.id()))
                .where((filter, t, t1) -> filter.eq(t1.title(), "123").like(t.id(), "22"))
                .select(BlogEntityTestProxy.DEFAULT, (selector, t, t1) -> selector.columns(t.id(), t1.title()).columnAs(t.content(), r -> r.content())
                        .columnAs(t.isTop(), r -> r.isTop())).toSQL();
        Assert.assertEquals("SELECT t.`id`,t1.`title`,t.`content` AS `content`,t.`is_top` AS `is_top` FROM `t_blog` t LEFT JOIN `t_topic_auto` t1 ON t.`id` = t1.`id` WHERE t.`deleted` = ? AND t1.`title` = ? AND t.`id` LIKE ?",sql);

        String sql1 = easyProxyQuery.queryable(BlogEntityProxy.DEFAULT)
                .leftJoin(TopicAutoProxy.DEFAULT, (filter, t, t1) -> filter.eq(t.id(), t1.id()))
                .where((filter, t, t1) -> filter.eq(t1.title(), "123").like(t.id(), "22"))
                .select(BlogEntityTestProxy.DEFAULT, (selector, t, t1) -> selector.columnAll(t).columns(t.id(), t1.title()).columnAs(t.content(), r -> r.content())
                        .columnAs(t.isTop(), r -> r.isTop())).toSQL();
        Assert.assertEquals("SELECT t.`title`,t.`content`,t.`url`,t.`star`,t.`publish_time`,t.`score`,t.`status`,t.`order`,t.`is_top`,t.`top`,t.`id`,t1.`title`,t.`content` AS `content`,t.`is_top` AS `is_top` FROM `t_blog` t LEFT JOIN `t_topic_auto` t1 ON t.`id` = t1.`id` WHERE t.`deleted` = ? AND t1.`title` = ? AND t.`id` LIKE ?",sql1);
//        List<TopicAuto> topicAutos = easyQuery.queryable(TopicAuto.class).where(o->o.lt(TopicAuto::getStars,999)).toList();
    }
    @Test
    public void testProxy3(){
        Topic topic = easyProxyQuery.queryable(TopicProxy.DEFAULT)
                .where((filter, t) -> filter.eq(t.id(), "3").or().like(t.title(), "你好"))
                .firstOrNull();
        Assert.assertNotNull(topic);
    }
    @Test
    public void testLambda3(){
        Topic topic = easyQuery.queryable(Topic.class)
                .where(t -> t.eq(Topic::getId,"3").or().like(Topic::getTitle,"你好"))
                .firstOrNull();
        Assert.assertNotNull(topic);
    }
    @Test
    public void testProperty3(){
        Topic topic =  easyQueryClient.queryable(Topic.class)
                .where(t->t.eq("id","3").or().like("title","你好"))
                .firstOrNull();
        Assert.assertNotNull(topic);
    }

    @Test
    public void testSelfPredicate1(){
        {
            String sql = easyQueryClient.queryable(BlogEntity.class)
                    .where(t -> t.eq("score", "3").eq(t, "score", "order"))
                    .toSQL();
            Assert.assertEquals("SELECT `id`,`create_time`,`update_time`,`create_by`,`update_by`,`deleted`,`title`,`content`,`url`,`star`,`publish_time`,`score`,`status`,`order`,`is_top`,`top` FROM `t_blog` WHERE `deleted` = ? AND `score` = ? AND `score` = `order`",sql);
        }
        {
            String sql = easyQueryClient.queryable(BlogEntity.class)
                    .where(t -> t.eq("score", "3").gt(t, "score", "order"))
                    .toSQL();
            Assert.assertEquals("SELECT `id`,`create_time`,`update_time`,`create_by`,`update_by`,`deleted`,`title`,`content`,`url`,`star`,`publish_time`,`score`,`status`,`order`,`is_top`,`top` FROM `t_blog` WHERE `deleted` = ? AND `score` = ? AND `score` > `order`",sql);
        }
        {

            String sql1 = easyQuery.queryable(BlogEntity.class)
                    .where(t -> t.eq(BlogEntity::getScore, "3").eq(t, BlogEntity::getScore, BlogEntity::getOrder))
                    .toSQL();
            Assert.assertEquals("SELECT `id`,`create_time`,`update_time`,`create_by`,`update_by`,`deleted`,`title`,`content`,`url`,`star`,`publish_time`,`score`,`status`,`order`,`is_top`,`top` FROM `t_blog` WHERE `deleted` = ? AND `score` = ? AND `score` = `order`",sql1);
        }
        {

            String sql1 = easyQuery.queryable(BlogEntity.class)
                    .where(t -> t.eq(BlogEntity::getScore, "3").le(t, BlogEntity::getScore, BlogEntity::getOrder))
                    .toSQL();
            Assert.assertEquals("SELECT `id`,`create_time`,`update_time`,`create_by`,`update_by`,`deleted`,`title`,`content`,`url`,`star`,`publish_time`,`score`,`status`,`order`,`is_top`,`top` FROM `t_blog` WHERE `deleted` = ? AND `score` = ? AND `score` <= `order`",sql1);
        }
        {
            String sql2 = easyProxyQuery.queryable(BlogEntityProxy.DEFAULT)
                    .where((filter,t) -> filter.eq(t.score(), "3").eq(t.score(), t.order()))
                    .toSQL();
            Assert.assertEquals("SELECT `id`,`create_time`,`update_time`,`create_by`,`update_by`,`deleted`,`title`,`content`,`url`,`star`,`publish_time`,`score`,`status`,`order`,`is_top`,`top` FROM `t_blog` WHERE `deleted` = ? AND `score` = ? AND `score` = `order`",sql2);
        } {
            String sql2 = easyProxyQuery.queryable(BlogEntityProxy.DEFAULT)
                    .where((filter,t) -> filter.eq(t.score(), "3").ge(t.score(), t.order()))
                    .toSQL();
            Assert.assertEquals("SELECT `id`,`create_time`,`update_time`,`create_by`,`update_by`,`deleted`,`title`,`content`,`url`,`star`,`publish_time`,`score`,`status`,`order`,`is_top`,`top` FROM `t_blog` WHERE `deleted` = ? AND `score` = ? AND `score` >= `order`",sql2);
        }
    }
}
