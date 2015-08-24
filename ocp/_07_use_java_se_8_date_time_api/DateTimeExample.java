package ocp._07_use_java_se_8_date_time_api;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.chrono.IsoChronology;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.ResolverStyle;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

import javax.swing.plaf.synth.SynthSeparatorUI;
import javax.swing.plaf.synth.SynthSplitPaneUI;

import ocp._00_utils.Example;

public class DateTimeExample implements Example {

	@Override
	public void run() {
		localExample();
		zoneExample();
		instantPeriodDurationTemporalUnit();
	}

	/**
	 * Create and manage date-based and time-based events including a
	 * combination of date and time into a single object using LocalDate,
	 * LocalTime, LocalDateTime, Instant, Period, and Duration
	 */
	private void localExample() {
		// LocalTime
		LocalTime time = LocalTime.now();
		System.out.println(time);
		LocalDate date = LocalDate.now();
		System.out.println(date);
		LocalDateTime dateTime = LocalDateTime.now();
		System.out.println(dateTime);

		// LocalDate
		LocalDate nineties = LocalDate.of(1990, 1, 1);
		System.out.println(nineties);
		LocalDate ofEpoch = LocalDate.ofEpochDay(365);
		System.out.println(ofEpoch);
		LocalDate parse = LocalDate.parse("2000-01-01");
		System.out.println(parse);
		LocalDate parse2 = LocalDate.parse("2015 180", DateTimeFormatter.ofPattern("yyyy D"));
		System.out.println(parse2);

		int m = LocalDate.now().getDayOfMonth();
		int y = LocalDate.now().getYear();
		int h = LocalTime.now().getHour();
		int min = LocalTime.now().getMinute();

		// LocalDateTime
		LocalDateTime sameDayInThe90 = LocalDateTime.now().withYear(1990);
		System.out.println(sameDayInThe90);

		LocalDateTime lastYear = LocalDateTime.now().minusYears(1);
		System.out.println(lastYear);

		// Instant
		Instant now = Instant.now();
		System.out.println(now);
		System.out.println(now.getEpochSecond());
		System.out.println(now.getNano());
		System.out.println(now.getEpochSecond() + "s and " + now.getNano() + "ns");

		// Period
		Period period = Period.of(1, 1, 1);
		System.out.println(period);
		Period period20142015 = Period.between(LocalDate.of(2014, 01, 01), LocalDate.of(2015, 12, 31));
		System.out.println(period20142015);
		int years = Period.of(12, 0, 0).getYears();
		Period millenium = period.plusYears(999).minusDays(1).minusMonths(1);
		System.out.println(millenium);

		LocalDate nextyear = LocalDate.now().plus(Period.of(1, 0, 0));

		// Duration
		Duration duration = Duration.of(1000, ChronoUnit.SECONDS);
		System.out.println(duration);
		Duration ofSec = Duration.ofSeconds(2000);
		System.out.println(ofSec);
		System.out.println(Duration.between(LocalTime.now(), LocalTime.now().plusHours(1)));
		System.out.println(Duration.between(LocalTime.NOON, LocalTime.MIDNIGHT));
	}

	/**
	 * Work with dates and times across timezones and manage changes resulting
	 * from daylight savings including Format date and times values
	 */
	private void zoneExample() {
		// ZoneId
		System.out.println(ZoneId.getAvailableZoneIds());
		System.out.println(ZoneId.of("Pacific/Majuro"));

		// ZoneOffset
		System.out.println(ZoneOffset.of("-06:00"));

		// ZonedDateTime
		ZonedDateTime zoneDateTime = ZonedDateTime.now();
		System.out.println(zoneDateTime);
		System.out.println(zoneDateTime.plusMonths(6));
		System.out.println(ZonedDateTime.now(ZoneId.of(("America/Chicago"))));

		// Formating
		DateTimeFormatter formatter = DateTimeFormatter.ISO_ZONED_DATE_TIME;
		System.out.println(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
		DateTimeFormatter custom = DateTimeFormatter.ofPattern("yyyy M");
		System.out.println(custom);
		DateTimeFormatterBuilder dtfb = new DateTimeFormatterBuilder();
		DateTimeFormatter f = dtfb.appendLiteral('[').appendZoneRegionId().appendLiteral(']').toFormatter();
		System.out.println(f);
	}

	/**
	 * Define and create and manage date-based and time-based events using
	 * Instant, Period, Duration, and TemporalUnit
	 */
	private void instantPeriodDurationTemporalUnit() {
		// Instant Period Duration
		Instant i = Instant.now();
		Period p = Period.of(1, 1, 1);
		Duration d = Duration.ofDays(1);
		
		// TemporalUnit
		System.out.println(ChronoUnit.CENTURIES.between(LocalDate.of(1000,1,1), LocalDate.of(2015, 1, 1)));
	}
}
