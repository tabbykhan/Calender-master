package com.ajaygaikwad.calender2020.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ajaygaikwad.calender2020.R;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class AfterNotesFragment extends Fragment {


    public AfterNotesFragment() {
        // Required empty public constructor
    }

    Button btnSave,btnTime,btnDate;
    EditText etTitle;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_after_notes, container, false);

        btnSave = v.findViewById(R.id.btnSave);
        btnTime = v.findViewById(R.id.btnTime);
        btnDate = v.findViewById(R.id.btnDate);
        etTitle = v.findViewById(R.id.etTitle);

        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        String currentDate = df.format(new Date());
        String currentTime = new SimpleDateFormat("hh:mm a", Locale.getDefault()).format(new Date());
        btnTime.setText(currentTime);
        btnDate.setText(currentDate);

        return  v;}

}
