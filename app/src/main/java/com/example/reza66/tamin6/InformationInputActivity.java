package com.example.reza66.tamin6;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.util.UUID;

public class InformationInputActivity extends SingleFragmentActivity {
    private static final String EXTRA_TASK_ID ="com.example.reza66.tamin6.task_id" ;

    public static Intent newIntent(Context context, UUID crimeId){
        Intent intent =new Intent(context,InformationInputActivity.class );

        intent.putExtra(EXTRA_TASK_ID,crimeId);
        return  intent;
    }

    @Override
    public InputFragment createFragment() {
        UUID taskId = (UUID) getIntent().getSerializableExtra(EXTRA_TASK_ID);
        return InputFragment.newInstance(taskId);
    }
    @Override
    public void onBackPressed() {
        if(InputFragment.mTaskIsEnter)
           super.onBackPressed();
        else
           Toast.makeText(InformationInputActivity.this, R.string.not_nuul_mesege, Toast.LENGTH_LONG).show();

    }

}
