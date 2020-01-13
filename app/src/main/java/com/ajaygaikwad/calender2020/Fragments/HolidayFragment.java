package com.ajaygaikwad.calender2020.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ajaygaikwad.calender2020.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class HolidayFragment extends Fragment {

    Button btnPrev,btnNext;
    TextView tvDate;
    ImageView ivCal;
    int month;

    public static HolidayFragment newInstance() {
        HolidayFragment fragment = new HolidayFragment();
        return fragment;
    }
    public HolidayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_holiday, container, false);

        tvDate = v.findViewById(R.id.tvDate);
        btnNext = v.findViewById(R.id.btnNext);
        btnPrev = v.findViewById(R.id.btnPrev);
        ivCal = v.findViewById(R.id.ivCal);

        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formatedDate = df.format(date);
        tvDate.setText(formatedDate);

        Calendar cal = Calendar.getInstance();
        month = cal.get(Calendar.MONTH);
        month = month+1;

        monthSwitch(month);

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(month==1){

                }else{
                    month = month - 1;
                    monthSwitch(month);
                }

            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(month==12){}
                else{
                    month = month + 1;
                    monthSwitch(month);
                }

            }
        });


        return v;}

    private void monthSwitch(int month) {

        switch (month){
            case 1 :
                ivCal.setImageResource(R.drawable.january_holi);
                break;

            case 2 :
                ivCal.setImageResource(R.drawable.february_holi);
                break;

            case 3 :
                ivCal.setImageResource(R.drawable.march_holi);
                break;

            case 4 :
                ivCal.setImageResource(R.drawable.april_holi);
                break;

            case 5 :
                ivCal.setImageResource(R.drawable.may_holi);
                break;

            case 6 :
                ivCal.setImageResource(R.drawable.june_holi);
                break;

            case 7 :
                ivCal.setImageResource(R.drawable.july_holi);
                break;

            case 8 :
                ivCal.setImageResource(R.drawable.august_holi);
                break;

            case 9 :
                ivCal.setImageResource(R.drawable.sepoct_holi);
                break;

            case 10 :
                ivCal.setImageResource(R.drawable.octholi);
                break;

            case 11 :
                ivCal.setImageResource(R.drawable.nov_holi);
                break;

            case 12 :
                ivCal.setImageResource(R.drawable.dec_holi);
                break;

        }
    }

}
