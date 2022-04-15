package jhresasfdhgfgh53456gg144453bgdh.Util.Util.Log;

import jhresasfdhgfgh53456gg144453bgdh.Util.Util.Parm.ParmTools;

import javax.servlet.http.HttpServletRequest;

public class Loog {
    public static String ReqlogForm(HttpServletRequest req){
        return "{req:{req},ip:{ip},URI:{URI},parm:{parm}}".
                replace("{req}", (CharSequence) ParmTools.NullDefault(req.getMethod(),"")).
                replace("{ip}",(CharSequence) ParmTools.NullDefault(req.getRemoteHost(),"")).
                replace("{URI}",(CharSequence) ParmTools.NullDefault(req.getRequestURI(),"")).
                replace("{parm}",(CharSequence) ParmTools.NullDefault(req.getQueryString(),""));
    }
}
