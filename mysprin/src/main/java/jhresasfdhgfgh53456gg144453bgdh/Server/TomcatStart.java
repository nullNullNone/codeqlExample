package jhresasfdhgfgh53456gg144453bgdh.Server;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.descriptor.web.FilterDef;
import org.apache.tomcat.util.descriptor.web.FilterMap;


import javax.servlet.Filter;
import javax.servlet.ServletException;


public class TomcatStart {
    public void run() throws ServletException, LifecycleException {

        //Ent
        Tomcat tomcat = new Tomcat();
        StandardContext context = new StandardContext();
        ServerConfig serverConfig = new ServerConfig();
        FilterDef filterDef = new FilterDef();//Filter定义

        //Set-Server
        tomcat.setPort(serverConfig.getTomcatPort());

        //Set-Context
        context.setPath(serverConfig.getContext("index"));
        context.addLifecycleListener(new Tomcat.FixContextListener());
        tomcat.getHost().addChild(context);

        //filter
//        filterDef.setFilterName("test-filter");
//        filterDef.setFilterClass(Filter.class.getName());
//        filterDef.addInitParameter("name", "test-filter");
//        context.addFilterDef(filterDef);

//        FilterMap filterMap = new FilterMap();//Filter路径映射
//        filterMap.setFilterName("test-filter");
//        filterMap.addURLPattern("/*");
//        context.addFilterMap(filterMap);


        //api
        tomcat.addServlet(serverConfig.getContextPath(), "apiServlet", new Dispatcher());
        context.addServletMapping("/*", "apiServlet");


        tomcat.start();
        tomcat.getServer().await();
    }

}