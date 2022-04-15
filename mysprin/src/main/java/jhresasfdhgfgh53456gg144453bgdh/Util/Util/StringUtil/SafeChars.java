package jhresasfdhgfgh53456gg144453bgdh.Util.Util.StringUtil;

public class SafeChars {

    public static String SafeReplace(String string){
        string = string
                .replace("$","")
                .replace("<","")
        .replace(">","")
        .replace("'","")
        .replace("\"","");
        return string;
    }
}
