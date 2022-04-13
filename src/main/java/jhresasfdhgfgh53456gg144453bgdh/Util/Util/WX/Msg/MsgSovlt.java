package jhresasfdhgfgh53456gg144453bgdh.Util.Util.WX.Msg;

import com.thoughtworks.xstream.XStream;
import jhresasfdhgfgh53456gg144453bgdh.Util.Util.Doc.XMLm;
import org.dom4j.DocumentException;


import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class MsgSovlt {

    public static String Boby2xml(Object obj){
        XStream xStream = XMLm.CdataXstream();
        return xStream.toXML(obj);
    }

    public static Map<String, String> XmlSolvStream(InputStream in) throws IOException, DocumentException {
        return XMLm.XML2MapFstream(in);
    }

}
