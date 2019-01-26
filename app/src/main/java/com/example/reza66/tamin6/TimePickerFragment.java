package com.example.reza66.tamin6;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TimePicker;

import java.sql.Time;

/**
 * Created by www.NooR26.com on 1/12/2019.
 */

public class TimePickerFragment extends DialogFragment {
    private static final String ARG_TIME_HOUR = "time_hour";
    public static final String EXTRA_TIME_HOUR = "com.example.amin.criminalintent.time_hour";
    public static final String EXTRA_TIME_MINUTE = "com.example.amin.criminalintent.time_minute";
    private static final String ARG_TIME_MINUTE ="time_minute" ;


    private TimePicker mTimePicker;

    private Time mTime;
    private int hour;
    private  int minute;

    public static TimePickerFragment newInstance(int hour,int minute) {

        Bundle args = new Bundle();
        args.putInt(ARG_TIME_HOUR, hour);
        args.putInt(ARG_TIME_MINUTE,minute);
        TimePickerFragment fragment = new TimePickerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public TimePickerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        hour= getArguments().getInt(ARG_TIME_HOUR);
        minute=getArguments().getInt(ARG_TIME_MINUTE);
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_time_picker, null);

        mTimePicker = view.findViewById(R.id.dialog_time_picker);
        mTimePicker.setHour(hour);
        mTimePicker.setMinute(minute);

        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .setTitle(R.string.date_picker_title)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // SimpleDateFormat format =new SimpleDateFormat("HH:mm:ss");
                        // String formated= format.format(mTimePicker);
                        int hour=mTimePicker.getHour();
                        int minute=mTimePicker.getMinute();
                        sendResult(hour,minute);
                    }
                })
                .create();
    }

    private void sendResult(int hour,int minute) {
        Intent intent = new Intent();
        intent.putExtra(EXTRA_TIME_HOUR, hour);
        intent.putExtra(EXTRA_TIME_MINUTE,minute);
        getTargetFragment().
                onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, intent);

        getTargetFragment().onResume();
    }

}

