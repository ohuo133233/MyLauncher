package com.example.launcher;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;


import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class MainActivity extends Activity implements MyAppsAdapter.MyAppsAdapterSetOnClickListener {
    private String TAG = "MainActivity";
    private RecyclerView mRecyclerView;
    private MyAppsAdapter mMyAppsAdapter;
    private List<ResolveInfo> mMApps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);
        initView();
        getApps();
        setData();
    }

    private void setData() {
        if (mMyAppsAdapter == null) {
            mMyAppsAdapter = new MyAppsAdapter(this, mMApps);
        }
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        mRecyclerView.setAdapter(mMyAppsAdapter);
        mMyAppsAdapter.setMyAppsAdapterSetOnClickListener(this);
    }

    private void getApps() {
        PackageManager packageManager = getPackageManager();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        mMApps = packageManager.queryIntentActivities(intent, 0);
    }

    private void initView() {
        mRecyclerView = findViewById(R.id.rl);
    }

    @Override
    public void OnClickListener(int i) {
        ResolveInfo resolveInfo = mMApps.get(i);
        String packageName = resolveInfo.activityInfo.packageName;
        String name = resolveInfo.activityInfo.name;
        LogUtils.e("packageName: "+packageName);
        LogUtils.e("name: "+name);
        ComponentName componentName = new ComponentName(packageName, name);
        Intent intent = new Intent();
        intent.setComponent(componentName);
        startActivity(intent);
    }
}
