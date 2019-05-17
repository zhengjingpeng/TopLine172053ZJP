package cn.edu.gdpt.topline172053zjp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

import cn.edu.gdpt.topline172053zjp.R;

public class JavaCountActivity extends AppCompatActivity {
    private LineChart chartTop;
    private BarChart chartBottom;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java_count);
        chartTop=findViewById(R.id.chart_top);
        chartBottom=findViewById(R.id.chart_bottom);
        //折线图
        //x
        final String[] years=new String[]{"应届生","1-2年","1-2年","1-2年","1-2年","1-2年","1-2年"};
        XAxis xAxis=chartTop.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(){
            @Override
            public String getFormattedValue(float value) {
                return years[(int) value];
            }
        });
        //y
        Float[] salary=new Float[]{6000F,13000F,20000F,26000F,35000F,50000F,100000F};
        ArrayList<Entry> yVals=new ArrayList<Entry>();
        for(int i=0;i<salary.length;i++){
            yVals.add(new Entry(i,salary[i]));
        }
        LineDataSet lineDataSet=new LineDataSet(yVals,"薪资");
        ArrayList<ILineDataSet> dataSets=new ArrayList<>();
        dataSets.add(lineDataSet);
        LineData data=new LineData(dataSets);
        chartTop.setData(data);
        //柱状图
        //y
        ArrayList<BarEntry> yBarVals=new ArrayList<BarEntry>();
        for(int i=0;i<salary.length;i++){
            yBarVals.add(new BarEntry(i,salary[i]));

        }
        BarDataSet barDataSet=new BarDataSet(yBarVals,"薪资");
        ArrayList<IBarDataSet> dataSets1 =new ArrayList<>();
        dataSets1.add(barDataSet);
        BarData barData=new BarData(dataSets1);
        chartBottom.setData(barData);
        //x
        XAxis xBarAxis=chartBottom.getXAxis();
        xBarAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xBarAxis.setValueFormatter(new IndexAxisValueFormatter(){
            @Override
            public String getFormattedValue(float value) {
                return years[(int) value];
            }
        });
    }

}
