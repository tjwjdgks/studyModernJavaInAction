package Part4;

import java.time.*;
import java.time.temporal.ChronoField;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

public class NewCalendarAndTime {


    public static void practice() {
        LocalDate date = LocalDate.of(2019,9,12); // Output: 2019-09-12
        int year = date.getYear(); // Output : 2019
        Month month = date.getMonth(); // Output : SEPTEMBER // 9월
        int day = date.getDayOfMonth(); // Output : 12
        DayOfWeek dow = date.getDayOfWeek(); // Output : THURSDAY // 해당 요일
        int len = date.lengthOfMonth(); // Output : 30 // 9월의 일 수
        boolean leap = date.isLeapYear(); // Output : false // 윤년이 아님

        // 팩토리 매서드 now()는 시스템 시계의 정보를 이용해서 현재 날짜 정보를 얻는다.
        LocalDate currentDate = LocalDate.now();

        int year_c = date.get(ChronoField.YEAR);
        int month_c = date.get(ChronoField.MONTH_OF_YEAR);
        int day_c = date.get(ChronoField.DAY_OF_MONTH);

        LocalTime hourAndMinute = LocalTime.of(13, 4); // output :13:04
        LocalTime hourAndMinuteAndSecond = LocalTime.of(13, 4,5); // output : 13:04:05
        // getter 메서드 제공
        int hour = hourAndMinuteAndSecond.getHour(); // 13
        int minute = hourAndMinuteAndSecond.getMinute(); // 4
        int second = hourAndMinuteAndSecond.getSecond(); // 5

        LocalDate date_p = LocalDate.parse("2017-05-03");
        LocalTime time_p = LocalTime.parse("10:33:44");
        System.out.println(date_p);
        System.out.println(time_p);

        LocalDateTime dateTime = LocalDateTime.of(2020, Month.DECEMBER, 22, 13, 45, 20);
        LocalDateTime dateTime_p = LocalDateTime.of(date_p, time_p);

        LocalDateTime dateTime_t = date_p.atTime(13, 45, 20);
        LocalDateTime localDateTime_d = time_p.atDate(date_p);
        LocalDate date_to = dateTime.toLocalDate();
        LocalTime time_to = dateTime.toLocalTime();

        Duration between_d = Duration.between(dateTime, dateTime_p);
        Period between_p = Period.between(date,currentDate);

        // dateTime_t에 해당 달의 3번째 월요일 반환
        TemporalAdjuster temporalAdjuster = TemporalAdjusters.dayOfWeekInMonth(3, DayOfWeek.MONDAY);
        LocalDateTime with = dateTime_t.with(temporalAdjuster);

    }
}
