package cn.edu.gdpt.topline172053zjp.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class UtilsHelper {
    public static int getScreeWidth(Context context){
        WindowManager windowManager= (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics=new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }
}
