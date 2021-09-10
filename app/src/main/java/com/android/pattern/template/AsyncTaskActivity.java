package com.android.pattern.template;

import android.os.AsyncTask;
import android.os.Bundle;

import com.android.common.BaseActivity;
import com.android.pattern.R;

public class AsyncTaskActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);

        AsyncTask task = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                return null;
            }
        };
        task.execute();
    }
}