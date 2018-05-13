package com.fullerton.project.blissfulmoms.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.fullerton.project.blissfulmoms.HeartLogActivity;
import com.fullerton.project.blissfulmoms.R;
import com.fullerton.project.blissfulmoms.SleepLogActivity;
import com.fullerton.project.blissfulmoms.StepsLogActivity;
import com.fullerton.project.blissfulmoms.WeightLogActivity;


public class HealthFragment extends Fragment implements View.OnClickListener {


    public HealthFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View healthView = inflater.inflate(R.layout.health_fragment, container, false);
        ImageView viewById = (ImageView) healthView.findViewById(R.id.imageViewReport);
        ImageView heartById = (ImageView) healthView.findViewById(R.id.imageViewHeart);
        ImageView walkById = (ImageView) healthView.findViewById(R.id.imageViewWalking);
        ImageView sleepById = (ImageView) healthView.findViewById(R.id.imageViewSleep);

        heartById.setOnClickListener(this);
        viewById.setOnClickListener(this);
        walkById.setOnClickListener(this);
        sleepById.setOnClickListener(this);
        return healthView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageViewReport:
                Intent intent = new Intent(getActivity(), WeightLogActivity.class);
                startActivity(intent);
                break;
            case R.id.imageViewHeart:
                Intent intent1 = new Intent(getActivity(), HeartLogActivity.class);
                startActivity(intent1);
                break;
            case R.id.imageViewWalking:
                Intent intent2 = new Intent(getActivity(), StepsLogActivity.class);
                startActivity(intent2);
                break;
            case R.id.imageViewSleep:
                Intent intent3 = new Intent(getActivity(), SleepLogActivity.class);
                startActivity(intent3);
                break;
        }
    }
}
