package jhresasfdhgfgh53456gg144453bgdh.Util.Util.StringUtil;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class REtool {
    public static ArrayList<String> RePick(String pattern, String str){
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);
        ArrayList<String> stringArrayList = new ArrayList<>();
        if (m.find( ))
        for(int i = 0;i<m.groupCount()+1;){
            stringArrayList.add(m.group(i));
            i++;
        }
        return stringArrayList;
    }
}
