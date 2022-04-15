package jhresasfdhgfgh53456gg144453bgdh.Apps.Api;


import jhresasfdhgfgh53456gg144453bgdh.Util.Ann.Autowrite;
import jhresasfdhgfgh53456gg144453bgdh.Util.Ann.Controller;
import jhresasfdhgfgh53456gg144453bgdh.Util.Ann.RequestMapping;
import jhresasfdhgfgh53456gg144453bgdh.Util.Ann.RequestParam;


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
@RequestMapping("test1")
public class Test1Controller {

    @Autowrite
    private TestService testService;

    @RequestMapping("test")
    public void myTest(HttpServletRequest request, HttpServletResponse response,
                       @RequestParam("param") String param){
        try {
            response.getWriter().write( "Test1Controller:the param you send is :"+param);
            testService.printParam(param);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

