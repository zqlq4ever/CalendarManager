# CalendarManager

# 接入方式

##  第一步 :

```apl
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

##	第二步

```apl
dependencies {
	implementation 'com.github.zqlq4ever:CalendarManager:version'
}
```

[![](https://jitpack.io/v/zqlq4ever/CalendarManager.svg)](https://jitpack.io/#zqlq4ever/CalendarManager)

##  使用说明


```java
//  日历操作都应放在子线程
// 添加日历事件
CalendarManager.addCalendarEvent(this, calendarEvent);

// 删除日历事件
CalendarManager.deleteCalendarEvent(this, eventDeleteId);

// 查询日历事件
CalendarManager.searchAccountEvent(this, accountId);

//  可以动态注册广播，接收日历提醒事件
IntentFilter filter = new IntentFilter();
filter.addAction(CalendarContract.ACTION_EVENT_REMINDER);
// 隐示 intent 所以要加这一行
filter.addDataScheme("content");
mReceiver = new ReminderReceiver();
registerReceiver(mReceiver, filter);
```


> Fork 自`kylechandev/CalendarProviderManager`的项目

# 原有信息

> 一个Android日历管理器，提供向系统日历插入日历账户、查询日历账户、添加修改删除日历事件以及事件提醒等功能，是时候为你的APP增加一个事件提醒功能啦！

介绍文章：https://www.jianshu.com/p/b60cc5e49a19
