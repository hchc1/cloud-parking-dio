package com.meusite.parking.service;

import com.meusite.parking.model.Parking;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class ParkingCheckout {

    public static final int ONE_HOUR = 60;
    public static final int TWENTY_FOUR_HOUR = 24 * ONE_HOUR;
    public static final Double ONE_HOUR_VALUE = 5.00;
    public static final Double ADDITIONAL_PER_HOUR_VALUE = 2.00;
    public static final Double DAILY_VALUE = 40.00;

    
    public static Double getBill(Parking parking) {
        return getBill(parking.getEntryDate(), parking.getExitDate());
    }

    private static Double getBill(LocalDateTime entryDate, LocalDateTime exitDate) {
        long minutes = entryDate.until(exitDate, ChronoUnit.MINUTES);
        Double bill = 0.0;

        if (minutes <= ONE_HOUR) {
            return ONE_HOUR_VALUE;
        }
        if (minutes <= TWENTY_FOUR_HOUR) {
            bill = ONE_HOUR_VALUE;
            int hours = (int) (minutes / ONE_HOUR);
            System.out.println(hours);
            for (int i = 0; i < hours; i ++) {
                bill += ADDITIONAL_PER_HOUR_VALUE;
            }
            return bill;
        }
        int days = (int) (minutes / TWENTY_FOUR_HOUR);
        System.out.println(days);
        for (int i = 0; i < days; i++) {
            bill += DAILY_VALUE;
        }
        return bill;
    }
}
