package com.truedev.application.Interfaces;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.truedev.application.models.ListItem;

/**
 * Created by Lakshay on 25/03/16.
 */
public class ActionsListener implements View.OnClickListener {

    private ListItem mItem;
    private Context mContext;

    public ActionsListener(Context context , ListItem item){
        mItem = item;
        mContext = context;
    }

    @Override
    public void onClick(View v) {
        switch (mItem.getmAction()){
            case ACTIVITY:
                Intent intent = new Intent(mContext,mItem.getActionClass());
                mContext.startActivity(intent);
                break;
        }
    }
}
