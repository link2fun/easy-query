package com.easy.query.test;

import com.easy.query.core.basic.extension.listener.JdbcExecuteAfterArg;
import com.easy.query.core.util.EasySQLUtil;
import com.easy.query.test.entity.testrelation.TestJoinEntity;
import com.easy.query.test.entity.testrelation.TestRoleEntity;
import com.easy.query.test.entity.testrelation.TestRouteEntity;
import com.easy.query.test.entity.testrelation.TestUserEntity;
import com.easy.query.test.entity.testrelation.vo.TestRoleDTO1;
import com.easy.query.test.entity.testrelation.vo.TestRouteDTO1;
import com.easy.query.test.entity.testrelation.vo.TestUserDTO;
import com.easy.query.test.entity.testrelation.vo.TestUserDTO1;
import com.easy.query.test.entity.testrelation.vo.proxy.TestRoleDTO1Proxy;
import com.easy.query.test.entity.testrelation.vo.proxy.TestUserDTO1Proxy;
import com.easy.query.test.listener.ListenerContext;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * create time 2024/3/8 11:08
 * 文件说明
 *
 * @author xuejiaming
 */
public class QueryTest14 extends BaseTest{

    @Test
    public void test1(){

        easyEntityQuery.deletable(TestUserEntity.class)
                .allowDeleteStatement(true)
                .whereByIds(Arrays.asList("u1","u2","u3"))
                .executeRows();
        easyEntityQuery.deletable(TestRoleEntity.class)
                .allowDeleteStatement(true)
                .whereByIds(Arrays.asList("r1","r2","r3","r4"))
                .executeRows();
        easyEntityQuery.deletable(TestRouteEntity.class)
                .allowDeleteStatement(true)
                .whereByIds(Arrays.asList("ru1","ru2","ru3"))
                .executeRows();
        easyEntityQuery.deletable(TestJoinEntity.class)
                .allowDeleteStatement(true)
                .where(t -> t.expression().sql("1=1"))
                .executeRows();
        ArrayList<TestUserEntity> testUserEntities = new ArrayList<>();
        {
            TestUserEntity testUserEntity = new TestUserEntity();
            testUserEntity.setId("u1");
            testUserEntity.setName("uname1");
            testUserEntity.setPassword("upwd1");
            testUserEntities.add(testUserEntity);
        }
        {
            TestUserEntity testUserEntity = new TestUserEntity();
            testUserEntity.setId("u2");
            testUserEntity.setName("uname2");
            testUserEntity.setPassword("upwd2");
            testUserEntities.add(testUserEntity);
        }
        {
            TestUserEntity testUserEntity = new TestUserEntity();
            testUserEntity.setId("u3");
            testUserEntity.setName("uname3");
            testUserEntity.setPassword("upwd3");
            testUserEntities.add(testUserEntity);
        }
        ArrayList<TestRoleEntity> testRoleEntities = new ArrayList<>();
        {
            TestRoleEntity testRoleEntity = new TestRoleEntity();
            testRoleEntity.setId("r1");
            testRoleEntity.setName("rname1");
            testRoleEntity.setRemark("rremark1");
            testRoleEntities.add(testRoleEntity);
        }
        {
            TestRoleEntity testRoleEntity = new TestRoleEntity();
            testRoleEntity.setId("r2");
            testRoleEntity.setName("rname2");
            testRoleEntity.setRemark("rremark2");
            testRoleEntities.add(testRoleEntity);
        }
        {
            TestRoleEntity testRoleEntity = new TestRoleEntity();
            testRoleEntity.setId("r3");
            testRoleEntity.setName("rname3");
            testRoleEntity.setRemark("rremark3");
            testRoleEntities.add(testRoleEntity);
        }
        {
            TestRoleEntity testRoleEntity = new TestRoleEntity();
            testRoleEntity.setId("r4");
            testRoleEntity.setName("rname4");
            testRoleEntity.setRemark("rremark4");
            testRoleEntities.add(testRoleEntity);
        }
        ArrayList<TestRouteEntity> testRouteEntities = new ArrayList<>();
        {
            TestRouteEntity testRouteEntity = new TestRouteEntity();
            testRouteEntity.setId("ru1");
            testRouteEntity.setName("runame1");
            testRouteEntity.setRequestPath("/api/1");
            testRouteEntities.add(testRouteEntity);
        }
        {
            TestRouteEntity testRouteEntity = new TestRouteEntity();
            testRouteEntity.setId("ru2");
            testRouteEntity.setName("runame2");
            testRouteEntity.setRequestPath("/api/2");
            testRouteEntities.add(testRouteEntity);
        }
        {
            TestRouteEntity testRouteEntity = new TestRouteEntity();
            testRouteEntity.setId("ru3");
            testRouteEntity.setName("runame3");
            testRouteEntity.setRequestPath("/api/3");
            testRouteEntities.add(testRouteEntity);
        }


//        easyEntityQuery.deletable(TestUserEntity.class)
//                .allowDeleteStatement(true)
//                .whereByIds(Arrays.asList("u1","u2","u3"))
//                .executeRows();
//        easyEntityQuery.deletable(TestRoleEntity.class)
//                .allowDeleteStatement(true)
//                .whereByIds(Arrays.asList("r1","r2","r3","r4"))
//                .executeRows();
//        easyEntityQuery.deletable(TestRouteEntity.class)
//                .allowDeleteStatement(true)
//                .whereByIds(Arrays.asList("ru1","ru2","ru3"))
//                .executeRows();
//        easyEntityQuery.deletable(TestJoinEntity.class)
//                .allowDeleteStatement(true)
//                .where(t -> t.expression().sql("1=1"))
//                .executeRows();

        ArrayList<TestJoinEntity> testJoinEntities = new ArrayList<>();
        {
            TestJoinEntity testJoinEntity = new TestJoinEntity();
            testJoinEntity.setFirstId("u1");
            testJoinEntity.setSecondId("r1");
            testJoinEntity.setType(1);
            testJoinEntities.add(testJoinEntity);
        }
        {
            TestJoinEntity testJoinEntity = new TestJoinEntity();
            testJoinEntity.setFirstId("u2");
            testJoinEntity.setSecondId("r2");
            testJoinEntity.setType(1);
            testJoinEntities.add(testJoinEntity);
        }
        {
            TestJoinEntity testJoinEntity = new TestJoinEntity();
            testJoinEntity.setFirstId("u3");
            testJoinEntity.setSecondId("r3");
            testJoinEntity.setType(1);
            testJoinEntities.add(testJoinEntity);
        }
        {
            TestJoinEntity testJoinEntity = new TestJoinEntity();
            testJoinEntity.setFirstId("u1");
            testJoinEntity.setSecondId("r4");
            testJoinEntity.setType(1);
            testJoinEntities.add(testJoinEntity);
        }
        {
            TestJoinEntity testJoinEntity = new TestJoinEntity();
            testJoinEntity.setFirstId("u2");
            testJoinEntity.setSecondId("r4");
            testJoinEntity.setType(1);
            testJoinEntities.add(testJoinEntity);
        }
        //角色和路由
        {
            TestJoinEntity testJoinEntity = new TestJoinEntity();
            testJoinEntity.setFirstId("r1");
            testJoinEntity.setSecondId("ru1");
            testJoinEntity.setType(2);
            testJoinEntities.add(testJoinEntity);
        }
        {
            TestJoinEntity testJoinEntity = new TestJoinEntity();
            testJoinEntity.setFirstId("r1");
            testJoinEntity.setSecondId("ru2");
            testJoinEntity.setType(2);
            testJoinEntities.add(testJoinEntity);
        }
        {
            TestJoinEntity testJoinEntity = new TestJoinEntity();
            testJoinEntity.setFirstId("r2");
            testJoinEntity.setSecondId("ru2");
            testJoinEntity.setType(2);
            testJoinEntities.add(testJoinEntity);
        }
        {
            TestJoinEntity testJoinEntity = new TestJoinEntity();
            testJoinEntity.setFirstId("r3");
            testJoinEntity.setSecondId("ru3");
            testJoinEntity.setType(2);
            testJoinEntities.add(testJoinEntity);
        }

        easyEntityQuery.insertable(testUserEntities).executeRows();
        easyEntityQuery.insertable(testRoleEntities).executeRows();
        easyEntityQuery.insertable(testRouteEntities).executeRows();
        easyEntityQuery.insertable(testJoinEntities).executeRows();

        {


            ListenerContext listenerContext = new ListenerContext(true);
            listenerContextManager.startListen(listenerContext);

            List<TestUserDTO> list = easyEntityQuery.queryable(TestUserEntity.class)
                    .selectAutoInclude(TestUserDTO.class)
                    .toList();
            Assert.assertNotNull(listenerContext.getJdbcExecuteAfterArgs());
            Assert.assertEquals(5,listenerContext.getJdbcExecuteAfterArgs().size());
            {
                JdbcExecuteAfterArg jdbcExecuteAfterArg = listenerContext.getJdbcExecuteAfterArgs().get(0);
                Assert.assertEquals("SELECT t.`id`,t.`name`,t.`password` FROM `t_test_user` t", jdbcExecuteAfterArg.getBeforeArg().getSql());
//                    Assert.assertEquals("class1(String),class2(String),class3(String)", EasySQLUtil.sqlParameterToString(jdbcExecuteAfterArg.getBeforeArg().getSqlParameters().get(0)));
            }
            {
                JdbcExecuteAfterArg jdbcExecuteAfterArg = listenerContext.getJdbcExecuteAfterArgs().get(1);
                Assert.assertEquals("SELECT `first_id`,`second_id` FROM `t_test_join` WHERE `first_id` IN (?,?,?) AND `type` = ?", jdbcExecuteAfterArg.getBeforeArg().getSql());
                Assert.assertEquals("u1(String),u2(String),u3(String),1(Integer)", EasySQLUtil.sqlParameterToString(jdbcExecuteAfterArg.getBeforeArg().getSqlParameters().get(0)));
            }
            {
                JdbcExecuteAfterArg jdbcExecuteAfterArg = listenerContext.getJdbcExecuteAfterArgs().get(2);
                Assert.assertEquals("SELECT t.`id`,t.`name`,t.`remark` FROM `t_test_role` t WHERE t.`id` IN (?,?,?,?)", jdbcExecuteAfterArg.getBeforeArg().getSql());
                Assert.assertEquals("r1(String),r4(String),r2(String),r3(String)", EasySQLUtil.sqlParameterToString(jdbcExecuteAfterArg.getBeforeArg().getSqlParameters().get(0)));
            }
            {
                JdbcExecuteAfterArg jdbcExecuteAfterArg = listenerContext.getJdbcExecuteAfterArgs().get(3);
                Assert.assertEquals("SELECT `first_id`,`second_id` FROM `t_test_join` WHERE `first_id` IN (?,?,?,?) AND `type` = ?", jdbcExecuteAfterArg.getBeforeArg().getSql());
                Assert.assertEquals("r1(String),r2(String),r3(String),r4(String),2(Integer)", EasySQLUtil.sqlParameterToString(jdbcExecuteAfterArg.getBeforeArg().getSqlParameters().get(0)));
            }
            {
                JdbcExecuteAfterArg jdbcExecuteAfterArg = listenerContext.getJdbcExecuteAfterArgs().get(4);
                Assert.assertEquals("SELECT t.`id`,t.`name`,t.`request_path` FROM `t_test_route` t WHERE t.`id` IN (?,?,?)", jdbcExecuteAfterArg.getBeforeArg().getSql());
                Assert.assertEquals("ru1(String),ru2(String),ru3(String)", EasySQLUtil.sqlParameterToString(jdbcExecuteAfterArg.getBeforeArg().getSqlParameters().get(0)));
            }

            {

                TestUserDTO u1 = list.stream().filter(o -> Objects.equals("u1", o.getId())).findFirst().orElse(null);
                Assert.assertNotNull(u1);
                List<TestUserDTO.TestRoleDTO> roles = u1.getRoles();
                Assert.assertNotNull(roles);
                Assert.assertEquals(2,roles.size());
                TestUserDTO.TestRoleDTO testRoleDTO0 = roles.get(0);
                Assert.assertEquals("r1",testRoleDTO0.getId());
                TestUserDTO.TestRoleDTO testRoleDTO1 = roles.get(1);
                Assert.assertEquals("r4",testRoleDTO1.getId());
                Assert.assertNotNull(testRoleDTO0.getRoutes());
                Assert.assertEquals(2,testRoleDTO0.getRoutes().size());
                TestUserDTO.TestRouteDTO testRouteDTO0 = testRoleDTO0.getRoutes().get(0);
                TestUserDTO.TestRouteDTO testRouteDTO1 = testRoleDTO0.getRoutes().get(1);
                Assert.assertEquals("ru1",testRouteDTO0.getId());
                Assert.assertEquals("ru2",testRouteDTO1.getId());
            }
            {

                TestUserDTO u2 = list.stream().filter(o -> Objects.equals("u2", o.getId())).findFirst().orElse(null);
                Assert.assertNotNull(u2);
                List<TestUserDTO.TestRoleDTO> roles = u2.getRoles();
                Assert.assertNotNull(roles);
                Assert.assertEquals(2,roles.size());
                TestUserDTO.TestRoleDTO testRoleDTO0 = roles.get(0);
                Assert.assertEquals("r2",testRoleDTO0.getId());
                TestUserDTO.TestRoleDTO testRoleDTO1 = roles.get(1);
                Assert.assertEquals("r4",testRoleDTO1.getId());
                Assert.assertNotNull(testRoleDTO0.getRoutes());
                Assert.assertEquals(1,testRoleDTO0.getRoutes().size());
                TestUserDTO.TestRouteDTO testRouteDTO0 = testRoleDTO0.getRoutes().get(0);
                Assert.assertEquals("ru2",testRouteDTO0.getId());
            }
            {

                TestUserDTO u2 = list.stream().filter(o -> Objects.equals("u3", o.getId())).findFirst().orElse(null);
                Assert.assertNotNull(u2);
                List<TestUserDTO.TestRoleDTO> roles = u2.getRoles();
                Assert.assertNotNull(roles);
                Assert.assertEquals(1,roles.size());
                TestUserDTO.TestRoleDTO testRoleDTO0 = roles.get(0);
                Assert.assertEquals("r3",testRoleDTO0.getId());
                Assert.assertNotNull(testRoleDTO0.getRoutes());
                Assert.assertEquals(1,testRoleDTO0.getRoutes().size());
                TestUserDTO.TestRouteDTO testRouteDTO0 = testRoleDTO0.getRoutes().get(0);
                Assert.assertEquals("ru3",testRouteDTO0.getId());
            }
        }
        {


            ListenerContext listenerContext = new ListenerContext(true);
            listenerContextManager.startListen(listenerContext);

            List<TestUserDTO1> list = easyEntityQuery.queryable(TestUserEntity.class)
                    .includes(t->t.roles(),r->r.includes(x->x.routes()))
                    .select(t -> {
                        TestUserDTO1Proxy result = new TestUserDTO1Proxy();
                        result.selectAll(t);

                        result.roles().set(t.roles(), role -> {
                            TestRoleDTO1Proxy r = new TestRoleDTO1Proxy();
                            r.selectAll(role);
                            r.routes().set(role.routes());
                            return r;
                        });
                        return result;
                    })
                    .toList();


            Assert.assertNotNull(listenerContext.getJdbcExecuteAfterArgs());
            Assert.assertEquals(5,listenerContext.getJdbcExecuteAfterArgs().size());
            {
                JdbcExecuteAfterArg jdbcExecuteAfterArg = listenerContext.getJdbcExecuteAfterArgs().get(0);
                Assert.assertEquals("SELECT t.`id`,t.`name`,t.`password` FROM `t_test_user` t", jdbcExecuteAfterArg.getBeforeArg().getSql());
//                    Assert.assertEquals("class1(String),class2(String),class3(String)", EasySQLUtil.sqlParameterToString(jdbcExecuteAfterArg.getBeforeArg().getSqlParameters().get(0)));
            }
            {
                JdbcExecuteAfterArg jdbcExecuteAfterArg = listenerContext.getJdbcExecuteAfterArgs().get(1);
                Assert.assertEquals("SELECT `first_id`,`second_id` FROM `t_test_join` WHERE `first_id` IN (?,?,?) AND `type` = ?", jdbcExecuteAfterArg.getBeforeArg().getSql());
                Assert.assertEquals("u1(String),u2(String),u3(String),1(Integer)", EasySQLUtil.sqlParameterToString(jdbcExecuteAfterArg.getBeforeArg().getSqlParameters().get(0)));
            }
            {
                JdbcExecuteAfterArg jdbcExecuteAfterArg = listenerContext.getJdbcExecuteAfterArgs().get(2);
                Assert.assertEquals("SELECT t.`id`,t.`name`,t.`remark` FROM `t_test_role` t WHERE t.`id` IN (?,?,?,?)", jdbcExecuteAfterArg.getBeforeArg().getSql());
                Assert.assertEquals("r1(String),r4(String),r2(String),r3(String)", EasySQLUtil.sqlParameterToString(jdbcExecuteAfterArg.getBeforeArg().getSqlParameters().get(0)));
            }
            {
                JdbcExecuteAfterArg jdbcExecuteAfterArg = listenerContext.getJdbcExecuteAfterArgs().get(3);
                Assert.assertEquals("SELECT `first_id`,`second_id` FROM `t_test_join` WHERE `first_id` IN (?,?,?,?) AND `type` = ?", jdbcExecuteAfterArg.getBeforeArg().getSql());
                Assert.assertEquals("r1(String),r2(String),r3(String),r4(String),2(Integer)", EasySQLUtil.sqlParameterToString(jdbcExecuteAfterArg.getBeforeArg().getSqlParameters().get(0)));
            }
            {
                JdbcExecuteAfterArg jdbcExecuteAfterArg = listenerContext.getJdbcExecuteAfterArgs().get(4);
                Assert.assertEquals("SELECT t.`id`,t.`name`,t.`request_path` FROM `t_test_route` t WHERE t.`id` IN (?,?,?)", jdbcExecuteAfterArg.getBeforeArg().getSql());
                Assert.assertEquals("ru1(String),ru2(String),ru3(String)", EasySQLUtil.sqlParameterToString(jdbcExecuteAfterArg.getBeforeArg().getSqlParameters().get(0)));
            }

            {

                TestUserDTO1 u1 = list.stream().filter(o -> Objects.equals("u1", o.getId())).findFirst().orElse(null);
                Assert.assertNotNull(u1);
                List<TestRoleDTO1> roles = u1.getRoles();
                Assert.assertNotNull(roles);
                Assert.assertEquals(2,roles.size());
                TestRoleDTO1 testRoleDTO0 = roles.get(0);
                Assert.assertEquals("r1",testRoleDTO0.getId());
                TestRoleDTO1 testRoleDTO1 = roles.get(1);
                Assert.assertEquals("r4",testRoleDTO1.getId());
                Assert.assertNotNull(testRoleDTO0.getRoutes());
                Assert.assertEquals(2,testRoleDTO0.getRoutes().size());
                TestRouteDTO1 testRouteDTO0 = testRoleDTO0.getRoutes().get(0);
                TestRouteDTO1 testRouteDTO1 = testRoleDTO0.getRoutes().get(1);
                Assert.assertEquals("ru1",testRouteDTO0.getId());
                Assert.assertEquals("ru2",testRouteDTO1.getId());
            }
            {

                TestUserDTO1 u2 = list.stream().filter(o -> Objects.equals("u2", o.getId())).findFirst().orElse(null);
                Assert.assertNotNull(u2);
                List<TestRoleDTO1> roles = u2.getRoles();
                Assert.assertNotNull(roles);
                Assert.assertEquals(2,roles.size());
                TestRoleDTO1 testRoleDTO0 = roles.get(0);
                Assert.assertEquals("r2",testRoleDTO0.getId());
                TestRoleDTO1 testRoleDTO1 = roles.get(1);
                Assert.assertEquals("r4",testRoleDTO1.getId());
                Assert.assertNotNull(testRoleDTO0.getRoutes());
                Assert.assertEquals(1,testRoleDTO0.getRoutes().size());
                TestRouteDTO1 testRouteDTO0 = testRoleDTO0.getRoutes().get(0);
                Assert.assertEquals("ru2",testRouteDTO0.getId());
            }
            {

                TestUserDTO1 u2 = list.stream().filter(o -> Objects.equals("u3", o.getId())).findFirst().orElse(null);
                Assert.assertNotNull(u2);
                List<TestRoleDTO1> roles = u2.getRoles();
                Assert.assertNotNull(roles);
                Assert.assertEquals(1,roles.size());
                TestRoleDTO1 testRoleDTO0 = roles.get(0);
                Assert.assertEquals("r3",testRoleDTO0.getId());
                Assert.assertNotNull(testRoleDTO0.getRoutes());
                Assert.assertEquals(1,testRoleDTO0.getRoutes().size());
                TestRouteDTO1 testRouteDTO0 = testRoleDTO0.getRoutes().get(0);
                Assert.assertEquals("ru3",testRouteDTO0.getId());
            }
        }
//        {
//
//            List<TestUserDTO2> list = easyEntityQuery.queryable(TestUserEntity.class)
//                    .includes(t->t.roles(),r->r.includes(x->x.routes()))
//                    .select(t -> {
//                        TestUserDTO2Proxy result = new TestUserDTO2Proxy();
//                        result.selectAll(t);
//                        SQLQueryable<StringProxy, String> select = t.roles().select(x -> new StringProxy(x.id()));
//                        result.roleIds().set(select);
//                        return result;
//                    })
//                    .toList();
//        }

    }
}