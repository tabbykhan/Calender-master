package com.ajaygaikwad.calender2020.Fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.ajaygaikwad.calender2020.HealperClasses.Config;
import com.ajaygaikwad.calender2020.HealperClasses.MyApplication;
import com.ajaygaikwad.calender2020.R;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import hari.bounceview.BounceView;

/**
 * A simple {@link Fragment} subclass.
 */
public class CalenderFragment extends Fragment {

    Button btnPrev, btnNext;
    TextView tvDate;
    ImageView ivCal;

    int month;

    public static CalenderFragment newInstance() {
        CalenderFragment fragment = new CalenderFragment();
        return fragment;
    }

    public CalenderFragment() {
        // Required empty public constructor
    }

    SharedPreferences preferences;
    SharedPreferences.Editor editor;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        editor = preferences.edit();

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
        month = month + 1;

        monthSwitch(month);

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (month == 1) {

                } else {
                    month = month - 1;
                    monthSwitch(month);
                }

            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (month == 12) {
                } else {
                    month = month + 1;
                    monthSwitch(month);
                }

            }
        });

        getMessage();

        return v;
    }

    private void monthSwitch(int month) {

        switch (month) {
            case 1:
                ivCal.setImageResource(R.drawable.jan2);
                Animation slideDown = AnimationUtils.loadAnimation(CalenderFragment.this.getContext(), R.anim.fade_in);
                ivCal.setAnimation(slideDown);
                break;

            case 2:
                ivCal.setImageResource(R.drawable.feb2);
                Animation slideDown1 = AnimationUtils.loadAnimation(CalenderFragment.this.getContext(), R.anim.fade_in);
                ivCal.setAnimation(slideDown1);
                break;

            case 3:
                ivCal.setImageResource(R.drawable.march2);
                Animation slideDown2 = AnimationUtils.loadAnimation(CalenderFragment.this.getContext(), R.anim.fade_in);
                ivCal.setAnimation(slideDown2);
                break;

            case 4:
                ivCal.setImageResource(R.drawable.april2);
                Animation slideDown3 = AnimationUtils.loadAnimation(CalenderFragment.this.getContext(), R.anim.fade_in);
                ivCal.setAnimation(slideDown3);
                break;

            case 5:
                ivCal.setImageResource(R.drawable.may2);
                Animation slideDown4 = AnimationUtils.loadAnimation(CalenderFragment.this.getContext(), R.anim.fade_in);
                ivCal.setAnimation(slideDown4);
                break;

            case 6:
                ivCal.setImageResource(R.drawable.june2);
                Animation slideDown5 = AnimationUtils.loadAnimation(CalenderFragment.this.getContext(), R.anim.fade_in);
                ivCal.setAnimation(slideDown5);
                break;

            case 7:
                ivCal.setImageResource(R.drawable.july2);
                Animation slideDown6 = AnimationUtils.loadAnimation(CalenderFragment.this.getContext(), R.anim.fade_in);
                ivCal.setAnimation(slideDown6);
                break;

            case 8:
                ivCal.setImageResource(R.drawable.aug2);
                Animation slideDown7 = AnimationUtils.loadAnimation(CalenderFragment.this.getContext(), R.anim.fade_in);
                ivCal.setAnimation(slideDown7);
                break;

            case 9:
                ivCal.setImageResource(R.drawable.sep2);
                Animation slideDown8 = AnimationUtils.loadAnimation(CalenderFragment.this.getContext(), R.anim.fade_in);
                ivCal.setAnimation(slideDown8);
                break;

            case 10:
                ivCal.setImageResource(R.drawable.oct2);
                Animation slideDown9 = AnimationUtils.loadAnimation(CalenderFragment.this.getContext(), R.anim.fade_in);
                ivCal.setAnimation(slideDown9);
                break;

            case 11:
                ivCal.setImageResource(R.drawable.nov2);
                Animation slideDown10 = AnimationUtils.loadAnimation(CalenderFragment.this.getContext(), R.anim.fade_in);
                ivCal.setAnimation(slideDown10);
                break;

            case 12:
                ivCal.setImageResource(R.drawable.dec2);
                Animation slideDown11 = AnimationUtils.loadAnimation(CalenderFragment.this.getContext(), R.anim.fade_in);
                ivCal.setAnimation(slideDown11);
                break;

        }
    }


    private void getMessage() {

        StringRequest postreq = new StringRequest(Request.Method.POST, Config.GET_MESSAGE, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    //progressBar.dismiss();
                    JSONObject jsonObject = new JSONObject(response);

                    JSONObject obj = jsonObject.getJSONArray("project").getJSONObject(0);

                    String srno = obj.getString("srno");
                    String title = obj.getString("title");
                    String message = obj.getString("message");
                    String btn = obj.getString("btn");
                    String project = obj.getString("project");

                    AlertForMessage(srno, title, message, btn);

                } catch (Exception e1) {
                    //progressBar.dismiss();
                    //Toasty.error(getActivity(), "Error retrive Url").show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //progressBar.dismiss();
                //Toasty.error(getActivity(), "Error Connecting To Server").show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> map = new HashMap<String, String>();

                //map.put("sno", editsno.getText().toString());
                map.put("project", "calender");

                return map;
            }
        };
        MyApplication.getInstance().addToReqQueue(postreq);

    }

    private void AlertForMessage(final String srno, String title, String message, String btn) {

        if (btn.equalsIgnoreCase("1")) {

            if (!preferences.getString("messagesSatus", "").equals(srno)) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                builder.setTitle(title)
                        .setMessage(message)
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                editor.putString("messagesSatus", srno);
                                editor.commit();
                            }
                        });

                AlertDialog alert = builder.create();
                BounceView.addAnimTo(alert);
                alert.show();
            }

        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle(title)
                    .setMessage(message)
                    .setCancelable(false);

            AlertDialog alertDialog = builder.create();
            BounceView.addAnimTo(alertDialog);
            alertDialog.show();
        }
    }

}
