package edu.baylor.ecs.csi3471.hotelReservationSystem.backend;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateHelper {
    public static Calendar getCalendarWithoutTime(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar;
    }
    public static Date getDateWithoutTime(Date date) throws ParseException {
        // for date comparisons ignoring time
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.parse(formatter.format(date));
    }
    public static List<Date> getDaysInBetween(Date start, Date end){
        // store all dates (without times) from start to end dates
        List<Date> days = new ArrayList<>();
        Calendar startDate = getCalendarWithoutTime(start);
        Calendar endDate = getCalendarWithoutTime(end);
        while(startDate.before(endDate)){
            days.add(startDate.getTime());
            startDate.add(Calendar.DATE, 1);
        }
        return days;
    }

}
