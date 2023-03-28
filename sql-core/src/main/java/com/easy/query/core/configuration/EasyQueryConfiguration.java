package com.easy.query.core.configuration;

import com.easy.query.core.basic.plugin.logicdel.*;
import com.easy.query.core.basic.plugin.version.EasyVersionStrategy;
import com.easy.query.core.config.DefaultEasyQueryDialect;
import com.easy.query.core.config.EasyQueryDialect;
import com.easy.query.core.config.NameConversion;
import com.easy.query.core.basic.plugin.encryption.EasyEncryptionStrategy;
import com.easy.query.core.exception.EasyQueryException;
import com.easy.query.core.basic.enums.LogicDeleteStrategyEnum;
import com.easy.query.core.config.DefaultNameConversion;
import com.easy.query.core.basic.plugin.interceptor.EasyInterceptor;
import com.easy.query.core.util.ClassUtil;
import com.easy.query.core.util.StringUtil;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @FileName: JDQCConfiguration.java
 * @Description: 文件说明
 * @Date: 2023/2/7 09:06
 * @author xuejiaming
 */
public class EasyQueryConfiguration {
    private static final EasyLogicDeleteStrategy BOOL_LOGIC_DELETE = new BooleanEasyEntityTypeConfiguration();
    private static final EasyLogicDeleteStrategy TIMESTAMP_LOGIC_DELETE = new DeleteLongTimestampEasyEntityTypeConfiguration();
    private static final EasyLogicDeleteStrategy LOCAL_DATE_TIME_LOGIC_DELETE = new LocalDateTimeEasyEntityTypeConfiguration();
    private static final EasyLogicDeleteStrategy LOCAL_DATE_LOGIC_DELETE = new LocalDateEasyLogicDeleteStrategy();


    private NameConversion nameConversion = new DefaultNameConversion();
    private EasyQueryDialect dialect = new DefaultEasyQueryDialect();
//    private Map<Class<?>, EntityTypeConfiguration<?>> entityTypeConfigurationMap = new HashMap<>();
    private Map<String, EasyInterceptor> globalInterceptorMap =new ConcurrentHashMap<>();
    private Map<String, EasyLogicDeleteStrategy> globalLogicDeleteStrategyMap = new ConcurrentHashMap<>();
    private Map<Class<? extends EasyEncryptionStrategy>, EasyEncryptionStrategy> easyEncryptionStrategyMap = new ConcurrentHashMap<>();
    private Map<Class<? extends EasyVersionStrategy>, EasyVersionStrategy> easyVersionStrategyMap = new ConcurrentHashMap<>();
    private final boolean deleteThrowError;

    public EasyQueryConfiguration() {
       this(true);
    }
    public EasyQueryConfiguration(boolean deleteThrowError) {
        this.deleteThrowError=deleteThrowError;
    }

    public boolean deleteThrow(){
        return deleteThrowError;
    }

    public NameConversion getNameConversion() {
        return nameConversion;
    }

    public void setNameConversion(NameConversion nameConversion) {
        this.nameConversion = nameConversion;
    }

    public EasyQueryDialect getDialect() {
        return dialect;
    }

    public void setDialect(EasyQueryDialect dialect) {
        this.dialect = dialect;
    }

    public void applyGlobalInterceptor(EasyInterceptor globalInterceptor){
        String interceptorName = globalInterceptor.name();
        if(StringUtil.isBlank(interceptorName)){
            throw new EasyQueryException(ClassUtil.getInstanceSimpleName(globalInterceptor)+"cant get interceptor name");
        }
        if(globalInterceptorMap.containsKey(interceptorName)){
            throw new EasyQueryException("global interceptor:" + interceptorName + ",repeat");
        }
        globalInterceptorMap.put(interceptorName,globalInterceptor);
    }


    public EasyInterceptor getGlobalInterceptor(String name){
        if(name==null){
            throw new IllegalArgumentException("cant get global interceptor,name is null");
        }
        return globalInterceptorMap.get(name);
    }
    public Collection<EasyInterceptor> getGlobalInterceptors(){
        return globalInterceptorMap.values();
    }
//    public void applyEntityTypeConfiguration(EntityTypeConfiguration<?> entityTypeConfiguration) {
//        entityTypeConfigurationMap.put(entityTypeConfiguration.entityType(), entityTypeConfiguration);
//    }
//
//    public EntityTypeConfiguration<?> getEntityTypeConfiguration(Class<?> entityType) {
//        return entityTypeConfigurationMap.get(entityType);
//    }

    public void applyGlobalLogicDeleteStrategy(EasyLogicDeleteStrategy globalLogicDeleteStrategy) {
        String strategy = globalLogicDeleteStrategy.getStrategy();
        if (globalLogicDeleteStrategyMap.containsKey(strategy)) {
            throw new EasyQueryException("global logic delete strategy:" + strategy + ",repeat");
        }
        globalLogicDeleteStrategyMap.put(strategy, globalLogicDeleteStrategy);
    }

    /**
     * 获取
     *
     * @param strategy
     * @return
     */
    public EasyLogicDeleteStrategy getGlobalLogicDeleteStrategy(String strategy) {
        return globalLogicDeleteStrategyMap.get(strategy);
    }
    public EasyLogicDeleteStrategy getGlobalLogicDeleteStrategyNotNull(String strategy) {
        EasyLogicDeleteStrategy globalLogicDeleteStrategy = getGlobalLogicDeleteStrategy(strategy);
        if(globalLogicDeleteStrategy==null){
            throw new EasyQueryException("easy logic delete strategy not found. strategy:"+strategy);
        }
        return globalLogicDeleteStrategy;
    }

    public EasyLogicDeleteStrategy getSysGlobalLogicDeleteStrategyNotNull(LogicDeleteStrategyEnum strategy) {
        if (Objects.equals(LogicDeleteStrategyEnum.BOOLEAN, strategy)) {
            return BOOL_LOGIC_DELETE;
        } else if (Objects.equals(LogicDeleteStrategyEnum.DELETE_LONG_TIMESTAMP, strategy)) {
            return TIMESTAMP_LOGIC_DELETE;
        } else if (Objects.equals(LogicDeleteStrategyEnum.LOCAL_DATE_TIME, strategy)) {
            return LOCAL_DATE_TIME_LOGIC_DELETE;
        } else if (Objects.equals(LogicDeleteStrategyEnum.LOCAL_DATE, strategy)) {
            return LOCAL_DATE_LOGIC_DELETE;
        }
        throw new EasyQueryException("easy logic delete strategy not found. strategy:"+strategy);
    }
    public void applyEasyEncryptionStrategy(EasyEncryptionStrategy encryptionStrategy) {
        Class<? extends EasyEncryptionStrategy> strategyClass = encryptionStrategy.getClass();
        if (easyEncryptionStrategyMap.containsKey(strategyClass)) {
            throw new EasyQueryException("easy encryption strategy:" + ClassUtil.getSimpleName(strategyClass) + ",repeat");
        }
        easyEncryptionStrategyMap.put(strategyClass, encryptionStrategy);
    }

    public EasyEncryptionStrategy getEasyEncryptionStrategy(Class<? extends EasyEncryptionStrategy> strategy){
        return easyEncryptionStrategyMap.get(strategy);
    }
    public boolean containEasyEncryptionStrategy(Class<? extends EasyEncryptionStrategy> strategy){
        return getEasyEncryptionStrategy(strategy)!=null;
    }
    public EasyEncryptionStrategy getEasyEncryptionStrategyNotNull(Class<? extends EasyEncryptionStrategy> strategy){
        EasyEncryptionStrategy easyEncryptionStrategy = getEasyEncryptionStrategy(strategy);
        if(easyEncryptionStrategy==null){
            throw new EasyQueryException("easy encryption strategy not found. strategy:"+ClassUtil.getSimpleName(strategy));
        }
        return easyEncryptionStrategy;
    }
    //easyVersionStrategyMap

    public void applyEasyVersionStrategy(EasyVersionStrategy versionStrategy) {
        Class<? extends EasyVersionStrategy> strategyClass = versionStrategy.getClass();
        if (easyVersionStrategyMap.containsKey(strategyClass)) {
            throw new EasyQueryException("easy version strategy:" + ClassUtil.getSimpleName(strategyClass) + ",repeat");
        }
        easyVersionStrategyMap.put(strategyClass, versionStrategy);
    }

    public EasyVersionStrategy getEasyVersionStrategy(Class<? extends EasyVersionStrategy> strategy){
        return easyVersionStrategyMap.get(strategy);
    }
    public boolean containEasyVersionStrategy(Class<? extends EasyVersionStrategy> strategy){
        return getEasyVersionStrategy(strategy)!=null;
    }
    public EasyVersionStrategy getEasyVersionStrategyNotNull(Class<? extends EasyVersionStrategy> strategy){
        EasyVersionStrategy easyVersionStrategy = getEasyVersionStrategy(strategy);
        if(easyVersionStrategy==null){
            throw new EasyQueryException("easy version strategy not found. strategy:"+ClassUtil.getSimpleName(strategy));
        }
        return easyVersionStrategy;
    }
}