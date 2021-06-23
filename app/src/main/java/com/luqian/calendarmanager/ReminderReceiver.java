package com.luqian.calendarmanager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CalendarContract;
import android.util.Log;

/**
 * @author Administrator
 * @date 2021/6/23
 */
public class ReminderReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("ReminderReceiver", "日历提醒事件来了");
        if (intent.getAction().equals(CalendarContract.ACTION_EVENT_REMINDER)) {
            Uri uri = intent.getData();
            String alertTime = uri.getLastPathSegment();
            String selection = CalendarContract.CalendarAlerts.ALARM_TIME + "=?";
            try (Cursor cursor = context.getContentResolver().query(
                    CalendarContract.CalendarAlerts.CONTENT_URI_BY_INSTANCE,
                    new String[]{CalendarContract.CalendarAlerts.TITLE},
                    selection,
                    new String[]{alertTime},
                    null)) {
                if (cursor.moveToFirst()) {
                    String title = cursor.getString(cursor.getColumnIndex(CalendarContract.Events.TITLE));
                }
            }
        }
    }
}
