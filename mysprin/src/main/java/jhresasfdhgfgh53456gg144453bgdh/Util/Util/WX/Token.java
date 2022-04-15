package jhresasfdhgfgh53456gg144453bgdh.Util.Util.WX;



import jhresasfdhgfgh53456gg144453bgdh.Util.Util.Encode.SHA1m;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class Token {

    public static String TokenCalc(String token, String timestamp, String nonce){
//        System.out.println(token);
//        System.out.println(timestamp);
//        System.out.println(nonce);
        String[] str = { token, timestamp, nonce };
        Arrays.sort(str); // 字典序排序
        String bigStr = str[0] + str[1] + str[2];
        return SHA1m.Encode(bigStr);
    }

    public static boolean TokenCheck(String token,String tokenget){
        return token.equals(tokenget);
    }

    public static boolean TockenPkg(String token, String timestamp, String nonce,String tokenget){
        return TokenCheck(TokenCalc(token,timestamp,nonce),tokenget);
    }


    public static void token(String tok,HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = "";
        String timestamp = "";
        String nonce = "";
        String echostr = "";

        token = req.getParameter("signature");
        timestamp = req.getParameter("timestamp");
        nonce = req.getParameter("nonce");
        echostr = req.getParameter("echostr");

        if (token == null || timestamp == null || nonce == null){
            resp.getWriter().println("{err:[code:500,msg:null]}");
            return;
        }

        if (Token.TockenPkg(tok,timestamp,nonce,token)){
            resp.getWriter().println(echostr);
        }
    }
}
