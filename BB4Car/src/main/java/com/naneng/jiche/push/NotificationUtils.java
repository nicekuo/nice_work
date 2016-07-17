package com.naneng.jiche.push;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;

import com.naneng.jiche.R;
import com.naneng.jiche.background.ConfigValue;
import com.naneng.jiche.ui.main.MainActivity;
import com.naneng.jiche.ui.main.MainActivity_;


/**
 * @author fanchaowei E-mail:fanei@126.com
 * @version 2014-7-28 18:03:11
 */

public class NotificationUtils {
    public static final String keyFrom = "from";
    public static final String keyValue = "value";


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public static void showNotifiComments(Context mContext, String title, String content, int type,String value, int NOTIFY_ID) {
        NotificationManager notificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification.Builder builder = new Notification.Builder(mContext);

        Intent intent = null;
        intent = new Intent(mContext, MainActivity_.class);
        intent.putExtra(ConfigValue.kIntentType,ConfigValue.kIntentTypePush);
        intent.putExtra(ConfigValue.kPushType,type);
        intent.putExtra(ConfigValue.kPushValue,value);


        Bitmap largeIcon = null;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            largeIcon = ((BitmapDrawable) mContext.getDrawable(R.drawable.ic_launcher)).getBitmap();
        } else {
            largeIcon = ((BitmapDrawable) mContext.getResources().getDrawable(R.drawable.ic_launcher)).getBitmap();
        }

        Notification notification = builder.setLargeIcon(largeIcon)
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle(title)
                .setContentText(content)
                .setTicker(content)
                .setContentIntent(PendingIntent.getActivity(mContext, NOTIFY_ID, intent, PendingIntent.FLAG_UPDATE_CURRENT))
                .build();

        notification.defaults = Notification.DEFAULT_SOUND;
        notification.flags = Notification.FLAG_AUTO_CANCEL;
        notificationManager.notify(NOTIFY_ID, notification);

    }
}
