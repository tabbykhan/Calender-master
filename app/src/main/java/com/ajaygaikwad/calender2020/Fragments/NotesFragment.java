package com.ajaygaikwad.calender2020.Fragments;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.database.sqlite.SQLiteConstraintException;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.ajaygaikwad.calender2020.Adapter.AdapterNotes;
import com.ajaygaikwad.calender2020.Pojo.NotesDBpojo;
import com.ajaygaikwad.calender2020.R;
import com.ajaygaikwad.calender2020.db.AppDatabase;
import com.ajaygaikwad.calender2020.db.NotesDAO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import hari.bounceview.BounceView;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotesFragment extends Fragment {


    Button btnAddNotes;
    RecyclerView recycleView;
    String dateSelected;
    private DatePickerDialog.OnDateSetListener dateSetListener;
    NotesDAO notesDAO;
    List<String> getNotes;

    AdapterNotes adapterNotes;

    public static NotesFragment newInstance() {
        NotesFragment fragment = new NotesFragment();
        return fragment;
    }
    public NotesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=  inflater.inflate(R.layout.fragment_notes, container, false);
        btnAddNotes=v.findViewById(R.id.btnAddNotes);
        btnAddNotes=v.findViewById(R.id.btnAddNotes);
        recycleView=v.findViewById(R.id.recycleView);
        getNotes = new ArrayList<>();

        notesDAO = Room.databaseBuilder(getActivity(), AppDatabase.class, "db-notes")
                .allowMainThreadQueries()
                .build()
                .getNotesDAO();

        getNotes = notesDAO.getNotes();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recycleView.setLayoutManager(layoutManager);

        btnAddNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                popUpMenu();
            }
        });



  return v;
    }


    private void popUpMenu() {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
// ...Irrelevant code for customizing the buttons and title
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.add_notes, null);
        dialogBuilder.setView(dialogView);
        final EditText etNote = dialogView.findViewById(R.id.etNote);
        final Button btnDate = dialogView.findViewById(R.id.btnDate);
        final Button btnSave = dialogView.findViewById(R.id.btnSave);

        Calendar cal = Calendar.getInstance();
        final int year = cal.get(Calendar.YEAR);
        final int month = cal.get(Calendar.MONTH);
        final int day =cal.get(Calendar.DAY_OF_MONTH);

        //dateSelected = day+"/"+month+1+"/"+year;
        int mon = month+1;
        dateSelected = day+"/"+mon+"/"+year;
        btnDate.setText(dateSelected);

        final AlertDialog alertDialog = dialogBuilder.create();
        BounceView.addAnimTo(alertDialog);
        alertDialog.show();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NotesDBpojo notesDBpojo = new NotesDBpojo();
                notesDBpojo.setNotes(etNote.getText().toString());
                notesDBpojo.setDate(dateSelected);

                try{
                    notesDAO.insert(notesDBpojo);
                    getActivity().setResult(RESULT_OK);
                }catch (SQLiteConstraintException e){
                    Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }

                alertDialog.dismiss();

            }
        });


        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),dateSetListener,year,month,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                datePickerDialog.show();

            }

        });
        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                month = month+1;
                dateSelected = dayOfMonth+"/"+month+"/"+year;
                btnDate.setText(dateSelected);
            }
        };



    }

}
