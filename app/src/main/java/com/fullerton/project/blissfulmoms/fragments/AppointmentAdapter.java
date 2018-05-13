package com.fullerton.project.blissfulmoms.fragments;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fullerton.project.blissfulmoms.R;
import com.fullerton.project.blissfulmoms.models.Appointment;

import java.util.List;

/**
 * Created by Saranya A on 5/7/2017.
 */

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.AppointmentViewHolder> {

    List<Appointment> appointments;

    AppointmentAdapter(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public AppointmentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.appointment_items, parent, false);
        AppointmentViewHolder pvh = new AppointmentViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(AppointmentViewHolder holder, int position) {
        holder.appointmentDesc.setText(appointments.get(position).getAppointmentDesc());
        holder.appointmentDateTime.setText(appointments.get(position).getAppointmentDate() + " " + appointments.get(position).getAppointmentTime());
        holder.appointmentPhoto.setImageResource(R.drawable.ic_action_appointment);
    }

    @Override
    public int getItemCount() {
        return appointments.size();
    }

    public static class AppointmentViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        ImageView appointmentPhoto;
        TextView appointmentDesc;
        TextView appointmentDateTime;

        AppointmentViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            appointmentDesc = (TextView) itemView.findViewById(R.id.textAppoinmentDesc);
            appointmentDateTime = (TextView) itemView.findViewById(R.id.textAppoinmentDetails);
            appointmentPhoto = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }

}
