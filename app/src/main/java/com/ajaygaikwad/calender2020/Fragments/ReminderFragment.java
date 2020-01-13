package com.ajaygaikwad.calender2020.Fragments;


import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ajaygaikwad.calender2020.R;
import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReminderFragment extends Fragment {
    public static ReminderFragment newInstance() {
        ReminderFragment fragment = new ReminderFragment();
        return fragment;
    }

    public ReminderFragment() {
        // Required empty public constructor
    }

    CaldroidFragment caldroidFragment ;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_reminder, container, false);

        caldroidFragment = new CaldroidFragment();
        preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        editor = preferences.edit();


        InitCalendar(savedInstanceState);


    return  v;}

    private void InitCalendar(Bundle savedInstanceState) {
        if (savedInstanceState != null)
        {
            caldroidFragment.restoreStatesFromKey(savedInstanceState, "CALDROID_SAVED_STATE");
        }
        Bundle args = new Bundle();

        Calendar cal = Calendar.getInstance();

        args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
        args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
        args.putBoolean(CaldroidFragment.ENABLE_SWIPE, true);
        args.putBoolean(CaldroidFragment.SIX_WEEKS_IN_CALENDAR, true);
        caldroidFragment.setArguments(args);

        try {
            FragmentTransaction t = getFragmentManager().beginTransaction();
            t.replace(R.id.assignmentCalendar, caldroidFragment).disallowAddToBackStack();
            t.commit();
        }catch (Exception e){}
        // Setup listener
        final CaldroidListener listener = new CaldroidListener()
        {

            @Override
            public void onSelectDate(Date date, View view)
            {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String selectedDate = sdf.format(date);

               /* new SessionManager(getActivity()).setAssignmentDate(selectedDate);
                getFragmentManager().beginTransaction().addToBackStack("").replace(R.id.content_frame, new AssignmentDetailFragment()).commit();*/

               Bundle bundle = new Bundle();
               bundle.putString("selectedDate",selectedDate);

               Fragment fragment = new AfterNotesFragment();
               fragment.setArguments(bundle);

               FragmentTransaction ft = getFragmentManager().beginTransaction();
               ft.replace(R.id.frame, fragment).addToBackStack("").commit();


            }

            @Override
            public void onChangeMonth(int month, int year)
            {
                String text = "month: " + month + " year: " + year;
            }

            @Override
            public void onLongClickDate(Date date, View view)
            {
            }

            @Override
            public void onCaldroidViewCreated()
            {
                if (caldroidFragment.getLeftArrowButton() != null)
                {
                }
            }

        };

        // Setup Caldroid
        caldroidFragment.setCaldroidListener(listener);

    }

}
