package cn.edu.gdpt.topline172053zjp.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.itheima.PullToRefreshView;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.List;

import cn.edu.gdpt.topline172053zjp.R;
import cn.edu.gdpt.topline172053zjp.adapter.VideoListAdapter;
import cn.edu.gdpt.topline172053zjp.bean.VideoBean;
import cn.edu.gdpt.topline172053zjp.utils.Constant;
import cn.edu.gdpt.topline172053zjp.utils.JsonParse;
import cn.edu.gdpt.topline172053zjp.view.WrapRecyclerView;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideoFragment extends Fragment {
    private MHandler mHandler;
    private PullToRefreshView mPullToRefreshView;
    private WrapRecyclerView recycleView;
    private VideoListAdapter adapter;

    public VideoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mHandler=new MHandler();
        initData();
        View view= inflater.inflate(R.layout.fragment_video, container, false);
        recycleView = (WrapRecyclerView) view.findViewById(R.id.recycler_view);
        recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new VideoListAdapter(getActivity());
        recycleView.setAdapter(adapter);
        mPullToRefreshView=view.findViewById(R.id.pull_to_refresh);
        mPullToRefreshView.setOnRefreshListener(new PullToRefreshView.
                OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPullToRefreshView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPullToRefreshView.setRefreshing(false);
                        initData();
                    }
                }, 1000);
            }
        });
        return view;

    }

    private void initData() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(Constant.WEB_SITE + Constant.
                REQUEST_VIDEO_URL).build();
        Call call = okHttpClient.newCall(request);
        //开启异步线程访问网络
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Response response) throws IOException {
                String res = response.body().string();
                Message msg = new Message();
                msg.what = 1;
                msg.obj = res;
                mHandler.sendMessage(msg);
            }
            @Override
            public void onFailure(Request arg0, IOException arg1) {
            }
        });
    }
    class MHandler extends Handler {
        @Override
        public void dispatchMessage(Message msg) {
            super.dispatchMessage(msg);
            switch (msg.what) {
                case 1:
                    if (msg.obj != null) {
                        String vlResult = (String) msg.obj;
                        //使用Gson解析数据
                        List<VideoBean> videoList = JsonParse.getInstance().
                                getVideoList(vlResult);
                        adapter.setData(videoList);
                    }
                    break;
            }
        }
    }
}
