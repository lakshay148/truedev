package com.truedev.application.Gcm;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.truedev.application.Activity.HomeActivity;
import com.truedev.application.R;

/**
 * Created by Lakshay on 10/04/16.
 */
public class NotificationUtils {

    /**
     * @param context Context
     * @param title   Title
     * @param message Message
     */
    public static void generateNotification(Context context, String title, String message) {
        NotificationCompat.Builder builder = getNotificationBuilder(context,title,message);

        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(1, builder.build());
    }

    /**
     * @param context
     * @param title
     * @param message
     * @param landingClass
     */
    public static void generateNotification(Context context, String title, String message, Class landingClass,Class parentClass) {
        NotificationCompat.Builder builder = getNotificationBuilder(context,title,message);

        generate(context, parentClass,landingClass, builder, 1);
    }

    /**
     *
     * @param context
     * @param title
     * @param message
     * @param landingClass
     */
    public static void generateNotificationInbox(Context context, String title, String message, Class landingClass,Class parentClass) {
        NotificationCompat.Builder builder = getNotificationBuilder(context,title,message);

        NotificationCompat.InboxStyle inboxStyle =
                new NotificationCompat.InboxStyle();
        String[] events = new String[]{"Jai ho","hollla","hollla","hollla","hollla","hollla"};
        inboxStyle.setBigContentTitle("Event tracker details:");
        for (int i = 0; i < events.length; i++) {
            inboxStyle.addLine(events[i]);
        }

        builder.setStyle(inboxStyle);

        generate(context, parentClass , landingClass, builder, 1);
    }

    /**
     *
     * @param context
     * @param title
     * @param message
     * @param landingClass
     */
    public static void generateNotificationBig(Context context, String title, String message, Class landingClass,Class parentClass) {
        NotificationCompat.Builder builder = getNotificationBuilder(context,title,message);

        NotificationCompat.BigTextStyle bigTextStyle =
                new NotificationCompat.BigTextStyle();
        bigTextStyle.setBigContentTitle(title);
        bigTextStyle.bigText(message);
        bigTextStyle.setSummaryText(context.getString(R.string.app_name));
        builder.setStyle(bigTextStyle);

        generate(context, parentClass, landingClass, builder, 1);
    }

    /**
     *
     * @param context
     * @param title
     * @param message
     * @return
     */
    private static NotificationCompat.Builder getNotificationBuilder(Context context, String title, String message) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setSmallIcon(R.drawable.ic_launcher);
        builder.setContentTitle(title);
        builder.setContentText(message);
        builder.setAutoCancel(true);
        return builder;
    }

    /**
     *
     * @param context
     * @param landingClass
     * @param builder
     * @param id
     */
    private static void generate(Context context,Class parentClass, Class landingClass,NotificationCompat.Builder builder,int id){
        Intent intent = new Intent(context, landingClass);
        Intent intent1 = new Intent(context, parentClass);
        PendingIntent resultPendingIntent = PendingIntent.getActivities(context,1,new Intent[]{intent1,intent},PendingIntent.FLAG_ONE_SHOT);
        builder.setContentIntent(resultPendingIntent);

        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(id, builder.build());
    }


    /**
     * @param landingId id which would be required to get landing activity
     */
    public static Class getLandingMap(String landingId) {
        switch (landingId) {
            case "1":
                return HomeActivity.class;
            default:
                return HomeActivity.class;
        }
    }
}
