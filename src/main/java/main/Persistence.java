package main;

import java.io.IOException;
import java.util.List;

public interface Persistence {
      public List<Appointment> loadAppointments() throws IOException;
      public List<Appointment> loadAppointmentsInTimespan(String start, String end) throws IOException;
      public List<AppointmentModel> loadAppointmentsAsAppointmentModelsInTimespan(String start, String end) throws IOException;
      public void deleteAppointment(String id) throws IOException;
      public void updateAppointment(Appointment appointment) throws IOException;
}
