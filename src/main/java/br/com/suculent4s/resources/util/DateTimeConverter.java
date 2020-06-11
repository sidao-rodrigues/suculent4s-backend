package br.com.suculent4s.resources.util;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeConverter {

    public static OffsetDateTime converterDateTime(String format, OffsetDateTime dateTime){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format);
        return OffsetDateTime.parse(dtf.format(dateTime));
    }

    public static OffsetDateTime converterDateTimeDefaultFormat(OffsetDateTime dateTime){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
        return OffsetDateTime.parse(dtf.format(dateTime));
    }

}
