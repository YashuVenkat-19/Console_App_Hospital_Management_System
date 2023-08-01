import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class ViewAllAppointmentsOnDate {
    Scanner scan = new Scanner(System.in);

    public void viewAllAppointmentsOnDate() {
        System.out.println("\nAll appointments on date");
        try {
            JdbcConnection jdbc = new JdbcConnection();
            Connection connection = jdbc.getJdbcConnection();

            String sql = "SELECT * FROM appointments WHERE DATE(appointment_time) = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql);) {

                System.out.println("Enter the date (YYYY-MM-DD): ");
                // scan.nextLine();
                String dateStr = scan.nextLine();
                viewAllAppointmentsOnDate(dateStr);

                statement.setString(1, dateStr.trim());
                ResultSet result = statement.executeQuery();

                if (!result.next()) {
                    System.out.println("\nNo appointments on " + dateStr);
                    return;
                }
                System.out.println();
                int i = 1;
                do {
                    System.out.print("Appointment " + i + ": Patient Name: " + result.getString("app_patient_name"));
                    System.out.print("\t| Patient Contact: " + result.getString("app_patient_contact"));
                    System.out.print("\t| Appointment Date: " + result.getString("appointment_time"));
                    System.out.print("\t| Appointment Doctor: " + result.getString("doctor_name"));
                    System.out.println();
                    i++;
                } while (result.next());
            }
        } catch (Exception e) {
            System.out.println("An error occurred while retrieving appointments");
        }
    }

    public void viewAllAppointmentsOnDate(String date) {
        System.out.println("\nAll appointments on " + date + "\n");
    }
}
