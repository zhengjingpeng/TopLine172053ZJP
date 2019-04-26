package cn.edu.gdpt.topline172053zjp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import cn.edu.gdpt.topline172053zjp.R;
import cn.edu.gdpt.topline172053zjp.bean.NewsBean;

public class NewsDetailActivity extends AppCompatActivity {
    private WebView webView;
    private NewsBean bean;
    private String newsUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        initView();
        bean=(NewsBean) getIntent().getSerializableExtra("newsBean");
        if(bean==null){
            return;
        }
        initWebView();

    }

    private void initWebView() {
        webView.loadUrl(bean.getNewsUrl());
        WebSettings mWebSettings = webView.getSettings();
        mWebSettings.setSupportZoom(true);
        mWebSettings.setLoadWithOverviewMode(true);
        mWebSettings.setUseWideViewPort(true);
        mWebSettings.setDefaultTextEncodingName("GBK");//设置解码格式
        mWebSettings.setLoadsImagesAutomatically(true);
        mWebSettings.setJavaScriptEnabled(true);//支持js 特效
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });
    }

    private void initView() {
        webView=findViewById(R.id.webView);
    }
}
