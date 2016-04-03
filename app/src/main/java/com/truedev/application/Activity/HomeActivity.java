package com.truedev.application.Activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.truedev.application.Adapters.BaseRecyclerAdapter;
import com.truedev.application.R;
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

    private ArrayList<ListItem> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_home, mContentFrame);
        ButterKnife.bind(this, mContentFrame);

        items = new ArrayList<>();
        ListItem item = new ListItem("Google Maps", ListItem.Action.ACTIVITY,MapsActivity.class);
        items.add(item);
        HomeListHolder holder = new HomeListHolder(getHolderView());
        BaseRecyclerAdapter<ListItem,HomeListHolder> adapter = new BaseRecyclerAdapter<>(this,items,holder,this);
        rcvItems.setLayoutManager(new LinearLayoutManager(this));
        rcvItems.setAdapter(adapter);

    }

    private View getHolderView() {
        return LayoutInflater.from(this).inflate(R.layout.home_item,null);
    }

    @Override
    public void onBind(Object holder, int position) {
        HomeListHolder holder1 = (HomeListHolder) holder;
        holder1.tvTitle.setText(items.get(position).getTitle());
        holder1.mItem = items.get(position);
    }

    private class HomeListHolder extends RecyclerView.ViewHolder{

        private TextView tvTitle;
        private ListItem mItem;

        public HomeListHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
//            itemView.setOnClickListener(new ActionsListener(HomeActivity.this,mItem));

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }
}
