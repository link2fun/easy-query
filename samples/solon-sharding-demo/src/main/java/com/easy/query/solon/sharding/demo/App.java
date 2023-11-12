package com.easy.query.solon.sharding.demo;

import com.easy.query.core.bootstrapper.EasyQueryBuilderConfiguration;
import com.easy.query.core.configuration.EasyQueryOption;
import com.easy.query.core.configuration.QueryConfiguration;
import com.easy.query.core.configuration.dialect.Dialect;
import com.easy.query.core.configuration.nameconversion.NameConversion;
import com.easy.query.core.job.EasyTimeJobManager;
import org.noear.solon.Solon;

/**
 * create time 2023/8/2 11:32
 * 文件说明
 *
 * @author xuejiaming
 */
public class App {
    public static void main(String[] args) {
        Solon.start(App.class,args,app->{
            app.onEvent(EasyQueryBuilderConfiguration.class,e->{
                e.replaceServiceFactory(QueryConfiguration.class, s->{
                    QueryConfiguration queryConfiguration = new QueryConfiguration(s.getService(EasyQueryOption.class)
                            ,s.getService(Dialect.class)
                            ,s.getService(NameConversion.class)
                            ,s.getService(EasyTimeJobManager.class)
                    );
//                    queryConfiguration.applyInterceptor();
//                    queryConfiguration.applyLogicDeleteStrategy();
//                    queryConfiguration.applyValueConverter();
                    return queryConfiguration;
                });


//                HttpLogRequest httpLogRequest = app.context().getBean(HttpLogRequest.class);
//                e.replaceService(app.context());
//                e.replaceService(JdbcExecutorListener.class,LogSlowSQLListener.class);
            });
        });
    }
}
