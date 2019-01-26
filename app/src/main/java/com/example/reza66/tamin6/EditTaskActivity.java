package com.example.reza66.tamin6;

import android.content.Context;
import android.content.Intent;

import java.util.UUID;

public class EditTaskActivity extends SingleFragmentActivity {
    private static final String EXTRA_TASK_ID ="com.example.reza66.tamin6.task_id" ;

    public static Intent newIntent(Context context, UUID crimeId){
        Intent intent =new Intent(context,EditTaskActivity.class );

        intent.putExtra(EXTRA_TASK_ID,crimeId);
        return  intent;
    }

    @Override
    public EditFragment createFragment() {
        UUID taskId = (UUID) getIntent().getSerializableExtra(EXTRA_TASK_ID);
        return EditFragment.newInstance(taskId);
    }
}
