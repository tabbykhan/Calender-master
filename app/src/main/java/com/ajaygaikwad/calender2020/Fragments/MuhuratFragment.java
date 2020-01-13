package com.ajaygaikwad.calender2020.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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
public class MuhuratFragment extends Fragment {

    Button btnPrev,btnNext;
    TextView tvDate;
    ImageView ivCal;
    int month;


    public static MuhuratFragment newInstance() {
        MuhuratFragment fragment = new MuhuratFragment();
        return fragment;
    }

    public MuhuratFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_muhurat, container, false);

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


        return v; }

    private void monthSwitch(int month) {

        switch (month){
            case 1 :
                ivCal.setImageResource(R.drawable.jan);
                Animation  slideDown = AnimationUtils.loadAnimation(MuhuratFragment.this.getContext(), R.anim.fade_in);
                ivCal.setAnimation(slideDown);
                break;

            case 2 :
                ivCal.setImageResource(R.drawable.feb);
                Animation slideDown12  = AnimationUtils.loadAnimation(MuhuratFragment.this.getContext(), R.anim.fade_in);
                ivCal.setAnimation(slideDown12);
                break;

            case 3 :
                ivCal.setImageResource(R.drawable.march);
                Animation slideDown2 = AnimationUtils.loadAnimation(MuhuratFragment.this.getContext(), R.anim.fade_in);
                ivCal.setAnimation(slideDown2);
                break;

            case 4 :
                ivCal.setImageResource(R.drawable.april);
                Animation slideDown3 = AnimationUtils.loadAnimation(MuhuratFragment.this.getContext(), R.anim.fade_in);
                ivCal.setAnimation(slideDown3);
                break;

            case 5 :
                ivCal.setImageResource(R.drawable.may);
                Animation slideDown4 = AnimationUtils.loadAnimation(MuhuratFragment.this.getContext(), R.anim.fade_in);
                ivCal.setAnimation(slideDown4);
                break;

            case 6 :
                ivCal.setImageResource(R.drawable.june);
                Animation slideDown5 = AnimationUtils.loadAnimation(MuhuratFragment.this.getContext(), R.anim.fade_in);
                ivCal.setAnimation(slideDown5);
                break;

            case 7 :
                ivCal.setImageResource(R.drawable.july);
                Animation  slideDown6 = AnimationUtils.loadAnimation(MuhuratFragment.this.getContext(), R.anim.fade_in);
                ivCal.setAnimation(slideDown6);
                break;

            case 8 :
                ivCal.setImageResource(R.drawable.aug);
                Animation  slideDown7 = AnimationUtils.loadAnimation(MuhuratFragment.this.getContext(), R.anim.fade_in);
                ivCal.setAnimation(slideDown7);
                break;

            case 9 :
                ivCal.setImageResource(R.drawable.sep);
                Animation  slideDown8 = AnimationUtils.loadAnimation(MuhuratFragment.this.getContext(), R.anim.fade_in);
                ivCal.setAnimation(slideDown8);
                break;

            case 10 :
                ivCal.setImageResource(R.drawable.oct);
                Animation slideDown9 = AnimationUtils.loadAnimation(MuhuratFragment.this.getContext(), R.anim.fade_in);
                ivCal.setAnimation(slideDown9);
                break;

            case 11 :
                ivCal.setImageResource(R.drawable.nov);
                Animation slideDown10 = AnimationUtils.loadAnimation(MuhuratFragment.this.getContext(), R.anim.fade_in);
                ivCal.setAnimation(slideDown10);
                break;

            case 12 :
                ivCal.setImageResource(R.drawable.dec);
                Animation  slideDown11 = AnimationUtils.loadAnimation(MuhuratFragment.this.getContext(), R.anim.fade_in);
                ivCal.setAnimation(slideDown11);
                break;

        }
    }

}
