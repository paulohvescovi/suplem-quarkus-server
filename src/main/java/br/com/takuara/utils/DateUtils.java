package br.com.takuara.utils;

import lombok.NonNull;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DateUtils {

    public static int yearsBetween(@NonNull LocalDate date1, @NonNull LocalDate date2){
        return (int) ChronoUnit.YEARS.between(date1 , date2);
    }

}
