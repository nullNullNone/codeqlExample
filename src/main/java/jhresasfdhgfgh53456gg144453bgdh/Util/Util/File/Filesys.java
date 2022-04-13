package jhresasfdhgfgh53456gg144453bgdh.Util.Util.File;

import java.io.File;
import java.util.ArrayList;

class FilesysABS {
    String path;
    String Appspath;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getAppspath() {
        return Appspath;
    }

    public void setAppspath(String appspath) {
        Appspath = appspath;
    }
}

public class Filesys extends FilesysABS{
    ArrayList<File> fileArrayList;

    public Filesys(){
        setPath(new File(Filesys.class.getResource("/").getPath()).getPath());
        fileArrayList = new ArrayList<>();
        scan(new File(getPath()),fileArrayList);
    }

    public void scan(File f, ArrayList<File> pa) {
        if (f != null) {
            if (f.isDirectory()) {
                File[] fileArray = f.listFiles();
                if (fileArray != null) {
                    for (int i = 0; i < fileArray.length; i++) {
                        // µÝ¹éµ÷ÓÃ
                        scan(fileArray[i],pa);
                    }
                }
            } else {
                pa.add(f);
            }
        }
    }

    public ArrayList<String> getAppClass(String app){
        ArrayList<String> cs = new ArrayList<>();
        String p = "";
        for(File f:fileArrayList){
            p = f.getPath();
            if (p.contains(app) && p.endsWith("class")){
                cs.add(p.replace(getPath(),"").
                        substring((1)%p.length()).
                        replace(File.separator,".").
                        replace(".class",""));
            }
        }
        return cs;
    }


  public ArrayList<String> AllClass(){
        ArrayList<String> cs = new ArrayList<>();
        String p = "";
        for(File f:fileArrayList){
            p = f.getPath();
            if (p.endsWith("class")){
                cs.add(p.replace(getPath(),"").
                        substring((1)%p.length()).
                        replace(File.separator,".").
                        replace(".class",""));
            }
        }
        return cs;
    }
	

}