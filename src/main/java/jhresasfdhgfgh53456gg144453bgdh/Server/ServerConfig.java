package jhresasfdhgfgh53456gg144453bgdh.Server;


import jhresasfdhgfgh53456gg144453bgdh.Server.Abs.Server;

import java.io.File;
import java.util.HashMap;

public class ServerConfig extends Server {
    public ServerConfig(){
        //Internet
        setTomcatPort(8085);
        setTomcatHostname("127.0.0.1");

        //Dir
        String userDir = System.getProperty("user.dir"); // 项目目录
        String tomcatBaseDir = userDir + File.separatorChar + "tmp";
        setBaseDir(tomcatBaseDir);

        //context
        setContextPaths(new HashMap<String, String>());
        getContextPaths().put("index","");
        getContextPaths().put("tmp","/tmp");

        //cut
        setBaseurlcut("api");
    }

    public String getContext(String s){
        return getContextPaths().get(s);
    }
}
