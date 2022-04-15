package jhresasfdhgfgh53456gg144453bgdh.Server;


import jhresasfdhgfgh53456gg144453bgdh.Util.ClassTools.ClassTools;
import jhresasfdhgfgh53456gg144453bgdh.Util.Util.Log.Loog;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

public class Dispatcher extends HttpServlet {

    public Logger log=Logger.getLogger("init");
    ClassTools classTools;

    @Override
    public void init() throws ServletException {
        classTools = new ClassTools();
        classTools.init("myapplication.properties");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 注释掉父类实现，不然会报错：405 HTTP method GET is not supported by this URL
        //super.doPost(req, resp);
        log.info(Loog.ReqlogForm(req));
        if (classTools.doReqallow(req.getMethod())){
            try {
                //处理请求
                classTools.doDispatch(req,resp);
            } catch (Exception e) {
                resp.getWriter().write("500!! Server Exception");
            }
        }else {
            resp.getWriter().write("{405 Method Not Allow</h1>");
        }
    }
}
