package com.fullerton.project.blissfulmoms.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fullerton.project.blissfulmoms.AppointmentActivity;
import com.fullerton.project.blissfulmoms.R;
import com.fullerton.project.blissfulmoms.models.Appointment;
import com.fullerton.project.blissfulmoms.utils.SQLiteHelper;

import java.util.List;


public class AppointmentFragment extends Fragment implements View.OnClickListener {
    private FloatingActionButton fab;
    private RecyclerView rv;
    private List<Appointment> appointments;
    private Appointment userAppointment;
    private SQLiteHelper storageHelper;

    public AppointmentFragment() {
        // Required empty public constructorl
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.appointment_fragment, container, false);
        fab = (FloatingActionButton) view.findViewById(R.id.fab);
        rv = (RecyclerView) view.findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);

        fab.setOnClickListener(this);
        userAppointment = new Appointment();
        storageHelper = new SQLiteHelper(getContext());
        appointments = storageHelper.getAllUserAppointments(getActivity().getIntent().getExtras().getString("EMAIL"));
        AppointmentAdapter adp = new AppointmentAdapter(appointments);
        adp.notifyDataSetChanged();
        rv.setAdapter(adp);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                Intent intent = new Intent(getActivity(), AppointmentActivity.class);
                intent.putExtra("EMAIL", getActivity().getIntent().getExtras().getString("EMAIL"));
                startActivity(intent);
                break;
        }
    }
}