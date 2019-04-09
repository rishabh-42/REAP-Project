package com.example.spring.others;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class DateTimeUtil {

   public static String get(LocalDateTime fromDateTime){


       LocalDateTime toDateTime = LocalDateTime.now();

       LocalDateTime tempDateTime = LocalDateTime.from( fromDateTime );

       long years = tempDateTime.until( toDateTime, ChronoUnit.YEARS);
       tempDateTime = tempDateTime.plusYears( years );

       long months = tempDateTime.until( toDateTime, ChronoUnit.MONTHS);
       tempDateTime = tempDateTime.plusMonths( months );

       long days = tempDateTime.until( toDateTime, ChronoUnit.DAYS);
       tempDateTime = tempDateTime.plusDays( days );


       long hours = tempDateTime.until( toDateTime, ChronoUnit.HOURS);
       tempDateTime = tempDateTime.plusHours( hours );

       long minutes = tempDateTime.until( toDateTime, ChronoUnit.MINUTES);
       tempDateTime = tempDateTime.plusMinutes( minutes );

       long seconds = tempDateTime.until( toDateTime, ChronoUnit.SECONDS);

       StringBuffer s=new StringBuffer("");

       if(days!=0){
           s= s.append(days + " days ");
       }

       s=s.append(hours+" hours "+minutes+" minutes ");
       return s.toString();

   }
}
