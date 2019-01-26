package com.example.reza66.tamin6;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.reza66.tamin6.models.Task;
import com.example.reza66.tamin6.models.TaskBank;

import java.text.SimpleDateFormat;
import java.util.UUID;


/**
 * A simple {@link Fragment} subclass.
 */
public class EditFragment extends Fragment {

    private static final String ARG_TASK_ID ="taskId" ;
    private TextView mTitleText;
    private TextView mDescriptionText;
    private TextView mDateText;
    private TextView mTimeText;
    private Button mEditButton;
    private Button mDeleteButton;
    private Button mDoneButton;
    private Task mTask;




    public static EditFragment newInstance(UUID taskId) {

        Bundle args = new Bundle();
        args.putSerializable(ARG_TASK_ID,taskId);
        EditFragment fragment = new EditFragment();
        fragment.setArguments(args);
        return fragment;
    }
    public EditFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID crimeId = (UUID)  getArguments().getSerializable(ARG_TASK_ID);
        mTask = TaskBank.getInstance().getTask(crimeId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_edit, container, false);
        mTitleText=view.findViewById(R.id.task_title_text);
        mDescriptionText=view.findViewById(R.id.descrip_text);
        mDateText=view.findViewById(R.id.date_text);
        mTimeText = view.findViewById(R.id.time_text);
        mEditButton= view.findViewById(R.id.edit_button);
        mDeleteButton = view.findViewById(R.id.delete_button);
        mDoneButton = view.findViewById(R.id.done_button);


        updateUI();

        mEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=InformationInputActivity.newIntent(getActivity(),mTask.getId());
                startActivity(intent);
            }
        });
        mDoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTask.setDone(true);
                Toast.makeText(getActivity(), "your Task is Done" , Toast.LENGTH_LONG).show();
            }
        });
        mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TaskBank.getInstance().deleteTask(mTask);
                Toast.makeText(getActivity(), "your Task is Delete" , Toast.LENGTH_SHORT).show();
                getActivity().finish();
//                Intent intent=AllTaskActivity.newIntent(getActivity());
//                startActivity(intent);
            }
        });

        return view;
    }

    private void updateUI() {
        mTitleText.setText(mTask.getTitle().toString());
        mDescriptionText.setText((CharSequence) mTask.getDescription());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = sdf.format(mTask.getDate());
        SimpleDateFormat sdf1 = new SimpleDateFormat("HH-mm");
        String currentTime = sdf1.format(mTask.getDate());

        // mDateButton.setText(""+month+"/"+day+"/"+year);
        mDateText.setText(currentDate);
        //mTimeButton.setText(hour +" : "+minute);
        mTimeText.setText(currentTime);
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }
}
