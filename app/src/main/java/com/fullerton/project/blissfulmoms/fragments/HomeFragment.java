package com.fullerton.project.blissfulmoms.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fullerton.project.blissfulmoms.R;
import com.fullerton.project.blissfulmoms.models.User;
import com.fullerton.project.blissfulmoms.utils.SQLiteHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class HomeFragment extends Fragment {

    private AppCompatTextView textViewDueDate;
    private SQLiteHelper storageHelper;


    public HomeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View homeFragment = inflater.inflate(R.layout.home_fragment, container, false);
        storageHelper = new SQLiteHelper(getActivity());
        User user = storageHelper.getUser(getActivity().getIntent().getExtras().getString("EMAIL"));
        textViewDueDate = (AppCompatTextView) homeFragment.findViewById(R.id.textViewDueDate);

        SimpleDateFormat dates = new SimpleDateFormat("MM/dd/yyyy");
        Calendar c = Calendar.getInstance();
        String formattedDate = dates.format(c.getTime());

        Date date1 = new Date();
        Date date2 = new Date();


        try {
            date1 = dates.parse(formattedDate);
            date2 = dates.parse(user.getDueDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }


        long difference = Math.abs(date1.getTime() - date2.getTime());
        long differenceDates = difference / (24 * 60 * 60 * 1000);

        //Convert long to String
        String dayDifference = Long.toString(differenceDates);
        textViewDueDate.setText(dayDifference + " " + " more days to go !!!");

        return homeFragment;
    }
}