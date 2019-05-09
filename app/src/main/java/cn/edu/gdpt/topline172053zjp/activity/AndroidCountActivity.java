package cn.edu.gdpt.topline172053zjp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;

import cn.edu.gdpt.topline172053zjp.R;

public class AndroidCountActivity extends AppCompatActivity {
private PieChart chart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_count);
        chart=findViewById(R.id.chart);
        ArrayList<PieEntry> entries=new ArrayList<>();
        entries.add(new PieEntry(4,"月薪8-15k"));
        entries.add(new PieEntry(3,"月薪15-20k"));
        entries.add(new PieEntry(2,"月薪20-30k"));
        entries.add(new PieEntry(1,"月薪30k"));
        PieDataSet dataSet=new PieDataSet(entries,"月薪");
        dataSet.setColors(new int[]{R.color.color_green,R.color.color_violet,R.color.color_blue,R.color.color_orange},getApplicationContext());
        PieData pieData=new PieData(dataSet);
        chart.setData(pieData);
        chart.setHoleRadius(0f);
        chart.setTransparentCircleRadius(0f);
        dataSet.setDrawValues(false);
        chart.setEntryLabelTextSize(16f);
    }
}
