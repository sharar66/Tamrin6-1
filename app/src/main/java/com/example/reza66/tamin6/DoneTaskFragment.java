package com.example.reza66.tamin6;


import android.os.Bundle;
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
public class DoneTaskFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private TaskAdapter mTaskAdapter;

    public static DoneTaskFragment newInstance() {
        
        Bundle args = new Bundle();
        
        DoneTaskFragment fragment = new DoneTaskFragment();
        fragment.setArguments(args);
        return fragment;
    }


    public DoneTaskFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_done_task, container, false);
        mRecyclerView=view.findViewById(R.id.done_tasks_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();

        return  view;
    }
    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        TaskBank taskBank = TaskBank.getInstance();
        List<Task> tasks = taskBank.getDoneTasks();
        if (mTaskAdapter == null) {
            mTaskAdapter = new TaskAdapter(tasks);
            mRecyclerView.setAdapter(mTaskAdapter);
        } else {
            mTaskAdapter.setTasks(tasks);
            mTaskAdapter.notifyDataSetChanged();
        }
        //mTaskAdapter.notifyItemChanged(posi);
//        if (mTaskAdapter.getItemCount() == 0)
//            mNullTask.setVisibility(View.VISIBLE);
//        else
//            mNullTask.setVisibility(View.GONE);
    }
    private class  TaskHolder extends RecyclerView.ViewHolder{

        private TextView mTitleTextView;
        private TextView mIconTextView;

        private Task mTask;

        public TaskHolder(View itemView) {
            super(itemView);
           {
                 mTitleTextView=itemView.findViewById(R.id.list_item_task_title);
                 mIconTextView=itemView.findViewById(R.id.task_icon_text);}
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
//            if(task.isDone())
                holder.bind(task);
        }

        @Override
        public int getItemCount() {
            return mTasks.size();
        }
    }


}
