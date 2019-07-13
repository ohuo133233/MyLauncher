package com.example.mylauncher;


import java.util.List;

import android.content.Context;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class MyAppsAdapter extends RecyclerView.Adapter<MyAppsAdapter.ViewHolder> {
    private final Context mContext;
    private final List<ResolveInfo> mMApps;

    public MyAppsAdapter(MainActivity context, List<ResolveInfo> mApps) {
        mContext = context;
        mMApps = mApps;
    }

    @NonNull
    @Override
    public MyAppsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_view_item, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyAppsAdapter.ViewHolder viewHolder, final int i) {
        ResolveInfo resolveInfo = mMApps.get(i);
        Drawable drawable = resolveInfo.activityInfo.loadIcon(mContext.getPackageManager());
        CharSequence charSequence = resolveInfo.loadLabel(mContext.getPackageManager());
        viewHolder.mAppimg.setImageDrawable(drawable);
        viewHolder.mAppname.setText(charSequence);
        viewHolder.mAppimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMyAppsAdapterSetOnClickListener.OnClickListener(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mMApps.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView mAppname;
        private final ImageView mAppimg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mAppimg = itemView.findViewById(R.id.appimg);
            mAppname = itemView.findViewById(R.id.appname);
        }
    }

    public MyAppsAdapterSetOnClickListener mMyAppsAdapterSetOnClickListener;

    public void setMyAppsAdapterSetOnClickListener(
            MyAppsAdapterSetOnClickListener myAppsAdapterSetOnClickListener) {
        mMyAppsAdapterSetOnClickListener = myAppsAdapterSetOnClickListener;
    }

    interface MyAppsAdapterSetOnClickListener {
        void OnClickListener(int i);
    }
}
