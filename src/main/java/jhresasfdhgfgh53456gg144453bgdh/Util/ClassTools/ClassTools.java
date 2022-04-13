package jhresasfdhgfgh53456gg144453bgdh.Util.ClassTools;

import jhresasfdhgfgh53456gg144453bgdh.Util.Ann.Autowrite;
import jhresasfdhgfgh53456gg144453bgdh.Util.Ann.Controller;
import jhresasfdhgfgh53456gg144453bgdh.Util.Ann.RequestMapping;
import jhresasfdhgfgh53456gg144453bgdh.Util.Ann.Service;
import jhresasfdhgfgh53456gg144453bgdh.Util.Util.File.Filesys;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.logging.Logger;

public class ClassTools {
    //log
    public Logger log=Logger.getLogger("init");
    //system prop
    public Properties properties = new Properties();
    List<java.lang.String> reqaccess = new ArrayList<>();
    //prepare
    public List<java.lang.String> classNames = new ArrayList<java.lang.String>();
    //container
    public Map<java.lang.String, Object> ioc = new HashMap<java.lang.String, Object>();
    //handlerMapping的类型可以自定义为Handler
    public Map<java.lang.String, Method> handlerMapping = new HashMap<java.lang.String, Method>();
    public Map<java.lang.String, Object> controllerMap  =new HashMap<java.lang.String, Object>();

    public void init(java.lang.String conf) throws ServletException {
        log.info("load-cfg");
        //1.加载配置文件，填充properties字段；
        doLoadConfig(conf);
        //2.根据properties，初始化所有相关联的类,扫描用户设定的包下面所有的类
        doScanner(properties.getProperty("scanPackage"));
        //3.拿到扫描到的类,通过反射机制,实例化,并且放到ioc容器中(k-v  beanName-bean) beanName默认是首字母小写
        doInstance();
        // 4.自动化注入依赖
        doAutowired();
        //5.初始化HandlerMapping(将url和method对应上)
        initHandlerMapping();
        doAutowired2();
        //6.初始化请求方法表
        ReqAcessinit();
    }

    private List<java.lang.String> ReqAcessinit(){
        java.lang.String[] req = properties.getProperty("reqmethod").split("\\,");
        List<java.lang.String> str = new ArrayList<>();
        for (java.lang.String strmd:req)str.add(strmd);
        System.out.println(str);
        reqaccess = str;
        return str;
    }

    public boolean doReqallow(String s){
        return reqaccess.contains(s);
    }

    public void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        if(handlerMapping.isEmpty()){
            return;
        }
        java.lang.String url =req.getRequestURI();
        java.lang.String contextPath = req.getContextPath();
        url=url.replace(contextPath, "").replaceAll("/+", "/");
        // 去掉url前面的斜杠"/"，所有的@MyRequestMapping可以不用写斜杠"/"
        if(url.lastIndexOf('/')!=0){
            url=url.substring(1);
        }
        if(!this.handlerMapping.containsKey(url)){
            resp.getWriter().write("404 NOT FOUND!");
            log.info("404 NOT FOUND!");
            return;
        }
        Method method =this.handlerMapping.get(url);
        //获取方法的参数列表
        Class<?>[] parameterTypes = method.getParameterTypes();

        //获取请求的参数
        Map<java.lang.String, java.lang.String[]> parameterMap = req.getParameterMap();
        //保存参数值
        Object [] paramValues= new Object[parameterTypes.length];
        //方法的参数列表
        for (int i = 0; i<parameterTypes.length; i++){
            //根据参数名称，做某些处理
            java.lang.String requestParam = parameterTypes[i].getSimpleName();
            if (requestParam.equals("HttpServletRequest")){
                //参数类型已明确，这边强转类型
                paramValues[i]=req;
                continue;
            }
            if (requestParam.equals("HttpServletResponse")){
                paramValues[i]=resp;
                continue;
            }
            if(requestParam.equals("String")){
                for (Map.Entry<java.lang.String, java.lang.String[]> param : parameterMap.entrySet()) {
                    java.lang.String value =Arrays.toString(param.getValue()).replaceAll("\\[|\\]", "").replaceAll(",\\s", ",");
                    paramValues[i]=value;
                }
            }
        }
        //利用反射机制来调用
        try {
            //第一个参数是method所对应的实例 在ioc容器中
            //method.invoke(this.controllerMap.get(url), paramValues);
            resp.getWriter().println(method.invoke(this.controllerMap.get(url), paramValues));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Description:  根据配置文件位置，读取配置文件中的配置信息，将其填充到properties字段
     * Params:
     * @param location: 配置文件的位置
     * return: void
     * Author: CXJ
     * Date: 2018/6/16 19:07
     */
    private void  doLoadConfig(java.lang.String location){

        //把web.xml中的contextConfigLocation对应value值的文件加载到流里面
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(location);
        try {
            //用Properties文件加载文件里的内容
            log.info("Load prop:"+location);
            properties.load(resourceAsStream);
//            System.out.println(properties);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //关流
            if(null!=resourceAsStream){
                try {
                    resourceAsStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Description:  将指定包下扫描得到的类，添加到classNames字段中；
     * @param packageName: 需要扫描的包名 xxxx/Apps/xxxx.....
     * return: void
     */
    private void doScanner(java.lang.String packageName) {
        packageName = packageName.replace(".", File.separator);
        ArrayList<java.lang.String> cls = new Filesys().getAppClass(packageName);
        for (java.lang.String s:cls){
            classNames.add(s);
        }
    }

    /**
     * Description:  将classNames中的类实例化，经key-value：类名（小写）-类对象放入ioc字段中
     * Params:
     * @param :
     * return: void
     * Author: CXJ
     * Date: 2018/6/16 19:09
     */
    private void doInstance() {
        if (classNames.isEmpty()) {
            return;
        }
        for (java.lang.String className : classNames) {
            try {
                //把类搞出来,反射来实例化(只有加@Controller需要实例化)
                Class<?> clazz =Class.forName(className);
                if(clazz.isAnnotationPresent(Controller.class)){
                    ioc.put(toLowerFirstWord(clazz.getSimpleName()),clazz.newInstance());
                }else if(clazz.isAnnotationPresent(Service.class)){
                    Service service=clazz.getAnnotation(Service.class);
                    java.lang.String beanName=service.value();
                    if ("".equals(beanName.trim())){
                        beanName=toLowerFirstWord(clazz.getSimpleName());
                    }

                    Object instance= clazz.newInstance();
                    ioc.put(beanName,instance);
                    Class[] interfaces=clazz.getInterfaces();
                    for (Class<?> i:interfaces){
                        ioc.put(i.getName(),instance);
                    }
                }
                else{
                    continue;
                }
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
        }
    }

    /**
     * Description:自动化的依赖注入
     * Params:
     * @param :
     * return: void
     * Author: CXJ
     * Date: 2018/6/16 20:40
     */
    private void doAutowired(){

        if (ioc.isEmpty()){
            return;
        }
        for (Map.Entry<java.lang.String, Object> entry:ioc.entrySet()){
            //包括私有的方法，在spring中没有隐私，@MyAutowired可以注入public、private字段
            Field[] fields=entry.getValue().getClass().getDeclaredFields();
            for (Field field:fields){
                if (!field.isAnnotationPresent(Autowrite.class)){
                    continue;
                }
                Autowrite autowired= field.getAnnotation(Autowrite.class);
                java.lang.String beanName=autowired.value().trim();
                if ("".equals(beanName)){
                    beanName=field.getType().getName();
                }
                field.setAccessible(true);
                try {
                    field.set(entry.getValue(),ioc.get(beanName));
                }catch (Exception e){
                    e.printStackTrace();
                    continue;
                }

            }
        }
    }

    private void doAutowired2(){

        if (controllerMap.isEmpty()){
            return;
        }
        for (Map.Entry<java.lang.String, Object> entry:controllerMap.entrySet()){
            //包括私有的方法，在spring中没有隐私，@MyAutowired可以注入public、private字段
            Field[] fields=entry.getValue().getClass().getDeclaredFields();
            for (Field field:fields){
                if (!field.isAnnotationPresent(Autowrite.class)){
                    continue;
                }
                Autowrite autowired= field.getAnnotation(Autowrite.class);
                java.lang.String beanName=autowired.value().trim();
                if ("".equals(beanName)){
                    beanName=field.getType().getName();
                }
                field.setAccessible(true);
                try {
                    field.set(entry.getValue(),ioc.get(beanName));
                }catch (Exception e){
                    e.printStackTrace();
                    continue;
                }

            }
        }
    }

    /**
     * Description:  初始化HandlerMapping(将url和method对应上)
     * Params:
     * @param :
     * return: void
     * Author: CXJ
     * Date: 2018/6/16 19:12
     */
    private void initHandlerMapping(){

        if(ioc.isEmpty()){
            return;
        }
        try {
            for (Map.Entry<java.lang.String, Object> entry: ioc.entrySet()) {
                Class<? extends Object> clazz = entry.getValue().getClass();
                if(!clazz.isAnnotationPresent(Controller.class)){
                    continue;
                }

                //拼url时,是controller头的url拼上方法上的url
                java.lang.String baseUrl ="";
                if(clazz.isAnnotationPresent(RequestMapping.class)){
                    RequestMapping annotation = clazz.getAnnotation(RequestMapping.class);
                    baseUrl=annotation.value();
                }
                Method[] methods = clazz.getMethods();
                for (Method method : methods) {
                    if(!method.isAnnotationPresent(RequestMapping.class)){
                        continue;
                    }
                    RequestMapping annotation = method.getAnnotation(RequestMapping.class);
                    java.lang.String url = annotation.value();

                    url =(baseUrl+"/"+url).replaceAll("/+", "/");
                    handlerMapping.put(url,method);
                    controllerMap.put(url,clazz.newInstance());
                    System.out.println(url+","+method);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Description:  将字符串中的首字母小写
     * Params:
     * @param name:
     * return: java.lang.String
     * Author: CXJ
     * Date: 2018/6/16 19:13
     */
    private java.lang.String toLowerFirstWord(java.lang.String name){

        char[] charArray = name.toCharArray();
        charArray[0] += 32;
        return java.lang.String.valueOf(charArray);
    }
    
}
