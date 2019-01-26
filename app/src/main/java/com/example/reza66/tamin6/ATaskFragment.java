package com.example.reza66.tamin6;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.reza66.tamin6.models.Task;
import com.example.reza66.tamin6.models.TaskBank;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ATaskFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private TaskAdapter mTaskAdapter;
    private FloatingActionButton mAddTaskButton;
    private TextView mNullTask;

    private int posi;

    public static ATaskFragment newInstance() {
        
        Bundle args = new Bundle();
        
        ATaskFragment fragment = new ATaskFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public ATaskFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_all_task, container, false);

        mAddTaskButton=view.findViewById(R.id.add_task_button);
        mRecyclerView=view.findViewById(R.id.tasks_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mNullTask=view.findViewById(R.id.null_task_text);

        updateUI();



        mAddTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Task task=new Task();
                TaskBank.getInstance().addTask(task);
                Intent intent=InformationInputActivity.newIntent(getActivity(),task.getId());
                startActivity(intent);

            }
        });

        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        TaskBank taskBank=TaskBank.getInstance();
        List<Task> tasks=taskBank.getTasks();
        if(mTaskAdapter==null){
            mTaskAdapter=new TaskAdapter(tasks);
            mRecyclerView.setAdapter(mTaskAdapter);
        } else{
           mTaskAdapter.setTasks(tasks);
           mTaskAdapter.notifyDataSetChanged();}
        //mTaskAdapter.notifyItemChanged(posi);
        if(mTaskAdapter.getItemCount()==0)
            mNullTask.setVisibility(View.VISIBLE);
        else
            mNullTask.setVisibility(View.GONE);
    }
    private class  TaskHolder extends RecyclerView.ViewHolder{

        private TextView mTitleTextView;
        private TextView mIconTextView;

        private Task mTask;

        public TaskHolder(View itemView) {
            super(itemView);
            mTitleTextView=itemView.findViewById(R.id.list_item_task_title);
            mIconTextView=itemView.findViewById(R.id.task_icon_text);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    posi=TaskHolder.this.getAdapterPosition();
                    Intent intent=EditTaskActivity.newIntent(getActivity(),mTask.getId());
                    startActivity(intent);
                }
            });
        }


        public void bind(Task task){
            mTask=task;
            mTitleTextView.setText(task.getTitle());
            mIconTextView.setText(task.getTitle().substring(0,1));
            //mSolvedImageView.setVisibility(crime.isSolved()==true ? View.VISIBLE:View.GONE);
        }
    }
    private class TaskAdapter extends RecyclerView.Adapter<TaskHolder>{
        private List<Task> mTasks;

        public TaskAdapter(List<Task> tasks) {
            mTasks = tasks;
        }

        public void setTasks(List<Task> tasks) {mTasks = tasks;}

        @Override
        public TaskHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater= LayoutInflater.from(getActivity());
            View view=inflater.inflate(R.layout.list_item_task,parent,false);
            TaskHolder taskHolder=new TaskHolder(view);

            return taskHolder;
        }

        @Override
        public void onBindViewHolder(TaskHolder holder, int position) {
            Task task=mTasks.get(position);
            holder.bind(task);
        }

        @Override
        public int getItemCount() {
            return mTasks.size();
        }
    }

}
