package cn.edu.gdpt.topline172053zjp.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import cn.edu.gdpt.topline172053zjp.bean.NewsBean;

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
}
