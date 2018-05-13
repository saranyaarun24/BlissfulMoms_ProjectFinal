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
import com.fitbit.api.models.ActivitiesStepsItem;
import com.fitbit.api.models.StepsV1;
import com.fitbit.api.services.SleepService;
import com.fitbit.api.services.StepsService;
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
import java.util.Date;
import java.util.List;

public class StepsLogActivity extends FragmentActivity implements LoaderManager.LoaderCallbacks<ResourceLoaderResult<StepsV1>> {
/*This is the code used to track the steps walked by the user and the data collected is presented in the form of a bar chart*/
/*The code is written to display data for 7 days and as the number of steps */
    private BarChart mChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walklog);
        mChart = (BarChart) findViewById(R.id.activity_walklog_xml);
        mChart.setMaxVisibleValueCount(40);
    }

    public void setData(int count) {

    }

    @Override
    public Loader<ResourceLoaderResult<StepsV1>> onCreateLoader(int i, Bundle bundle) {
        Calendar calendar = Calendar.getInstance();
        return StepsService.getStepsLogLoader(this, calendar.getTime(), 0, 7);
//        Date startDate = calendar.getTime();
//        calendar.add(Calendar.DATE, -7);
//        return SleepService.getSleepLoader(this, startDate,calendar.getTime());
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onLoadFinished(Loader<ResourceLoaderResult<StepsV1>> loader, ResourceLoaderResult<StepsV1> heartLogsResourceLoaderResult) {
        bindWeightLogs(heartLogsResourceLoaderResult.getResult());

    }

    @Override
    public void onLoaderReset(Loader<ResourceLoaderResult<StepsV1>> loader) {

    }


    private String convertMMddyyyyToyyyyMMdd(String originalString) {
        StringBuilder dateBuilder = new StringBuilder();
        dateBuilder = dateBuilder.append(originalString.substring(5));
        return dateBuilder.toString();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void bindWeightLogs(StepsV1 response) {
        ArrayList<BarEntry> yValues = new ArrayList<>();
        List<ActivitiesStepsItem> stepsItems = response.getActivitiesSteps();
        ArrayList<String> timeFormat = new ArrayList<>();
        for (int i = 0; i < stepsItems.size(); i++) {
            timeFormat.add(convertMMddyyyyToyyyyMMdd(stepsItems.get(i).getDateTime()));
        }

        XAxis xAxis = mChart.getXAxis();
        xAxis.setValueFormatter(new MyXAxisValueFormatter(timeFormat.toArray(new String[0])));
        xAxis.setGranularity(1);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);


        int count = 7;

        for (int i=0; i<stepsItems.size(); i++) {
            float val1 = Float.parseFloat(stepsItems.get(i).getValue());
            yValues.add(new BarEntry(i, new float[]{val1}));
        }

        BarDataSet set1;
        set1 = new BarDataSet(yValues, "Steps Stats");
        set1.setDrawIcons(false);
        set1.setStackLabels(new String[]{"Steps Per day"});
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
