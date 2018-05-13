package com.fullerton.project.blissfulmoms;

import android.app.LoaderManager;
import android.content.Loader;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentActivity;

import com.fitbit.api.loaders.ResourceLoaderResult;
import com.fitbit.api.models.ActivitiesHeartItem;
import com.fitbit.api.models.HeartLogResponse;
import com.fitbit.api.services.HeartRateService;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HeartLogActivity extends FragmentActivity implements LoaderManager.LoaderCallbacks<ResourceLoaderResult<HeartLogResponse>> {
/*This is the code used to track the heart beat rate of the user and the data collected is presented in the form of a stacked bar chart*/
/*The code is written to display data for 7 days and as Normal, Fatburn, Cardio, Peak */
    private BarChart mChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heartlog);
        mChart = (BarChart) findViewById(R.id.activity_heartlog_xml);
        mChart.setMaxVisibleValueCount(40);
    }

    public void setData(int count) {

    }

    @Override
    public Loader<ResourceLoaderResult<HeartLogResponse>> onCreateLoader(int i, Bundle bundle) {
        Calendar calendar = Calendar.getInstance();
        return HeartRateService.getHeartRaterLoader(this, calendar.getTime(), Calendar.DAY_OF_WEEK, 7);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onLoadFinished(Loader<ResourceLoaderResult<HeartLogResponse>> loader, ResourceLoaderResult<HeartLogResponse> heartLogsResourceLoaderResult) {
        bindWeightLogs(heartLogsResourceLoaderResult.getResult());

    }

    @Override
    public void onLoaderReset(Loader<ResourceLoaderResult<HeartLogResponse>> loader) {

    }


    private String convertMMddyyyyToyyyyMMdd(String originalString) {
        StringBuilder dateBuilder = new StringBuilder();
        dateBuilder = dateBuilder.append(originalString.substring(5));
        return dateBuilder.toString();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void bindWeightLogs(HeartLogResponse response) {

        ArrayList<BarEntry> yValues = new ArrayList<>();
        List<ActivitiesHeartItem> heartItems = response.getActivitiesHeart();
        ArrayList<String> timeFormat = new ArrayList<>();
        for (int i = 0; i < heartItems.size(); i++) {
            timeFormat.add(convertMMddyyyyToyyyyMMdd(heartItems.get(i).getDateTime()));
        }

        XAxis xAxis = mChart.getXAxis();
        xAxis.setValueFormatter(new MyXAxisValueFormatter(timeFormat.toArray(new String[0])));
        xAxis.setGranularity(1);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);


        int count = 7;

        for (int i=0; i<heartItems.size(); i++) {
            float val1 = heartItems.get(i).getValue().getHeartRateZones().get(0).getMinutes();
            float val2 = heartItems.get(i).getValue().getHeartRateZones().get(1).getMinutes();
            float val3 = heartItems.get(i).getValue().getHeartRateZones().get(2).getMinutes();
            float val4 = heartItems.get(i).getValue().getHeartRateZones().get(3).getMinutes();
            yValues.add(new BarEntry(i, new float[]{val1, val2, val3,val4}));
        }

        BarDataSet set1;
        set1 = new BarDataSet(yValues, "Heart Rate Stats");
        set1.setDrawIcons(false);
        set1.setColors(new int[]{ColorTemplate.rgb("#8BC34A"), ColorTemplate.rgb("#FFC107"), ColorTemplate.rgb("#9E9E9E"), ColorTemplate.rgb("#9E8E8E")});
        set1.setStackLabels(new String[]{"Normal","Fat Burn","Cardio","Peak"});
        //set1.setColors(getColors());
        set1.setValueTextSize(10f);
        set1.setValueTextColor(Color.BLACK);

        BarData data = new BarData(set1);
        mChart.setData(data);
        mChart.setFitBars(true);
        mChart.getDescription().setEnabled(false);
        mChart.invalidate();
    }

    private int[] getColors() {
        int stacksize = 3;
        int[] colors = new int[stacksize];
        for (int i = 0; i < colors.length; i++) {
            colors[i] = ColorTemplate.MATERIAL_COLORS[i];
        }
        return colors;
    }

    @Override
    public void onResume() {
        super.onResume();
        getLoaderManager().initLoader(5, null, this).forceLoad();

    }


    public class MyXAxisValueFormatter implements IAxisValueFormatter {

        private String[] mValues;

        public MyXAxisValueFormatter(String[] values) {
            this.mValues = values;
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            return mValues[(int) value];
        }
    }
}
