package io.wegetit.sau.core.utils;

import org.springframework.scheduling.support.CronExpression;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.scheduling.support.SimpleTriggerContext;

import java.time.LocalDateTime;
import java.util.Date;

public class CronUtils {

    public static final long MIN_SPAN_IN_SECONDS = 10;

    private CronUtils(){}

    public static boolean isValid(String cron) {
        try {
            CronExpression.parse(cron);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public static LocalDateTime getNextExecutionDate(String cron, LocalDateTime date) {
        LocalDateTime minSpan = date.plusSeconds(MIN_SPAN_IN_SECONDS);
        CronTrigger ct = new CronTrigger(cron);
        Date nowDate = DateUtils.asDate(date);
        LocalDateTime next = DateUtils.asLocalDateTime(ct.nextExecutionTime(new SimpleTriggerContext(nowDate, nowDate, nowDate)));
        return next.isBefore(minSpan) ? minSpan : next;
    }
}
