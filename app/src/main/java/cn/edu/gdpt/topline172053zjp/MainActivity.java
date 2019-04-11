package cn.edu.gdpt.topline172053zjp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.edu.gdpt.topline172053zjp.fragment.CountFragment;
import cn.edu.gdpt.topline172053zjp.fragment.HomeFragment;
import cn.edu.gdpt.topline172053zjp.fragment.MeFragment;
import cn.edu.gdpt.topline172053zjp.fragment.VideoFragment;

public class MainActivity extends AppCompatActivity {

    private TextView mTvMainTitle;
    private ViewPager mVp;
    private RadioGroup mMainRg;
    private List<Fragment> allfargment=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initViewPager();
        initLisen();
        mMainRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.main_rb_shouye:
                        mVp.setCurrentItem(0,false);
                        break;
                    case R.id.main_rb_tongji:
                        mVp.setCurrentItem(1,false);
                        break;
                    case R.id.main_rb_video:
                        mVp.setCurrentItem(2,false);
                        break;
                    case R.id.main_rb_me:
                        mVp.setCurrentItem(3,false);
                        break;
                }
            }
        });
    }

    private void initLisen() {
        mVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                switch (i){
                    case 0:
                        mTvMainTitle.setText("首页");
                        mMainRg.check(R.id.main_rb_shouye);
                        break;
                    case 1:
                        mTvMainTitle.setText("统计");
                        mMainRg.check(R.id.main_rb_tongji);
                        break;
                    case 2:
                        mTvMainTitle.setText("视频");
                        mMainRg.check(R.id.main_rb_video);
                        break;
                    case 3:
                        mTvMainTitle.setText("我");
                        mMainRg.check(R.id.main_rb_me);
                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    private void initViewPager() {
        allfargment.add(new HomeFragment());
        allfargment.add(new CountFragment());
        allfargment.add(new VideoFragment());
        allfargment.add(new MeFragment());
        mVp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return allfargment.get(i);
            }

            @Override
            public int getCount() {
                return allfargment.size();
            }
        });
    }

    private void initView() {
        mTvMainTitle = (TextView) findViewById(R.id.tv_main_title);
        mVp = (ViewPager) findViewById(R.id.vp);
        mMainRg = (RadioGroup) findViewById(R.id.main_rg);
    }
}
