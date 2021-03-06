package io.github.eternalpro.core;

import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.util.JdbcConstants;
import com.alibaba.druid.wall.WallFilter;
import com.jfinal.config.*;
import com.jfinal.ext.plugin.tablebind.AutoTableBindPlugin;
import com.jfinal.ext.route.AutoBindRoutes;
import com.jfinal.plugin.activerecord.dialect.PostgreSqlDialect;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.render.ViewType;
import io.github.eternalpro.interceptor.GlobalInterceptor;
import io.github.gefangshuai.wfinal.flash.interceptor.FlashMessageInterceptor;
import io.github.gefangshuai.wfinal.menumapper.interceptor.MenuMapperInterceptor;
import io.github.gefangshuai.wfinal.menumapper.plugin.MenuMapperPlugin;
import io.github.gefangshuai.wfinal.security.core.SecurityRule;
import io.github.gefangshuai.wfinal.security.interceptor.SecurityInterceptor;
import io.github.gefangshuai.wfinal.security.plugin.SecurityPlugin;

import java.util.Properties;

/**
 * Created by fangshuai on 2015-03-17-0017.
 */
public class AppConfig  extends JFinalConfig {
    private Properties dbProperties;   // properties 配置文件
    private Properties configProperties;
    /**
     * 工程配置信息
     * @param me
     */
    @Override
    public void configConstant(Constants me) {
        // 加载properties文件
        dbProperties = loadPropertyFile("db.properties");
        configProperties = loadPropertyFile("config.properties");

        //设置视图jsp
        me.setViewType(ViewType.JSP);

        // 设置视图目录
        me.setBaseViewPath("/WEB-INF/views/");

        // 设置错误页面
        me.setError404View("/WEB-INF/views/error/404.jsp");
        me.setError500View("/WEB-INF/views/error/500.jsp");

        /**
         * 设置未授权
         */
        me.setErrorView(401, "/WEB-INF/views/error/unauthorized.jsp");
        me.setErrorView(403, "/WEB-INF/views/error/forbid.jsp");

        // 开启debug模式
        me.setDevMode(true);
        me.setMaxPostSize(1024 * 1024 * 1024);  // 1GB
    }

    /**
     * 配置路由
     * @param me
     */
    @Override
    public void configRoute(Routes me) {
        // 开启注解模式
        AutoBindRoutes routes = new AutoBindRoutes();
        me.add(routes);
    }

    /**
     * 配置插件
     * @param me
     */
    @Override
    public void configPlugin(Plugins me) {
        // 添加数据库连接池
        DruidPlugin dPlugin = new DruidPlugin(
                dbProperties.getProperty("jdbc.url"),
                dbProperties.getProperty("jdbc.username"),
                dbProperties.getProperty("jdbc.password"),
                dbProperties.getProperty("jdbc.driver")
        );
        dPlugin.addFilter(new StatFilter());
        WallFilter wall = new WallFilter();
        wall.setDbType(JdbcConstants.POSTGRESQL);
        dPlugin.addFilter(wall);
        me.add(dPlugin);

        // 添加自动绑定model与表插件
        AutoTableBindPlugin autoTableBindPlugin = new AutoTableBindPlugin(dPlugin);
        autoTableBindPlugin.setDialect(new PostgreSqlDialect());
        autoTableBindPlugin.setShowSql(true);
        me.add(autoTableBindPlugin);

        MenuMapperPlugin menuMapperPlugin = new MenuMapperPlugin("headerMenu");
        me.add(menuMapperPlugin);

        SecurityPlugin securityPlugin = new SecurityPlugin();
        SecurityRule securityRule = new SecurityRule();
        securityRule.setBackToLoginPage(true);
        securityRule.setLoginUrl("/signin");
        securityRule.setSubjectKey("subject");
        securityRule.setFilterUrls(new String[]{"/user", "/book"});
        securityRule.setUseAccessActionFilter(true);
        securityPlugin.setSecurityRule(securityRule);

        me.add(securityPlugin);
    }

    /**
     * 配置全局拦截器
     * @param me
     */
    @Override
    public void configInterceptor(Interceptors me) {
        me.add(new FlashMessageInterceptor());
        me.add(new GlobalInterceptor());
    }

    @Override
    public void configHandler(Handlers me) {

    }

}
