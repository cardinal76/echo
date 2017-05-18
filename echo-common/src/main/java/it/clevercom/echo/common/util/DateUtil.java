package it.clevercom.echo.common.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	/**
	 * 
	 * @param date
	 * @return
	 */
	public static Date getEndOfDay(Date date) {
	  LocalDateTime localDateTime = dateToLocalDateTime(date);
	  LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
	  return localDateTimeToDate(endOfDay);
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public static Date getStartOfDay(Date date) {
	  LocalDateTime localDateTime = dateToLocalDateTime(date);
	  // LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
	  LocalDateTime startOfDay = localDateTime.toLocalDate().atStartOfDay();
	  return localDateTimeToDate(startOfDay);
	}

	/**
	 * 
	 * @param startOfDay
	 * @return
	 */
	public static Date localDateTimeToDate(LocalDateTime startOfDay) {
	  return Date.from(startOfDay.atZone(ZoneId.systemDefault()).toInstant());
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public static LocalDateTime dateToLocalDateTime(Date date) {
	  return LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
	}
	
	/**
	 * 
	 * @param startOfDay
	 * @return
	 */
	public static Long localDateTimeToLong(LocalDateTime locaDateTime) {
		Date date = Date.from(locaDateTime.atZone(ZoneId.systemDefault()).toInstant()); 
		return date.getTime();
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public static LocalDateTime longToLocalDateTime(Long longDate) {
		return LocalDateTime.ofInstant(Instant.ofEpochMilli(longDate), ZoneId.systemDefault());
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public static Date getFirstDateOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date firstDay = calendar.getTime();
        return firstDay;
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public static Date getLastDateOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date lastDay = calendar.getTime();
        return lastDay;
	}

	/**
	 * 
	 * @param today
	 * @return
	 */
	public static Date getFirstDateOfWeek(Date date) {
		Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
        Date firstDay = calendar.getTime();
        return firstDay;
	}

	/**
	 * 
	 * @param today
	 * @return
	 */
	public static Date getLastDateOfWeek(Date date) {
		Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.WEEK_OF_YEAR, 1);
        Date lastDay = calendar.getTime();
        return lastDay;
	}
}
