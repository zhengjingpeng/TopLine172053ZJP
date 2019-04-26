package cn.edu.gdpt.topline172053zjp.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import cn.edu.gdpt.topline172053zjp.bean.NewsBean;
import cn.edu.gdpt.topline172053zjp.bean.PythonBean;

public class JsonParse {
    private static JsonParse instance;
    private JsonParse(){

    }
    public static JsonParse getInstance(){
        if(instance==null){
            instance=new JsonParse();
        }
        return instance;
    }
        Gson gson=new Gson();
        public List<NewsBean> getAdList(String json){
        Type listType=new TypeToken<List<NewsBean>>(){}.getType();
        List<NewsBean> alList =gson.fromJson(json,listType);
        return alList;
    }
    public List<PythonBean> getPythonList(String json) {
        //使用gson库解析JSON数据
        Gson gson = new Gson();
        //创建一个TypeToken的匿名子类对象，并调用对象的getType()方法
        Type listType = new TypeToken<List<PythonBean>>() {}.getType();
        //把获取到的信息集合存到pythonList中
        List<PythonBean> pythonList = gson.fromJson(json, listType);
        return pythonList;
    }
}
