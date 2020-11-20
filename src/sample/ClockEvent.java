package sample;

import com.sun.tools.corba.se.idl.constExpr.Times;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class ClockEvent {
    static long startSaver;
    String topic;
    long startTime;
    long stopTime;
    long duration;
    String durationFormatted;
public ClockEvent(String topic, long startTime, long stopTime, long duration, String durationFormatted){
    this.topic = topic;
    this.startTime = startTime;
    this.stopTime = stopTime;
    this.duration = duration;
    this.durationFormatted = durationFormatted;
}
public static String dateParser(long longValue){
    String date = String.valueOf(longValue/1000);

    return date;
}
}
