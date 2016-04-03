package com.truedev.application.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.truedev.application.Adapters.BaseRecyclerAdapter;
import com.truedev.application.R;
import com.truedev.application.Utils.Constants;
import com.truedev.application.models.HomeItem;
import com.truedev.application.models.ListItem;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lakshaygirdhar on 22/3/16.
 */
public class HomeActivity extends BaseActivity implements BaseRecyclerAdapter.BindAdapterListener {


    @Bind(R.id.rcvItems)
    RecyclerView rcvItems;

    private ArrayList<HomeItem> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_home, mContentFrame);

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ButterKnife.bind(this, mContentFrame);

        items = Constants.getHomeItems();

        HomeListHolder holder = new HomeListHolder(getHolderView());
        BaseRecyclerAdapter<HomeItem, HomeListHolder> adapter = new BaseRecyclerAdapter<>(this, items, holder, this);
        rcvItems.setLayoutManager(new LinearLayoutManager(this));
        rcvItems.setHasFixedSize(true);
        rcvItems.setAdapter(adapter);
    }

    private View getHolderView() {
        return LayoutInflater.from(this).inflate(R.layout.home_item, null);
    }

    @Override
    public void onBind(Object holder, int position) {
        HomeListHolder holder1 = (HomeListHolder) holder;
        holder1.tvTitle.setText(items.get(position).getTitle());
        holder1.mItem = items.get(position);
    }

    private class HomeListHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle;
        private HomeItem mItem;

        public HomeListHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (mItem.getmAction()){
                        case ACTIVITY:
                            Intent intent = new Intent(HomeActivity.this,mItem.getActionClass());
                            startActivity(intent);
                            break;
                    }
                }
            });
        }
    }
}
