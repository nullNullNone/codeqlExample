package jhresasfdhgfgh53456gg144453bgdh.Apps.Api;


import jhresasfdhgfgh53456gg144453bgdh.Util.Ann.Autowrite;
import jhresasfdhgfgh53456gg144453bgdh.Util.Ann.Controller;
import jhresasfdhgfgh53456gg144453bgdh.Util.Ann.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Description：
 * Author：CXJ
 * Date： 2018-06-16 18:39
 * Remark：
 */

@Controller()
@RequestMapping("App")
public class App1 {

    @Autowrite
    private TestService testService;

    @RequestMapping("App")
    public String myTest(HttpServletRequest request, HttpServletResponse response){
        String res = request.getParameter("ap");
        if (res == null){
            return "nUlL";
        }
        return res;
    }
}

