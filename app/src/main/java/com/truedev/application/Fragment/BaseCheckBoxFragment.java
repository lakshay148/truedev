package com.truedev.application.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.truedev.application.Adapters.BaseRecyclerAdapter;
import com.truedev.application.Interfaces.FragmentActionsListener;
import com.truedev.application.R;
import com.truedev.application.Utils.CommonUtils;
import com.truedev.application.events.CheckChangedEvent;
import com.truedev.application.models.BaseCheckListModel;
import com.truedev.application.models.CheckListItem;

import java.util.ArrayList;

/**
 * Created by lakshaygirdhar on 7/4/16.
 */
public class BaseCheckBoxFragment extends Fragment implements BaseRecyclerAdapter.BindAdapterListener<BaseCheckBoxFragment.CheckListHolder> {

    private static final String TAG = "BaseCheckBoxFragment";
    private Context mContext;
    public static final String CHECK_ITEMS = "items";
    private FragmentActionsListener mListener;
    private ArrayList<CheckListItem> mItems;
    private BaseCheckListModel baseCheckListModel;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    public static BaseCheckBoxFragment newInstance(BaseCheckListModel model,FragmentActionsListener listener) {
        Bundle args = new Bundle();
        args.putSerializable(CHECK_ITEMS,model);
        BaseCheckBoxFragment fragment = new BaseCheckBoxFragment();
        fragment.mListener = listener;
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        BaseCheckListModel model = (BaseCheckListModel) getArguments().getSerializable(CHECK_ITEMS);
        View view = inflater.inflate(R.layout.base_checkbox_layout, container, false);
        ((TextView)view.findViewById(R.id.tvTitleCheckBoxFragment)).setText(model.getTitle());
        RecyclerView rcvCheckBox= CommonUtils.recyclerView((RecyclerView) view.findViewById(R.id.rcvCheckBoxItems),mContext,true);
        mItems = model.getItem();
        BaseRecyclerAdapter<CheckListItem,CheckListHolder> adapter = new BaseRecyclerAdapter<CheckListItem,CheckListHolder>(mContext,mItems,this,CheckListHolder.class, R.layout.check_list_item);
        rcvCheckBox.setAdapter(adapter);

        return view;
    }

    @Override
    public void onBind(CheckListHolder holder, final int position) {
        holder.cbItem.setText(mItems.get(position).getDisplayName());
        holder.cbItem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.d(TAG, "onCheckedChanged: "+ position);
                mListener.onActionDone(new CheckChangedEvent(isChecked,position),BaseCheckBoxFragment.this);
            }
        });
    }


    public static class CheckListHolder extends RecyclerView.ViewHolder{

        private CheckBox cbItem;
        private TextView tvItem;

        public CheckListHolder(View itemView) {
            super(itemView);
            cbItem = (CheckBox) itemView.findViewById(R.id.cbItem);
        }
    }
}
