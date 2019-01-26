package com.example.reza66.tamin6;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.reza66.tamin6.models.Task;
import com.example.reza66.tamin6.models.TaskBank;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;


/**
 * A simple {@link Fragment} subclass.
 */
public class InputFragment extends Fragment {

    private static final String ARG_TASK_ID ="taskId" ;
    private static final String DIALOG_TAG = "DialogDate";
    private static final String DIALOG_TAG1 = "DialogTime";
    private static final int REQ_DATE_PICKER = 0;
    private static final int REQ_TIME_PICKER =1 ;

    private EditText mTitleField;
    private EditText mdescription;
    private Button mDateButton;
    private Button mTimeButton;
    //private CheckBox mSolvedCheckBox;
    private Task mTask;
    private Button mSaveButton;

    private Calendar calendar;
    private int year;
    private int month ;
    private int day ;

    public static boolean mTaskIsEnter;


    public static InputFragment newInstance(UUID taskId) {
        
        Bundle args = new Bundle();
        args.putSerializable(ARG_TASK_ID,taskId);
        InputFragment fragment = new InputFragment();
        fragment.setArguments(args);
        return fragment;
    }
    public InputFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID crimeId = (UUID)  getArguments().getSerializable(ARG_TASK_ID);
        mTask = TaskBank.getInstance().getTask(crimeId);
        mTaskIsEnter=false;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_input, container, false);
        mTitleField =view.findViewById(R.id.task_title);
        mDateButton = view.findViewById(R.id.task_date);
        mdescription=view.findViewById(R.id.task_description);
        mTimeButton = view.findViewById(R.id.task_time);
        //mSolvedCheckBox = view.findViewById(R.id.task_done);
        mSaveButton = view.findViewById(R.id.save_button);

        Calendar calendar = getCalendarDate();

        final int hour= calendar.get(Calendar.HOUR_OF_DAY);//mTimePicker.getHour();
        final int minute=calendar.get(Calendar.MINUTE);//mTimePicker.getMinute();

        mTitleField.setText(mTask.getTitle());
        mdescription.setText(mTask.getDescription());

        if(mTitleField.getText().length()==0)
            mSaveButton.setEnabled(false);

        mDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerFragment datePickerFragment = DatePickerFragment.newInstance(mTask.getDate());
                datePickerFragment.setTargetFragment(InputFragment.this,
                        REQ_DATE_PICKER);
                datePickerFragment.show(getFragmentManager(), DIALOG_TAG);
            }
        });
        mTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerFragment timePickerFragment = TimePickerFragment.newInstance(hour,minute);
                timePickerFragment.setTargetFragment(InputFragment.this,REQ_TIME_PICKER);
                timePickerFragment.show(getFragmentManager(),DIALOG_TAG1);
            }
        });

        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mTask.setTitle(charSequence.toString());
                mSaveButton.setEnabled(true);
                if (mTitleField.getText().length() == 0) {
                    mSaveButton.setEnabled(false);
                    Toast.makeText(getActivity(), "Please enter task name,", Toast.LENGTH_SHORT).show();}
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });



        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    mTask.setTitle(mTitleField.getText().toString());
                    mTask.setDescription(mdescription.getText().toString());
//                    mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                        @Override
//                        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                            mTask.setSolved(b);
//                        }
//                    });
                    Toast.makeText(getActivity(), "the information is save", Toast.LENGTH_SHORT).show();
                    mTaskIsEnter=true;
            }
        });

        return view;
    }
    private Calendar getCalendarDate() {
        calendar = Calendar.getInstance();
        calendar.setTime(mTask.getDate());

        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        return calendar;
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != Activity.RESULT_OK)
            return;

        if (requestCode == REQ_DATE_PICKER) {
            Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);

            mTask.setDate(date);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String currentDateandTime = sdf.format(mTask.getDate());
            mDateButton.setText(currentDateandTime);
        }
        if (requestCode == REQ_TIME_PICKER) {
            int hour =  data.getIntExtra(TimePickerFragment.EXTRA_TIME_HOUR,0);
            int minute = data.getIntExtra(TimePickerFragment.EXTRA_TIME_MINUTE,0);
//            Calendar calendar = Calendar.getInstance();
            getCalendarDate();
//            calendar.set(Calendar.HOUR_OF_DAY,hour);
//            calendar.set(Calendar.MINUTE,minute);
            calendar.set(year,month,day,hour,minute);
            mTask.setDate(calendar.getTime());

            mTimeButton.setText(hour+": "+minute);
        }
    }

}
