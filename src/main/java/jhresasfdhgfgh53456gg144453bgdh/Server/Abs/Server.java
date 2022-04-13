package jhresasfdhgfgh53456gg144453bgdh.Server.Abs;

import java.util.Map;

public abstract class Server {
    public  int TOMCAT_PORT = 8080;
    public  String TOMCAT_HOSTNAME = "127.0.0.1";
    public  String WEBAPP_PATH = "/";
    public  String WEBINF_CLASSES = "";
    public  String CLASS_PATH = "";
    public  String INTERNAL_PATH = "/";
    public  String contextPath = "";
    public  String BaseDir = "";
    public String baseurlcut = "";

    public Map<String,String> contextPaths;

    public  int getTomcatPort() {
        return TOMCAT_PORT;
    }

    public  void setTomcatPort(int tomcatPort) {
        TOMCAT_PORT = tomcatPort;
    }

    public  String getTomcatHostname() {
        return TOMCAT_HOSTNAME;
    }

    public  void setTomcatHostname(String tomcatHostname) {
        TOMCAT_HOSTNAME = tomcatHostname;
    }

    public  String getWebappPath() {
        return WEBAPP_PATH;
    }

    public  void setWebappPath(String webappPath) {
        WEBAPP_PATH = webappPath;
    }

    public  String getWebinfClasses() {
        return WEBINF_CLASSES;
    }

    public  void setWebinfClasses(String webinfClasses) {
        WEBINF_CLASSES = webinfClasses;
    }

    public  String getClassPath() {
        return CLASS_PATH;
    }

    public  void setClassPath(String classPath) {
        CLASS_PATH = classPath;
    }

    public  String getInternalPath() {
        return INTERNAL_PATH;
    }

    public  void setInternalPath(String internalPath) {
        INTERNAL_PATH = internalPath;
    }

    public String getContextPath() {
        return contextPath;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    public String getBaseDir() {
        return BaseDir;
    }

    public void setBaseDir(String baseDir) {
        BaseDir = baseDir;
    }

    public Map<String, String> getContextPaths() {
        return contextPaths;
    }

    public void setContextPaths(Map<String, String> contextPaths) {
        this.contextPaths = contextPaths;
    }

    public String getBaseurlcut() {
        return baseurlcut;
    }

    public void setBaseurlcut(String baseurlcut) {
        this.baseurlcut = baseurlcut;
    }
}
