package com.mediturnos.util;

import java.time.LocalTime;

public final class HorarioUtil {

    private HorarioUtil() {
    }

    public static boolean horariosSeCruzan(
            LocalTime inicio1,
            LocalTime fin1,
            LocalTime inicio2,
            LocalTime fin2) {

        return inicio1.isBefore(fin2)
                && fin1.isAfter(inicio2);
    }

}