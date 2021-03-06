package com.truedev.application.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.truedev.application.Adapters.BaseRecyclerAdapter;
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

//        HomeListHolder holder = new HomeListHolder(getHolderView());
        BaseRecyclerAdapter<ListItem, HomeListHolder> adapter = new BaseRecyclerAdapter<ListItem,HomeListHolder>(this, items, this,HomeListHolder.class, R.layout.home_item);
//        HomeListAdapter adapter = new HomeListAdapter(items,this);
        rcvItems.setLayoutManager(new LinearLayoutManager(this));
        rcvItems.setHasFixedSize(true);
        rcvItems.setAdapter(adapter);
    }

    @Override
    public void onBind(final HomeListHolder holder, int position) {
        holder.tvTitle.setText(items.get(position).getTitle());
        holder.mItem = items.get(position);
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (holder.mItem.getmAction()) {
                    case ACTIVITY:
                        Intent intent = new Intent(HomeActivity.this, holder.mItem.getActionClass());
                        startActivity(intent);
                        break;
                }
            }
        });
    }

    public static class HomeListHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle;
        private ListItem mItem;
        private View mView;

        public HomeListHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            mView = itemView;
        }
    }
}
