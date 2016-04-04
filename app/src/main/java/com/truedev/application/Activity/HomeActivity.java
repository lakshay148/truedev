package com.truedev.application.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.truedev.application.Adapters.BaseRecyclerAdapter;
import com.truedev.application.Adapters.HomeListAdapter;
import com.truedev.application.Gcm.GcmIntentService;
import com.truedev.application.R;
import com.truedev.application.Utils.Constants;
import com.truedev.application.Utils.PrefsUtils;
import com.truedev.application.models.ListItem;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lakshaygirdhar on 22/3/16.
 */
public class HomeActivity extends BaseActivity implements BaseRecyclerAdapter.BindAdapterListener<HomeActivity.HomeListHolder> {


    @Bind(R.id.rcvItems)
    RecyclerView rcvItems;

    private ArrayList<ListItem> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_home, mContentFrame);

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ButterKnife.bind(this, mContentFrame);

        if("".equals(PrefsUtils.getStringSharedPreference(this, GcmIntentService.GCM_ID, ""))){
            startService(new Intent(this, GcmIntentService.class));
        }

        items = Constants.getHomeItems();

        BaseRecyclerAdapter<ListItem, HomeListHolder> adapter = new BaseRecyclerAdapter<>(this, items, this,HomeListHolder.class,R.layout.home_item);
        rcvItems.setLayoutManager(new LinearLayoutManager(this));
        rcvItems.setHasFixedSize(true);
        rcvItems.setAdapter(adapter);
    }

    @Override
    public void onBind(final HomeListHolder holder, int position) {
        holder.tvTitle.setText(items.get(position).getTitle());
        holder.mItem = items.get(position);
        holder.myView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (holder.mItem.getmAction()) {
                    case ACTIVITY:
                            Intent intent = new Intent(HomeActivity.this,holder.mItem.getActionClass());
                            startActivity(intent);
                        break;
                }
            }
        });
    }
//
    public static class HomeListHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle;
        private ListItem mItem;
        private View myView;


        public HomeListHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            myView = itemView;
        }
    }
}
