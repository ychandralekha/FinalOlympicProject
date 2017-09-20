package com.cts.constants;

public class SqlConstants {
public static final String selectCountryQuery="select country from OlympicAthlete";
public static final String olympicHost="from OlympicHost";
public static final String selectSportEventDiscipline="select sport from OlympicEventDiscipline";
public static final String selectDiscipline="select discipline from OlympicEventDiscipline where sport= :sport";
public static final String selectEvent="select event from OlympicEventDiscipline where sport= :sport and discipline=:discipline";
public static final String selectEventId="select eventId from OlympicEventDiscipline where sport=:sport and discipline=:discipline and event=:event";
public static final String selectAthlete="select athlete from OlympicAthlete where eventId=:eventId and country=:country and gender=:gender and medal=:medal and year=:year and display=:display";
public static final String selectCity="select city from OlympicHost where year=:year";
public static final String updateAthlete="update OlympicAthlete set display=:display where athlete=:athlete and eventId=:eventId and country=:country and gender=:gender and medal=:medal and year=:year";
public static final String updateSetAthlete ="update OlympicAthlete set athlete=:athlete where athlete=:oldathlete and eventId=:eventId and country=:country and gender=:gender and medal=:medal and year=:year and display=:display";
public static final String fromAthlete="from OlympicAthlete where eventId=:eventId and country=:country and gender=:gender and medal=:medal and year=:year and display=:display";
}
