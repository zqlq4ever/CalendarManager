package com.luqian.calendarmanager.calendar;

/**
 * 日历事件重复模式
 *
 * <p>通过重复模式获取事件组装用的重复规则
 * TIP: 提供几种常用的重复模式类型{@link RRuleConstant},可直接使用,更多模式参考以下解释进行完全自定义
 *
 * <p>-------------------------------------------------------------------------
 * 重复规则：
 * recur-rule-part =（“FREQ”“=”freq）
 * /（“UNTIL”“=”enddate）
 * /（“COUNT”“=”1 * DIGIT）
 * /（“INTERVAL”“ =“1 * DIGIT
 * ”/（“BYSECOND”“=”byseclist）
 * /（“BYMINUTE”“=”byminlist）
 * /（“BYHOUR”“=”byhrlist）
 * /（“BYDAY”“=”
 * /（“BYWEEKNO”“=”bywknolist）
 * /（“BYMONTH”“=”bymolist）
 * /（“BYSETPOS”“=”bysplist）
 * /（“WKST”“=”工作日）
 * -------------------------------------------------------------------------
 *
 * <p> TIP 重复规则组装 参数解释：
 * -------------------------------------------------------------------------
 * FREQ 重复频率  Type: TEXT
 * DAILY 天  YEARLY 年  WEEKLY 周  MONTHLY 月
 * -------------------------------------------------------------------------
 * <p>
 * INTERVAL 间隔时间  Type: INT
 * 依据 FREQ 重复频率分别为  间隔 INT 天  间隔 INT 年  间隔 INT 周  间隔 INT 月
 * 例：每隔一天 - 永远：
 * RRULE：FREQ = DAILY; INTERVAL = 2
 * <p>
 * -------------------------------------------------------------------------
 * <p>
 * COUNT 发生次数
 * <P>Type: INT
 * 指在 FREQ 和 INTERVAL 的基础上，一共发生的次数
 * 例：每日 10 次：
 * RRULE：FREQ=DAILY;COUNT=10
 * <p>
 * -------------------------------------------------------------------------
 * UNTIL 从指定开始日开始计算，直到 UNTIL 到的那天结束 Type: TEXT
 * 例：每周一直到 1997年12月24日：
 * RRULE：FREQ = WEEKLY;UNTIL = 19971224T000000Z   TIP: 最后的 T......Z 之间的内容指时分秒
 * <p>
 * -------------------------------------------------------------------------
 * WKST 从星期 INT 开始
 * <P>Type: TEXT 以周的开头两个字母大写组成  例：MO
 * <p>
 * BYDAY 一周的哪些天才提醒
 * <P>Type: TEXT 以周的开头两个字母大写组成  例：MO
 * 例：每周周二和周四为期五周：
 * RRULE：FREQ=WEEKLY ;COUNT=10;WKST=SU;BYDAY=TU，TH
 *
 * <p>注：BYDAY 若在星期几(如 MO)前添加数字(如 1MO,-1MO)表示为每月(年)第几个星期几
 * +1 表示每月(年)第一个星期几，-1表示每月(年)倒数第一个星期几，以此类推.
 * (只有在 FREQ 标记为 WEEKLY 或 YEARLY 时才有效)
 * <p>
 * -------------------------------------------------------------------------
 * BYMONTHDAY  一月中的某天
 * <P>Type: INT
 * 例：BYMONTHDAY=2,15 表示每月2日和15日
 * 可接受-值，含义同 BYDAY 的-值
 * <p>
 * -------------------------------------------------------------------------
 * BYYEARDAY  一年中的某天  <P>Type: INT</P>
 * 同 BYMONTHDAY
 * -------------------------------------------------------------------------
 * 周表：
 * -------------------------
 * MO      周一
 * TU      周二
 * WE      周三
 * TH      周四
 * FR      周五
 * SA      周六
 * SU      周日
 * -------------------------
 *
 * <p> 了解更多关于 RRule 的规则请参考
 * <p> https://tools.ietf.org/html/rfc5545
 * <p> https://www.cnblogs.com/jcli/p/calendar_recur_rule.html
 * <p> https://blog.csdn.net/csdn15002274757/article/details/94557330
 */
public class RRuleConstant {

    /**
     * 每天重复 - 永远
     */
    public static final String REPEAT_CYCLE_DAILY_FOREVER = "FREQ=DAILY;INTERVAL=1";

    /**
     * 每周某天重复
     */
    public static final String REPEAT_CYCLE_WEEKLY = "FREQ=WEEKLY;INTERVAL=1;WKST=SU;BYDAY=";

    /**
     * 每月某天重复
     */
    public static final String REPEAT_CYCLE_MONTHLY = "FREQ=WEEKLY;INTERVAL=2;WKST=SU;BYMONTHDAY =";

    /**
     * 每周重复 - 周一
     */
    public static final String REPEAT_WEEKLY_BY_MO = "FREQ=WEEKLY;INTERVAL=1;WKST=MO;BYDAY=MO;UNTIL=";

    /**
     * 每周重复 - 周二
     */
    public static final String REPEAT_WEEKLY_BY_TU = "FREQ=WEEKLY;INTERVAL=1;WKST=MO;BYDAY=TU;UNTIL=";

    /**
     * 每周重复 - 周三
     */
    public static final String REPEAT_WEEKLY_BY_WE = "FREQ=WEEKLY;INTERVAL=1;WKST=MO;BYDAY=WE;UNTIL=";

    /**
     * 每周重复 - 周四
     */
    public static final String REPEAT_WEEKLY_BY_TH = "FREQ=WEEKLY;INTERVAL=1;WKST=MO;BYDAY=TH;UNTIL=";

    /**
     * 每周重复 - 周五
     */
    public static final String REPEAT_WEEKLY_BY_FR = "FREQ=WEEKLY;INTERVAL=1;WKST=MO;BYDAY=FR;UNTIL=";

    /**
     * 每周重复 - 周六
     */
    public static final String REPEAT_WEEKLY_BY_SA = "FREQ=WEEKLY;INTERVAL=1;WKST=MO;BYDAY=SA;UNTIL=";

    /**
     * 每周重复 - 周日
     */
    public static final String REPEAT_WEEKLY_BY_SU = "FREQ=WEEKLY;INTERVAL=1;WKST=MO;BYDAY=SU;UNTIL=";

    /**
     * 每年第一天和最后一天 - 永远
     */
    public static final String REPEAT_YEARLY_FIRST_AND_LAST_FOREVER = "FREQ=YEARLY;BYYEARDAY=1,-1";

}
