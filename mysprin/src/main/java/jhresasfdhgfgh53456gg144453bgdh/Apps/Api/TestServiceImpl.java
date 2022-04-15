package jhresasfdhgfgh53456gg144453bgdh.Apps.Api;


import jhresasfdhgfgh53456gg144453bgdh.Util.Ann.Service;

/**
 * Description：
 * Author：CXJ
 * Date： 2018-06-16 20:45
 * Remark：
 */

@Service
public class TestServiceImpl implements TestService {

    @Override
    public void printParam(String param) {
        System.out.println("接收到的参数为： "+param);
    }
}

