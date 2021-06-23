package com.luqian.calendarmanager;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.luqian.calendarmanager.calendar.AdvanceTime;
import com.luqian.calendarmanager.calendar.CalendarEvent;
import com.luqian.calendarmanager.calendar.CalendarManager;
import com.luqian.calendarprovider.R;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ReminderReceiver mReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{
                            Manifest.permission.WRITE_CALENDAR,
                            Manifest.permission.READ_CALENDAR},
                    1);
        }

        findViewById(R.id.bt_add).setOnClickListener(this);
        findViewById(R.id.bt_delete).setOnClickListener(this);
        findViewById(R.id.bt_open_system_calendar).setOnClickListener(this);
        findViewById(R.id.bt_update).setOnClickListener(this);
        findViewById(R.id.bt_query).setOnClickListener(this);
        findViewById(R.id.bt_search).setOnClickListener(this);

        IntentFilter filter = new IntentFilter();
        filter.addAction(CalendarContract.ACTION_EVENT_REMINDER);
        // 隐示 intent 所以要加这一行
        filter.addDataScheme("content");
        mReceiver = new ReminderReceiver();
        registerReceiver(mReceiver, filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {

        long accountId = CalendarManager.obtainCalendarAccountId(this);
        List<CalendarEvent> calendarEventList = CalendarManager.queryAccountEvent(this, accountId);

        switch (view.getId()) {
            // 添加事件
            case R.id.bt_add:

                CalendarEvent calendarEvent = new CalendarEvent(
                        "有一个快递要去拿",
                        "京东送来了一个快递，放在丰巢，不要忘记拿了",
                        "月球表面的那个大坑",
                        System.currentTimeMillis() + 6 * 60 * 1000,
                        System.currentTimeMillis() + 10 * 60 * 1000,
                        AdvanceTime.FIVE_MINUTES,
                        null
                );

                int result = CalendarManager.addCalendarEvent(this, calendarEvent);
                switch (result) {
                    case 0:
                        toast("插入成功");
                        break;
                    case -1:
                        toast("插入失败");
                        break;
                    case -2:
                        toast("没有权限");
                        break;
                }
                break;

            // 删除事件
            case R.id.bt_delete:

                if (null == calendarEventList) {
                    toast("查询失败");
                    break;
                }

                if (calendarEventList.size() == 0) {
                    toast("没有事件可以删除");
                } else {
                    long eventDeleteId = calendarEventList.get(0).getId();
                    int resultDelete = CalendarManager.deleteCalendarEvent(this, eventDeleteId);
                    if (resultDelete == -2) {
                        toast("没有权限");
                    } else {
                        toast("删除成功");
                    }
                }

                break;

            // 更新事件
            case R.id.bt_update:
                if (null == calendarEventList) {
                    Toast.makeText(this, "查询失败", Toast.LENGTH_SHORT).show();
                    break;
                }

                if (calendarEventList.size() == 0) {
                    toast("没有事件可以更新");
                } else {
                    //  第一个事件
                    long eventUpdateId = calendarEventList.get(0).getId();
                    //  修改事件标题
                    int resultUpdate = CalendarManager.updateCalendarEventTitle(
                            this, eventUpdateId, "---------我是修改后的标题----------");
                    if (resultUpdate == 1) {
                        toast("更新成功");
                    } else {
                        toast("更新失败");
                    }
                }

                break;

            // 查询事件
            case R.id.bt_query:

                if (null == calendarEventList) {
                    toast("查询失败");
                    break;
                }

                StringBuilder builder = new StringBuilder();
                for (CalendarEvent event : calendarEventList) {
                    builder.append("\n")
                            .append(event.toString())
                            .append("\n");
                }
                ((TextView) findViewById(R.id.tv_event)).setText(builder.toString());
                toast("查询成功");

                break;

            case R.id.bt_open_system_calendar:
                // 启动系统日历进行编辑事件
                CalendarManager.startCalendarForIntentToInsert(
                        this,
                        System.currentTimeMillis(),
                        System.currentTimeMillis() + 60000,
                        "这是提醒标题",
                        "我在新建日历提醒",
                        "中国",
                        false);
                break;
            case R.id.bt_search:
                if (CalendarManager.isEventAlreadyExist(
                        this,
                        1552986006309L,
                        155298606609L,
                        "马上吃饭")) {
                    toast("存在");
                } else {
                    toast("不存在");
                }
                break;
            default:
                break;
        }
    }


    private void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}
