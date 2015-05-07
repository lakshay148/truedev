package com.truedev.application.Fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.truedev.application.Activity.MediaStoreTest;
import com.truedev.application.Activity.ToolBarTestActivity;
import com.truedev.application.ImageInfo;
import com.truedev.application.R;
import com.truedev.application.Utils.Constants;

/**
 * Created by Lakshay on 08-04-2015.
 */
public class InfosFragment extends Fragment implements View.OnClickListener{

    String fragmentType ;


    public static InfosFragment newInstance(String fragmentType)
    {
        InfosFragment infosFragment = new InfosFragment();
        Bundle extras = new Bundle();
        extras.putString(Constants.OPEN_FRAGMENT, fragmentType);
        infosFragment.setArguments(extras);
        return infosFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.infos_fragment,container, false);
        rootView.findViewById(R.id.bImplementation).setOnClickListener(this);
        fragmentType = getArguments().getString(Constants.OPEN_FRAGMENT);
        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.bImplementation:
                getImplementation(fragmentType);
                break;
        }
    }

    private void getImplementation(String fragmentType) {
        switch (fragmentType)
        {
            case Constants.TOOL_BAR:
                Intent intent =  new Intent(getActivity(), ToolBarTestActivity.class);
                startActivity(intent);
                break;

            case Constants.MEDIA_STORE:
                Intent intent1 = new Intent(getActivity(), MediaStoreTest.class);
                startActivity(intent1);
                break;
            case Constants.NAVIGATION_DRAWER:
            case Constants.NOTIFICATIONS:
            case Constants.LISTVIEWS:
                Toast.makeText(getActivity(),"Coming Soon ", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
