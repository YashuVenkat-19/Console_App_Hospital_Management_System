import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class AppointmentBooking {
    Appointments a = new Appointments();

    public String bookAppointment() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter patient name: ");
        String patient_name = scan.nextLine();
        System.out.println("Enter doctor name: ");
        String doctor_name = scan.nextLine();
        System.out.println("Enter patient contact: ");
        String app_patient_contact = scan.nextLine();
        System.out.println("Enter appointment time (YYYY-MM-DD hh:mm): ");
        String appointment_time = scan.nextLine() + ":00";

        return bookAppointmentDb(appointment_time, patient_name, doctor_name, app_patient_contact);
    }

    public String bookAppointmentDb(String appointment_time, String patient_name, String doctor_name,
            String app_patient_contact) {

        a = new Appointments(appointment_time, patient_name, doctor_name, app_patient_contact);

        try {
            // Get the JDBC connection from JdbcConnection class
            JdbcConnection jdbc = new JdbcConnection();
            Connection connection = jdbc.getJdbcConnection();

            // Check if the patient exists in the patient table
            String checkPatientQuery = "SELECT COUNT(*) FROM patient WHERE patient_contact = ?";
            PreparedStatement checkPatientStmt = connection.prepareStatement(checkPatientQuery);
            checkPatientStmt.setString(1, a.getApp_patient_contact());
            ResultSet result = checkPatientStmt.executeQuery();

            // Get the count of rows that match the patient_contact
            result.next();
            int rowCount = result.getInt(1);

            if (rowCount == 0) {
                // If the patient does not exist, prompt the user to create an account or
                // register
                System.out.println("\nPATIENT WITH THIS CONTACT IS NOT REGISTERED! PLEASE REGISTER!\n");
                System.out.println("Do you want to create an account?");
                System.out.println("1. Yes");
                System.out.println("2. No\n");

                Scanner scan = new Scanner(System.in);
                int choice = scan.nextInt();
                if (choice == 1) {
                    // Code to create a new patient account
                    PatientReg patientReg = new PatientReg();
                    patientReg.newPatientReg();
                } else {
                    // Return a message indicating the user chose not to create an account
                    return "Appointment booking canceled.";
                }
            }

            // If the patient exists or the user created an account, proceed with the
            // appointment booking
            String insertQuery = "INSERT INTO appointments (appointment_time, app_patient_name, doctor_name, app_patient_contact) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);

            preparedStatement.setString(1, a.getAppointment_time());
            preparedStatement.setString(2, a.getPatient_name());
            preparedStatement.setString(3, a.getDoctor_name());
            preparedStatement.setString(4, a.getApp_patient_contact());

            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                // If the insert was successful
                return "\n--------------------------------\nAppointment Booked Successfully!!!\n--------------------------------\n";
            } else {
                // If the insert failed
                return "⚠ Something went wrong. Please try again ⚠";
            }
        } catch (NoSuchElementException e) {
            // System.out.println(e.getMessage());
            // return "⚠ Something went wrong. Please try again ⚠";
            return "⚠\n";
        } catch (SQLException e) {
            // System.out.println(e.getMessage());
            return "⚠ Something went wrong. Please try again ⚠";
        }
    }
}
