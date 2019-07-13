package com.example.mylauncher;

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
    private RecyclerView rl;
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
        rl.setLayoutManager(new GridLayoutManager(this, 4));
        rl.setAdapter(mMyAppsAdapter);
        mMyAppsAdapter.setMyAppsAdapterSetOnClickListener(this);
    }

    private void getApps() {
        PackageManager packageManager = getPackageManager();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        mMApps = packageManager.queryIntentActivities(intent, 0);
    }

    private void initView() {
        rl = findViewById(R.id.rl);
    }

    @Override
    public void OnClickListener(int i) {
        ResolveInfo resolveInfo = mMApps.get(i);
        String packageName = resolveInfo.activityInfo.packageName;
        String appName = resolveInfo.activityInfo.name;
        ComponentName componentName = new ComponentName(packageName, appName);
        Intent intent = new Intent();
        intent.setComponent(componentName);
        startActivity(intent);
    }
}
