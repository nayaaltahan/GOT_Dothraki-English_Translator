package com.example.got.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class Converter {
    public static long convertToLong(LocalDateTime localDateTime){
        Date date = Date.from( localDateTime.atZone( ZoneId.systemDefault()).toInstant());
        return date.getTime();
    }
}