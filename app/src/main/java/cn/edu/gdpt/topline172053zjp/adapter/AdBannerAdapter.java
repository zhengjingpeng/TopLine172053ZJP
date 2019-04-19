package cn.edu.gdpt.topline172053zjp.adapter;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.MotionEvent;
import android.view.View;


import java.util.ArrayList;
import java.util.List;

import cn.edu.gdpt.topline172053zjp.bean.NewsBean;
import cn.edu.gdpt.topline172053zjp.fragment.AdBannerFragment;

public class AdBannerAdapter extends FragmentStatePagerAdapter {
private List<NewsBean> abl;
    public AdBannerAdapter(FragmentManager fm) {
        super(fm);
        abl=new ArrayList<NewsBean>();
    }
    public void setData(List<NewsBean> abl){
        this.abl=abl;
        notifyDataSetChanged();
    }
    @Override
    public Fragment getItem(int index) {
        Bundle args = new Bundle();
        if (abl.size() > 0)
            args.putSerializable("ad", abl.get(index % abl.size()));
        return AdBannerFragment.newInstance(args);
    }
    @Override
    public int getCount() {
        return abl==null? 0 :abl.size();
    }
    /**
     * 返回数据集的真实容量大小
     */
    @Override
    public int getItemPosition(Object object) {
        //防止刷新结果显示列表的时候出现缓存数据,重载这个函数,使之默认返回POSITION_NONE
        return POSITION_NONE;
    }
}
