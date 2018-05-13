package com.fullerton.project.blissfulmoms;

import android.Manifest;
import android.app.Activity;
import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.support.v13.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;


import com.fitbit.api.loaders.ResourceLoaderResult;
import com.fitbit.api.models.Weight;
import com.fitbit.api.models.WeightLogs;
import com.fitbit.api.services.WeightService;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Image;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class WeightLogActivity extends FragmentActivity implements LoaderManager.LoaderCallbacks<ResourceLoaderResult<WeightLogs>> {
/*This is the code used to track the weight lost/gained by the user and the collected data is presented in the form of a line graph*/
/*The code is written to extract and display data for the days the weight is recorded on Fitbit and the
data is provided in terms of kilograms */
    private LineChart mChart;
    private Button sendReport;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        StrictMode.setThreadPolicy(policy);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weightlog);
        verifyStoragePermissions(this);
        mChart = (LineChart) findViewById(R.id.chart);
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(false);
        getLoaderManager().initLoader(3, null, this).forceLoad();

        sendReport = (Button) findViewById(R.id.send_report);
        sendReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File file = null;
                String root = Environment.getExternalStorageDirectory().toString();
                File myDir = new File(getApplicationContext().getFilesDir() + "/saved_images");
                if (!myDir.exists()) {
                    myDir.mkdirs();
                }

                file = new File(myDir, "weightlog.jpg");
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                FileOutputStream fOut = null;
                try {
                    fOut = new FileOutputStream(file);
                    Bitmap bitmap = mChart.getChartBitmap();
                    mChart.saveToGallery("test", 50);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
                    fOut.flush();
                    fOut.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setType("image/jpeg");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"saranya.arun24@gmail.com"});
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "WEIGHT LOG");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Please find my current weight log attached !");
//                emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://" + file.getAbsolutePath()));
                startActivity(emailIntent);
            }
        });
    }

    @Override
    public Loader<ResourceLoaderResult<WeightLogs>> onCreateLoader(int i, Bundle bundle) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        return WeightService.getWeightLogLoader(this, calendar.getTime(), Calendar.MONTH, 1);
    }

    @Override
    public void onLoadFinished(Loader<ResourceLoaderResult<WeightLogs>> loader, ResourceLoaderResult<WeightLogs> weightLogsResourceLoaderResult) {
        try {
            bindWeightLogs(weightLogsResourceLoaderResult.getResult());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLoaderReset(Loader<ResourceLoaderResult<WeightLogs>> loader) {

    }

    public void bindWeightLogs(WeightLogs weightLogs) throws IOException {
        mChart.getDescription().setEnabled(false);
        ArrayList<Entry> entries = new ArrayList<>();
        int i = 0;
        for (Weight data : weightLogs.getWeight()) {
            entries.add(new Entry(i++, data.getWeight().floatValue()));
        }

        LineDataSet lse1 = new LineDataSet(entries, "Weight Logs");
        lse1.setFillAlpha(110);
        lse1.setColor(Color.RED);
        lse1.setLineWidth(2);
        lse1.setValueTextSize(10f);
        lse1.setValueTextColor(Color.BLACK);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lse1);

        LineData data = new LineData(dataSets);
        mChart.setData(data);


        ArrayList<String> labels = new ArrayList<String>();
        for (Weight localData : weightLogs.getWeight()) {
            labels.add(localData.getDate());
        }

        XAxis xAxis = mChart.getXAxis();
        xAxis.setValueFormatter((new MyXAxisValueFormatter(labels.toArray(new String[0]))));
        xAxis.setGranularity(1);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
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

    @Override
    public void onResume() {
        super.onResume();
        getLoaderManager().initLoader(3, null, this).forceLoad();

    }

    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }
}