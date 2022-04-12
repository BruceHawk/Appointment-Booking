package uk.ac.aston.oop.calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class DayTest.
 *
 * @author (Hassan)
 * @version (29/1/2022)
 */
public class DayTest {

	
	Day day;
	
	@BeforeEach public void setup () {
		day = new Day(1);
	}

@Test public void appointmentStartOfDay() {
//Arrange
	
	Appointment app = new Appointment("meeting", 1);
	//Act
	boolean success = day.makeAppointment(Day.START_OF_DAY, app);
	Appointment fetched = day.getAppointment(Day.START_OF_DAY);
	//Assert
	assertTrue(success, "Making an appointment at the start of a new day should work");
	assertSame(app, fetched, "It should be possible to fetch the appointment we just made");
}
@Test public void appointmentBeforeStartOfDay () {
	//Arrange

	Appointment app = new Appointment("meeting", 1);
	
	//Act
	boolean success = day.makeAppointment(Day.START_OF_DAY - 1, app);
	Appointment fetched = day.getAppointment(Day.START_OF_DAY - 1);
	//Assert
	assertFalse(success);
	assertNull(fetched, "No appointment at this time!");
	}
@Test public void twoAppsOnSameTime () {
     //Arrange
	
	Appointment appA = new Appointment("meetingA", 1);
	Appointment appB = new Appointment("meetingB", 1);
	//Act
	boolean successA = day.makeAppointment(Day.START_OF_DAY, appA);
	Appointment fetchedA = day.getAppointment(Day.START_OF_DAY);
	boolean successB = day.makeAppointment(Day.START_OF_DAY, appB);
	Appointment fetchedB = day.getAppointment(Day.START_OF_DAY);
	//Assert
	assertTrue (successA, "possible to book");
	assertSame(fetchedA, appA, "possible to fetch this appointment");
	assertFalse(successB, "not possible to book 2 appointments at the same time");
	assertSame(fetchedB, appA, "will fetch the first appointment");
	
}


@Test public void twoHourAppointmentAtStart () {

	Appointment app = new Appointment("meeting", 2);
	
	boolean success= day.makeAppointment(9, app);
	Appointment fetched = day.getAppointment(10);

	
	assertTrue(success);
	assertSame(app, fetched, "2 hour aapointment can be fetched");
}

@Test public void twoHourAppointmentBeyondEnd () {

	Appointment app = new Appointment("meeting", 2);
	
	boolean success= day.makeAppointment(17, app);
	Appointment fetchedA = day.getAppointment(17);
	Appointment fetchedB = day.getAppointment(18);
	
	assertFalse(success, "not possible");
	assertSame(null, fetchedA, "not possible to fetch this appointment");
	assertSame(null, fetchedB, "not possible to fetch this appointment");
}

@Test public void overlappingTwoHourAppointments () {
	
	Appointment app = new Appointment("meeting", 2);
	
	boolean successA= day.makeAppointment(11, app);
	boolean successB= day.makeAppointment(10, app);
	
	assertTrue(successA, "appointment possible");
	assertFalse(successB, "appointment not possible due to overlap");
	
}

}
