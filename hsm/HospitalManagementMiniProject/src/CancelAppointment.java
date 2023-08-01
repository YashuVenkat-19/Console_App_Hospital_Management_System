import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;

public class CancelAppointment {
    public String cancelAppointment() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter patient name: ");
        String patient_name = scan.nextLine();
        System.out.println("Enter patient contact: ");
        String patient_contact = scan.nextLine();

        try {
            return cancelAppointmentDb(patient_name, patient_contact);
        } catch (Exception e) {
            return "An error occurred: " + e.getMessage();
        }
    }

    private String cancelAppointmentDb(String patient_name, String patient_contact) throws Exception {
        JdbcConnection jdbc = new JdbcConnection();
        Connection connection = jdbc.getJdbcConnection();

        String selectQuery = "SELECT COUNT(*) FROM patient WHERE patient_name = ? AND patient_contact = ?";

        PreparedStatement selectStmt = connection.prepareStatement(selectQuery);
        selectStmt.setString(1, patient_name);
        selectStmt.setString(2, patient_contact);
        ResultSet result = selectStmt.executeQuery();

        result.next();
        int rowCount = result.getInt(1);

        if (rowCount == 0) {
            return "\nPatient with the provided name and contact does not exist.\n";
        } else {
            String selectQuery2 = "SELECT * FROM appointments WHERE app_patient_name = ? AND app_patient_contact = ?";
            PreparedStatement selectStmt2 = connection.prepareStatement(selectQuery2);
            selectStmt2.setString(1, patient_name);
            selectStmt2.setString(2, patient_contact);
            ResultSet result2 = selectStmt2.executeQuery();
            System.out.println("--------------------------------------------------------------------------------");
            System.out.println("Appointments for " + patient_name + " with contact " + patient_contact + ":\n");
            ArrayList<String> appointmentsList = new ArrayList<>();

            while (result2.next()) {
                String appointmentTime = result2.getString("appointment_time");
                String doctorName = result2.getString("doctor_name");
                String appointmentDetails = "Appointment Time: " + appointmentTime + ", Doctor: " + doctorName;
                appointmentsList.add(appointmentDetails);
            }

            // Display existing appointments with index
            // System.out.println("Appointments for " + patient_contact + ":");
            for (int i = 0; i < appointmentsList.size(); i++) {
                System.out.println((i + 1) + ". " + appointmentsList.get(i));
            }

            // Ask the user to choose an appointment to cancel
            Scanner scan = new Scanner(System.in);
            System.out.println("\nEnter the appointment you want to cancel (0 to cancel):");
            int choice = scan.nextInt();

            if (choice > 0 && choice <= appointmentsList.size()) {
                String selectedAppointment = appointmentsList.get(choice - 1);
                System.out.println("\nYou have canceled the appointment: " + selectedAppointment);
                // Retrieve the appointment details to delete from the database
                String[] appointmentParts = selectedAppointment.split(", Doctor: ");
                String appointmentTime = appointmentParts[0].replace("Appointment Time: ", "");
                String doctorName = appointmentParts[1];
                // Cancel the selected appointment (you can add the cancellation logic here)
                String deleteQuery = "DELETE FROM appointments WHERE appointment_time = ? AND doctor_name = ?";
                PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
                deleteStatement.setString(1, appointmentTime);
                deleteStatement.setString(2, doctorName);
                int rowsAffected = deleteStatement.executeUpdate();
                if (rowsAffected > 0) {
                    return "-----------------------------Appointment cancelled successfully!!!--------------------------------";
                } else {
                    return "Error while canceling the appointment.";
                }
            } else {
                return "No appointment canceled.";
            }
        }
    }
}
