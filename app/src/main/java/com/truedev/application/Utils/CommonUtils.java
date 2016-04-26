package com.truedev.application.Utils;

import android.Manifest;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.truedev.application.ApplicationController;
import com.truedev.application.Gcm.GcmIntentService;
import com.truedev.application.R;
import com.truedev.application.retrofit.RetrofitRequest;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by lakshaygirdhar on 23/3/16.
 */
public class CommonUtils {

    private static final String TAG = "CommonUtils";

    /**
     * @param context
     * @return the internet state
     */
    public static boolean isNetWorkAvailable(Context context) {

        ConnectivityManager connMgr = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        try {
            if (connMgr == null) {
                return false;
            } else if (connMgr.getActiveNetworkInfo() != null
                    && connMgr.getActiveNetworkInfo().isAvailable()
                    && connMgr.getActiveNetworkInfo().isConnected()) {
                return true;
            } else {

                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    /**
     *
     * @param recyclerView
     * @param context
     * @param orientation
     * @return
     */
    public static RecyclerView recyclerView(RecyclerView recyclerView, Context context, boolean orientation) {
        LinearLayoutManager recycler_layout = new LinearLayoutManager(context);
        if (orientation)
            recycler_layout.setOrientation(LinearLayout.VERTICAL);
        else
            recycler_layout.setOrientation(LinearLayout.HORIZONTAL);

        recyclerView.setLayoutManager(recycler_layout);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        return recyclerView;
    }

    public static byte[] serialize(Object obj) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream os;
        try {
            os = new ObjectOutputStream(out);
            os.writeObject(obj);
            return out.toByteArray();
        } catch (Exception e) {

            e.printStackTrace();
        }
        return null;
    }

    public static Object deserialize(byte[] mydata) {
        ByteArrayInputStream in = new ByteArrayInputStream(mydata);
        ObjectInputStream is;
        try {
            is = new ObjectInputStream(in);
            return is.readObject();
        } catch (StreamCorruptedException e) {

            e.printStackTrace();
        } catch (Exception e) {

            e.printStackTrace();
        }
        return null;
    }


    public static String getEmail(Context context) {
        AccountManager accountManager = AccountManager.get(context);
        Account account = getAccount(accountManager);

        if (account == null) {
            return null;
        } else {
            return account.name;
        }
    }

    private static Account getAccount(AccountManager accountManager) {
        Account[] accounts = accountManager.getAccountsByType("com.google");
        Account account;
        if (accounts.length > 0) {
            account = accounts[0];
        } else {
            account = null;
        }
        return account;
    }

    public static String getPhoneNumber(Context context) {
        TelephonyManager tMgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String mPhoneNumber = tMgr.getLine1Number();
        Log.e(TAG, "Phone Number : " + mPhoneNumber);
        return mPhoneNumber;
    }

    public static String getSelectedRadioButtonText(RadioGroup rootGroup) {
        int id = rootGroup.getCheckedRadioButtonId();
        if (id != -1) {
            RadioButton radioButton = (RadioButton) rootGroup.findViewById(id);
            return radioButton.getText().toString();
        }
        return "null";
    }


    public static ArrayList<String> splitIdFromData(String[] options, ArrayList<String> idList) {
        ArrayList<String> dataList = new ArrayList<>();
        idList.clear();
        String[] splitItem;
        for (String item : options) {
            splitItem = item.split("\\|");
            dataList.add(splitItem[0]);
            idList.add(splitItem[1]);
        }
        return dataList;
    }


    public static int getCheckedIndexFromGroup(RadioGroup rg) {
        int checkedId = rg.getCheckedRadioButtonId();
        View radioButton = rg.findViewById(checkedId);
        int index = rg.indexOfChild(radioButton);
        return index;
    }

    public static boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }


    public static void shareContent(Context context, String title, String content) {
        Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, title);
        shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, content);
        context.startActivity(Intent.createChooser(shareIntent, title));
    }

    public final static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    public static String getDeviceId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);
    }


    public static String getDeviceModel() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return model;
        }
        return manufacturer + " " + model;
    }

    public static void initiateGcm(Activity activity) {
        Intent intent = new Intent(activity, GcmIntentService.class);
        activity.startService(intent);
    }

    public static String getMonthValue(int possessionMonth) {
        switch (possessionMonth) {
            case 1:
                return "January";
            case 2:
                return "February";
            case 3:
                return "March";
            case 4:
                return "April";
            case 5:
                return "May";
            case 6:
                return "June";
            case 7:
                return "July";
            case 8:
                return "August";
            case 9:
                return "September";
            case 10:
                return "October";
            case 11:
                return "November";
            case 12:
                return "December";
            default:
                return "";
        }
    }

    public static void handlePermission(Activity activity, String permission, int requestCode) {
        if (ContextCompat.checkSelfPermission(activity,
                permission)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                    Manifest.permission.READ_CONTACTS)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(activity,
                        new String[]{Manifest.permission.READ_CONTACTS},
                        requestCode);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }
    }

    public static void openInPlayStore(Activity mActivity) {
        mActivity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + mActivity.getPackageName())));
    }

}

