package jhresasfdhgfgh53456gg144453bgdh.Util.Util.WX.Msg;

public class MsgBody {
    String ToUserName;//	开发者微信号
    String FromUserName;//	发送方帐号（一个OpenID）
    int CreateTime;//	消息创建时间 （整型）
    String MsgType;//	消息类型，文本为text
    String MsgId;//	消息id，64位整型

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public int getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(int createTime) {
        CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }

    public String getMsgId() {
        return MsgId;
    }

    public void setMsgId(String msgId) {
        MsgId = msgId;
    }
}
