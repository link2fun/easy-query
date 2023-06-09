package com.easy.query.test;

import com.easy.query.core.exception.EasyQueryInvalidOperationException;
import com.easy.query.core.exception.EasyQuerySQLCommandException;
import com.easy.query.core.exception.EasyQuerySQLStatementException;
import com.easy.query.test.entity.Topic;
import com.easy.query.test.entity.TopicValueUpdateAtomicTrack;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * @FileName: DeleteTest.java
 * @Description: 文件说明
 * @Date: 2023/3/21 12:54
 * @author xuejiaming
 */
public class DeleteTest  extends BaseTest {
    @Test
    public void deleteTest1(){
        Topic topic = easyQuery.queryable(Topic.class).whereById("999").firstOrNull();
        if(topic==null){
            topic=new Topic();
            topic.setId("999");
            topic.setTitle("title999");
            topic.setCreateTime(LocalDateTime.now());
            topic.setStars(999);
            long l = easyQuery.insertable(topic).executeRows();
            Assert.assertEquals(1,l);
        }
        String deleteSQL = easyQuery.deletable(Topic.class).whereById("999").toSQL();
        Assert.assertEquals("DELETE FROM `t_topic` WHERE `id` = ?",deleteSQL);
        long l = easyQuery.deletable(Topic.class).whereById("999").executeRows();
        Assert.assertEquals(1,l);
    }
    @Test
    public void deleteTest2(){
        Topic topic = easyQuery.queryable(Topic.class).whereById("998").firstOrNull();
        if(topic==null){
            topic=new Topic();
            topic.setId("998");
            topic.setTitle("title998");
            topic.setCreateTime(LocalDateTime.now());
            topic.setStars(998);
            long l = easyQuery.insertable(topic).executeRows();
            Assert.assertEquals(1,l);
        }
        String deleteSql = easyQuery.deletable(Topic.class).where(o->o.eq(Topic::getTitle,"title998")).toSQL();
        Assert.assertEquals("DELETE FROM `t_topic` WHERE `title` = ?",deleteSql);
        long l = easyQuery.deletable(Topic.class).where(o->o.eq(Topic::getTitle,"title998")).executeRows();
        Assert.assertEquals(1,l);
    }
    @Test
    public void deleteTest3(){
        Topic topic = easyQuery.queryable(Topic.class).whereById("997").firstOrNull();
        if(topic==null){
            topic=new Topic();
            topic.setId("997");
            topic.setTitle("title997");
            topic.setCreateTime(LocalDateTime.now());
            topic.setStars(997);
            long l = easyQuery.insertable(topic).executeRows();
            Assert.assertEquals(1,l);
        }
        String deleteSql = easyQuery.deletable(topic).toSQL();
        Assert.assertEquals("DELETE FROM `t_topic` WHERE `id` = ?",deleteSql);
        long l = easyQuery.deletable(topic).executeRows();
        Assert.assertEquals(1,l);
    }
    @Test
    public void deleteTest4(){
        Topic topic = easyQuery.queryable(Topic.class).whereById("996").firstOrNull();
        if(topic==null){
            topic=new Topic();
            topic.setId("996");
            topic.setTitle("title996");
            topic.setCreateTime(LocalDateTime.now());
            topic.setStars(996);
            long l = easyQuery.insertable(topic).executeRows();
            Assert.assertEquals(1,l);
        }
        String deleteSql = easyQuery.deletable(topic).toSQL();
        Assert.assertEquals("DELETE FROM `t_topic` WHERE `id` = ?",deleteSql);
        long l = easyQuery.deletable(topic).executeRows();
        Assert.assertEquals(1,l);
    }
    @Test
    public void deleteTest6(){
        Topic topic = easyQuery.queryable(Topic.class).whereById("995").firstOrNull();
        if(topic==null){
            topic=new Topic();
            topic.setId("995");
            topic.setTitle("title995");
            topic.setCreateTime(LocalDateTime.now());
            topic.setStars(995);
            long l = easyQuery.insertable(topic).executeRows();
            Assert.assertEquals(1, l);
        }
        try {
            long l = easyQuery.deletable(Topic.class).whereById("999").allowDeleteStatement(false).executeRows();
        } catch (Exception e) {
            Assert.assertEquals(EasyQueryInvalidOperationException.class, e.getClass());
        }

    }

    @Test
    public void deleteTest7() {

        try {
            long l = easyQuery.deletable(TopicValueUpdateAtomicTrack.class).whereByIds(Arrays.asList("1", "2")).executeRows();
        } catch (Exception ex) {
            Assert.assertTrue(ex instanceof EasyQuerySQLCommandException);
            EasyQuerySQLCommandException ex1 = (EasyQuerySQLCommandException) ex;
            Assert.assertTrue(ex1.getCause() instanceof EasyQuerySQLStatementException);
            String sql = ((EasyQuerySQLStatementException) ex1.getCause()).getSQL();
            Assert.assertEquals("DELETE FROM `t_topic_value_atomic` WHERE `id` IN (?,?)", sql);
        }
    }

    @Test
    public void deleteTest8() {

        try {
            long l = easyQuery.deletable(TopicValueUpdateAtomicTrack.class).whereByIds(Arrays.asList("1", "2")).executeRows();
        } catch (Exception ex) {
            Assert.assertTrue(ex instanceof EasyQuerySQLCommandException);
            EasyQuerySQLCommandException ex1 = (EasyQuerySQLCommandException) ex;
            Assert.assertTrue(ex1.getCause() instanceof EasyQuerySQLStatementException);
            String sql = ((EasyQuerySQLStatementException) ex1.getCause()).getSQL();
            Assert.assertEquals("DELETE FROM `t_topic_value_atomic` WHERE `id` IN (?,?)", sql);
        }
    }

    @Test
    public void deleteTest9() {

        try {
            long l = easyQuery.deletable(TopicValueUpdateAtomicTrack.class).whereByIds(true, Arrays.asList("1", "2")).executeRows();
        } catch (Exception ex) {
            Assert.assertTrue(ex instanceof EasyQuerySQLCommandException);
            EasyQuerySQLCommandException ex1 = (EasyQuerySQLCommandException) ex;
            Assert.assertTrue(ex1.getCause() instanceof EasyQuerySQLStatementException);
            String sql = ((EasyQuerySQLStatementException) ex1.getCause()).getSQL();
            Assert.assertEquals("DELETE FROM `t_topic_value_atomic` WHERE `id` IN (?,?)", sql);
        }
    }

    @Test
    public void deleteTest10() {

        try {
            long l = easyQuery.deletable(TopicValueUpdateAtomicTrack.class).whereByIds(true, Arrays.asList("1", "2")).executeRows();
        } catch (Exception ex) {
            Assert.assertTrue(ex instanceof EasyQuerySQLCommandException);
            EasyQuerySQLCommandException ex1 = (EasyQuerySQLCommandException) ex;
            Assert.assertTrue(ex1.getCause() instanceof EasyQuerySQLStatementException);
            String sql = ((EasyQuerySQLStatementException) ex1.getCause()).getSQL();
            Assert.assertEquals("DELETE FROM `t_topic_value_atomic` WHERE `id` IN (?,?)", sql);
        }
    }

    @Test
    public void deleteTest11() {
        try {
            long l = easyQuery.deletable(TopicValueUpdateAtomicTrack.class)
                    .asSchema("abc")
                    .asTable("aaa")
                    .noInterceptor().whereByIds(false, Arrays.asList("1", "2")).whereByIds(true, Arrays.asList("1", "2", "3")).executeRows();
        } catch (Exception ex) {
            Assert.assertTrue(ex instanceof EasyQuerySQLCommandException);
            EasyQuerySQLCommandException ex1 = (EasyQuerySQLCommandException) ex;
            Assert.assertTrue(ex1.getCause() instanceof EasyQuerySQLStatementException);
            String sql = ((EasyQuerySQLStatementException) ex1.getCause()).getSQL();
            Assert.assertEquals("DELETE FROM `abc`.`aaa` WHERE `id` IN (?,?,?)", sql);
        }
    }

    @Test
    public void deleteTest12() {
        try {
            long l = easyQuery.deletable(TopicValueUpdateAtomicTrack.class)
                    .asSchema(o -> "abc")
                    .asTable(o -> o + "aaa")
                    .noInterceptor().whereByIds(false, Arrays.asList("1", "2")).whereByIds(true, Arrays.asList("1", "2", "3")).executeRows();
        } catch (Exception ex) {
            Assert.assertTrue(ex instanceof EasyQuerySQLCommandException);
            EasyQuerySQLCommandException ex1 = (EasyQuerySQLCommandException) ex;
            Assert.assertTrue(ex1.getCause() instanceof EasyQuerySQLStatementException);
            String sql = ((EasyQuerySQLStatementException) ex1.getCause()).getSQL();
            Assert.assertEquals("DELETE FROM `abc`.`t_topic_value_atomicaaa` WHERE `id` IN (?,?,?)", sql);
        }
    }
}
