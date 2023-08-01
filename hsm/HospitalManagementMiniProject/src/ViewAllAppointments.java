import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ViewAllAppointments extends Abstract {
    public void viewAllAppointments() {
        System.out.println("\nAll appointments");
        System.out.println(
                "---------------------------------------------------------------------------------------------------------------------------");
        try {
            JdbcConnection jdbc = new JdbcConnection();
            Connection connection = jdbc.getJdbcConnection();
            String sql = "SELECT * FROM appointments";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            int i = 1;
            while (result.next()) {
                System.out.print(" Appointment " + i + ": Patient Name: " + result.getString("app_patient_name"));
                System.out.print("\t| Patient Contact: " + result.getString("app_patient_contact"));
                System.out.print("\t| Appointment Date: " + result.getString("appointment_time"));
                System.out.print("\t| Appointment Doctor: " + result.getString("doctor_name"));
                System.out.println();
                i++;
            }
        } catch (Exception e) {
            System.out.println("An error occurred while retrieving appointments");
        }
    }
}
