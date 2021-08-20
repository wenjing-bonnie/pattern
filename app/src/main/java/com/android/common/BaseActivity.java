package com.android.common;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.PatternApplication;

/**
 * Created by wenjing.liu on 2021/4/7 in J1.
 *
 * @author wenjing.liu
 */
public class BaseActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        BinderThreadPool.getAllBinderThread(getClass().getSimpleName());
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                finish();
                return true;
            }
            default:
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onPause() {
        super.onPause();
        BinderThreadPool.getAllBinderThread(getClass().getSimpleName() + " , onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        BinderThreadPool.getAllBinderThread(getClass().getSimpleName() + " , onResume");
    }

    @Override
    protected void onStart() {
        super.onStart();
        BinderThreadPool.getAllBinderThread(getClass().getSimpleName() + " , onStart");
    }
}
