package com.truedev.application.Utils;

import android.os.Environment;
import android.util.Log;

import com.truedev.application.Activity.DateTimeAcitivity;
import com.truedev.application.Activity.ImageUploadActivity;
import com.truedev.application.Activity.MapsActivity;
import com.truedev.application.Activity.NotificationsActivity;
import com.truedev.application.Activity.SocialActivity;
import com.truedev.application.Activity.SyncAdapterActivity;
import com.truedev.application.models.ListItem;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by Lakshay on 13-02-2015.
 */
public class Constants {

    public static final int TYPE_IMAGE = 1;
    private static final String ANALYTICS_FRAGMENTS = "AnalyticsFragment";
    public static final String OPEN_FRAGMENT = "openFragment";

    public static final String ANALYTICS = "Analytics";
    public static final String LISTVIEWS = "ListViews";
    public static final String CAMERA_ITEMS = "Camera";
    public static final String MAP_TYPE = "mapType";
    public static final String NAVIGATION_DRAWER = "Navigation Drawers";
    public static final String NOTIFICATIONS = "Notifications";

    public static final String MAPS = "Maps";
    public static final String TOOL_BAR = "ToolBar";

    public static final String MEDIA_STORE = "mediaStore";
    public static final int CONNECT_TIMEOUT_SECS = 50;
    public static final int WRITE_TIMEOUT_SECS = 50;
    public static final int READ_TIMEOUT_SECS = 50;

    public static final int CONNECTION_POOL_SIZE = 4;
    public static final long CONNECTION_MAX_IDLE_TIME_MS = 3000;
    public static final String QUERY = "q";
    public static final String CITY = "city";
    public static final String DEVICE_ID = "deviceId";
    public static final String APP_VERSION_LABEL = "app_version";
    public static final String APP_VERSION = "version";
    public static final String EMAIL = "email";
    public static final String USERNAME = "username";

    public static String[] allItems = new String[]{ANALYTICS,LISTVIEWS,NAVIGATION_DRAWER,MAPS,NOTIFICATIONS,CAMERA_ITEMS,TOOL_BAR,MEDIA_STORE};

    public static HashMap<String,String> getItemsFragments()
    {
        HashMap<String,String> itemsFragments = new HashMap<>();
        itemsFragments.put(ANALYTICS,ANALYTICS_FRAGMENTS);
        return itemsFragments;
    }

    public static File getMediaOutputFile(int type)
    {
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "MyCameraApp");

        // Create the storage directory if it does not exist
        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                Log.d("MyCameraApp", "failed to create directory");
            }
        }
        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;

        if(type==TYPE_IMAGE)
        {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "IMG_"+ timeStamp + ".jpg");
        }
        else
            return null;
        return mediaFile;
    }

    public static ArrayList<ListItem> getHomeItems() {
        ArrayList<ListItem> items = new ArrayList<>();
        items.add(new ListItem("Image Upload",ListItem.ACTION.ACTIVITY,ImageUploadActivity.class));
        items.add(new ListItem("Google Maps",ListItem.ACTION.ACTIVITY,MapsActivity.class));
        items.add(new ListItem("Notifications",ListItem.ACTION.ACTIVITY,NotificationsActivity.class));
        items.add(new ListItem("Social", ListItem.ACTION.ACTIVITY,SocialActivity.class));
        items.add(new ListItem("Date And Time ", ListItem.ACTION.ACTIVITY,DateTimeAcitivity.class));
        items.add(new ListItem("SyncAdapter ", ListItem.ACTION.ACTIVITY,SyncAdapterActivity.class));
        return items;
    }
}
