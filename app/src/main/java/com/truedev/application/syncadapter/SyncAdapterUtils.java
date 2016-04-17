package com.truedev.application.syncadapter;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.ContentResolver;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.truedev.application.BuildConfig;


/**
 * Created by lakshaygirdhar on 29/9/15.
 */
public class SyncAdapterUtils {

    private static final String TAG = "SyncAdapterUtils";

    private static final String ACCOUNT = BuildConfig.APPLICATION_NAME;
    public static final String ACCOUNT_TYPE = BuildConfig.APPLICATION_ID+".account";
    public static final String CONTENT_AUTHORITY = BuildConfig.APPLICATION_ID+".SyncAdapter.provider";
    private static final long SYNC_FREQUENCY = BuildConfig.SYNC_FREQUENCY;
    public static Account mAccount;

    public static void createAccount(Context context){
        // Create the account type and default account
        mAccount = new Account(
                ACCOUNT, ACCOUNT_TYPE);
        // Get an instance of the Android account manager
        AccountManager accountManager =
                (AccountManager) context.getSystemService(
                        Context.ACCOUNT_SERVICE);
        /*
         * Add the account and account type, no password or user data
         * If successful, return the Account object, otherwise report an error.
         */
        if (accountManager.addAccountExplicitly(mAccount, null, null)) {
            /*
             * If you don't set android:syncable="true" in
             * in your <provider> element in the manifest,
             * then call context.setIsSyncable(account, AUTHORITY, 1)
             * here.
             */

            ContentResolver.setIsSyncable(mAccount,CONTENT_AUTHORITY,1);
            ContentResolver.setSyncAutomatically(mAccount, CONTENT_AUTHORITY, true);
            ContentResolver.addPeriodicSync(mAccount, CONTENT_AUTHORITY, new Bundle(), SYNC_FREQUENCY);
            Log.e(TAG,"Account Added");
        } else {
            /*
             * The account exists or some other error occurred. Log this, report it,
             * or handle it internally.
             */
            Log.e(TAG, "Either Account exists ");
        }
    }

}
