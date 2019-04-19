package cn.edu.gdpt.topline172053zjp.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.itheima.PullToRefreshView;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.List;

import cn.edu.gdpt.topline172053zjp.R;
import cn.edu.gdpt.topline172053zjp.adapter.AdBannerAdapter;
import cn.edu.gdpt.topline172053zjp.adapter.HomeListAdapter;
import cn.edu.gdpt.topline172053zjp.bean.NewsBean;
import cn.edu.gdpt.topline172053zjp.utils.Constant;
import cn.edu.gdpt.topline172053zjp.utils.JsonParse;
import cn.edu.gdpt.topline172053zjp.utils.UtilsHelper;
import cn.edu.gdpt.topline172053zjp.view.WrapRecyclerView;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private OkHttpClient okHttpClient;
    private MHandle mHandle;
    private View view;
    private PullToRefreshView mPullToRefreshView;
    private WrapRecyclerView recyclerView;
    private HomeListAdapter adapter;
    private View adBannerLay;
    private ViewPager adPager;
    private AdBannerAdapter ada;
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        okHttpClient = new OkHttpClient();
        mHandle = new MHandle();
        getADData();
       /* getData();*/
        view =inflater.inflate(R.layout.fragment_home, container, false);
        mPullToRefreshView=view.findViewById(R.id.pull_to_refresh);
        recyclerView=view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter=new HomeListAdapter();
        recyclerView.setAdapter(adapter);
        View headView=inflater.inflate(R.layout.head_view,container,false);
        recyclerView.addHeaderView(headView);
        adBannerLay=headView.findViewById(R.id.adbanner_layout);
        adPager=headView.findViewById(R.id.slidingAdvertBanner);
        ada=new AdBannerAdapter(getActivity().getSupportFragmentManager());
        adPager.setAdapter(ada);
        resetSize();
        return  view;
    }

    private void resetSize() {
        int sw = UtilsHelper.getScreeWidth(getActivity());
        int adLheight = sw / 2; //广告条高度
        ViewGroup.LayoutParams adlp = adBannerLay.getLayoutParams();
        adlp.width = sw;
        adlp.height = adLheight;
        adBannerLay.setLayoutParams(adlp);
    }

    /*private void getData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient okHttpClient = new OkHttpClient();
                Request request = new Request.Builder().url("http://61.142.174.202:8080/topline/home_ad_list_data.json").get().build();
                try {
                    String string = okHttpClient.newCall(request).execute().body().string();
                    final NewsBean newsBean = new Gson().fromJson(string, NewsBean.class);
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //主线程
                            RecyclerView recyclerView = view.findViewById(R.id.rec);
                            recyclerView.setAdapter(new RecyclerView.Adapter() {
                                class IViewHolder extends RecyclerView.ViewHolder{
                                    TextView textView;
                                    public IViewHolder(@NonNull View itemView) {
                                        super(itemView);
                                        textView = itemView.findViewById(R.id.tv);
                                    }
                                }
                                @NonNull
                                @Override
                                public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                                    View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.main_title_bar, viewGroup, false);
                                    return new IViewHolder(inflate);
                                }

                                @Override
                                public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                                    ((IViewHolder)viewHolder).textView.setText(newsBean.getNewsName());
                                }



                                @Override
                                public int getItemCount() {
                                    return 3;
                                }
                            });
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }*/

    private void getADData() {
        Request request = new Request.Builder().url(Constant.WEB_SITE + Constant.REQUEST_AD_URL).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
            }

            @Override
            public void onResponse(Response response) throws IOException {
                String res = response.body().string();
                /*Log.i("ad",res);*/
                Message msg = new Message();
                msg.what = 1;
                msg.obj = res;
                mHandle.sendMessage(msg);
            }
        });
    }

    class MHandle extends Handler {
        @Override
        public void dispatchMessage(Message msg) {
            super.dispatchMessage(msg);
            switch (msg.what) {
                case 1:
                    if(msg.obj!=null){
                        String adResult = (String) msg.obj;
                        List<NewsBean> adList = JsonParse.getInstance().getAdList(adResult);
                        if(adList!=null){
                            if(adList.size()>0){
                                ada.setData(adList);
                            }
                        }
                    }
                        break;

            }
        }
    }
}
