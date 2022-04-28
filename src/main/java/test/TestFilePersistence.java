package test;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;

import builder.AppointmentBuilder;
import main.Appointment;
import main.AppointmentModel;
import main.Category;
import main.FilePersistence;
import main.PersistenceAppointmentModel;

public class TestFilePersistence {
	PersistenceAppointmentModel fp;
	
	@Before
	public void createTestFile() throws IOException {
		fp = new PersistenceAppointmentModel(System.getProperty("user.home") + File.separator + "Documents" + File.separator + "Appointments by PlanIt",
				 File.separator + "appointmentModel_Test.json");
//		generiert Test-F�lle
		fp.add(new AppointmentModel("12", "Friseur", "2022-03-14T14-00-00", "2022-03-14T15-00-00", ""));
		fp.add(new AppointmentModel("85", "Geburtstag", "2022-03-31T06-00-00", "2022-03-31T14-00-00", ""));
		fp.add(new AppointmentModel("123", "Zahnarzt", "2022-03-01T12-00-00","2022-03-31T14-00-00", ""));
		fp.add(new AppointmentModel("1020", "Abendessen", "2022-03-25T21-00-00", "2022-03-26T01-00-00", ""));
		fp.add(new AppointmentModel("45605", "Silvesterfeier", "2022-12-31T23-00-00", "2023-01-01T01-00-00", ""));
		fp.add(new AppointmentModel("9874", "Urlaub", "2022-03-28T06-00-00", "2022-04-15T23-59-59", ""));
	}
	
	@After
	public void deleteTestFile() {
		fp.deleteFile();
	}
	
	@Test
	public void testConvertAppointmentListToJSON() throws JsonProcessingException {
		// Create appointment-object
		LocalDate start = LocalDate.parse("2022-01-01");
		LocalDate end = LocalDate.parse("2022-01-02");
		Appointment a1 = new AppointmentBuilder().id("id-123").title("General Check").category(Category.Doctor)
				.start(start).end(end).description("Test").place("Goethe Platz 12, 85256 Vierkirchen").build();
		// Create FilePersistence-object -> no constructor for this test case needed
		FilePersistence fp = new FilePersistence();
		ArrayList<Appointment> aList = new ArrayList<Appointment>();
		aList.add(a1);
		assertEquals("[ {\r\n" + "  \"id\" : \"id-123\",\r\n" + "  \"title\" : \"General Check\",\r\n"
				+ "  \"category\" : \"Doctor\",\r\n" + "  \"start\" : {\r\n" + "    \"year\" : 2022,\r\n"
				+ "    \"month\" : \"JANUARY\",\r\n" + "    \"monthValue\" : 1,\r\n" + "    \"dayOfMonth\" : 1,\r\n"
				+ "    \"era\" : \"CE\",\r\n" + "    \"dayOfWeek\" : \"SATURDAY\",\r\n"
				+ "    \"leapYear\" : false,\r\n" + "    \"dayOfYear\" : 1,\r\n" + "    \"chronology\" : {\r\n"
				+ "      \"id\" : \"ISO\",\r\n" + "      \"calendarType\" : \"iso8601\"\r\n" + "    }\r\n" + "  },\r\n"
				+ "  \"end\" : {\r\n" + "    \"year\" : 2022,\r\n" + "    \"month\" : \"JANUARY\",\r\n"
				+ "    \"monthValue\" : 1,\r\n" + "    \"dayOfMonth\" : 2,\r\n" + "    \"era\" : \"CE\",\r\n"
				+ "    \"dayOfWeek\" : \"SUNDAY\",\r\n" + "    \"leapYear\" : false,\r\n" + "    \"dayOfYear\" : 2,\r\n"
				+ "    \"chronology\" : {\r\n" + "      \"id\" : \"ISO\",\r\n"
				+ "      \"calendarType\" : \"iso8601\"\r\n" + "    }\r\n" + "  },\r\n"
				+ "  \"description\" : \"Test\",\r\n" + "  \"place\" : \"Goethe Platz 12, 85256 Vierkirchen\"\r\n"
				+ "} ]", fp.convertAppointmentListToJSON(aList));
	}

	@Ignore("Not implemented yet")
	@Test
	public void testAddAppointmentSuccess() throws IOException {
		// Create appointment-object
		LocalDate start = LocalDate.parse("2022-01-01");
		LocalDate end = LocalDate.parse("2022-01-02");
		Appointment a1 = new AppointmentBuilder().id("id-123").title("General Check").category(Category.Doctor)
				.start(start).end(end).description("Test").place("Goethe Platz 12, 85256 Vierkirchen").build();
		
		// Set directory for test case
		String testDir = System.getProperty("user.home") + File.separator + "Documents" + File.separator 
				+ "Appointments by PlanIt" + File.separator + "Test";
		String testFilePath = testDir + File.separator + "testfile.txt";
		
		// Create FilePersistance object with defined paths
		FilePersistence fp = new FilePersistence(testDir, testFilePath);
		
		fp.addAppointment(a1);
	}

	@Ignore("Not implemented yet")
	@Test
	public void testLoadAppointments() throws IOException {
		List<AppointmentModel> list = new ArrayList<>();
		list.add(new AppointmentModel("12", "Friseur", "2022-03-14T14-00-00", "2022-03-14T15-00-00", ""));
		list.add(new AppointmentModel("85", "Geburtstag", "2022-03-31T06-00-00", "2022-03-31T14-00-00", ""));
		list.add(new AppointmentModel("123", "Zahnarzt", "2022-03-01T12-00-00","2022-03-31T14-00-00", ""));
		list.add(new AppointmentModel("1020", "Abendessen", "2022-03-25T21-00-00", "2022-03-26T01-00-00", ""));
		list.add(new AppointmentModel("45605", "Silvesterfeier", "2022-12-31T23-00-00", "2023-01-01T01-00-00", ""));
		list.add(new AppointmentModel("9874", "Urlaub", "2022-03-28T06-00-00", "2022-04-15T23-59-59", ""));
		assertEquals(fp.load(), list);
	}

	@Ignore("Not implemented yet")
	@Test
	public void testLoadAppointmentsInTimespan() {
	}

	@Ignore("Not implemented yet")
	@Test
	public void testLoadAppointmentsAsAppointmentModelsInTimespan() {
	}

	@Ignore("Not implemented yet")
	@Test
	public void testReadAppointemntFile() {
	}

	@Ignore("Not implemented yet")
	@Test
	public void testDeleteAppointment() {
	}

	@Ignore("Not implemented yet")
	@Test
	public void testUpdateAppointment() {
	}
}