import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ViewAllPatientsWithAppointments {
    public void viewAllPatientsWithAppointments() {
        System.out.println("\nAll patients with appointments\n");
        System.out.println("-----------------------------------------------------");

        List<PatientAppointmentData> patientAppointmentList = new ArrayList<>(); // Create a list to hold patient
                                                                                 // appointment data

        try {
            JdbcConnection jdbc = new JdbcConnection();
            Connection connection = jdbc.getJdbcConnection();
            String sql = "SELECT * FROM patient INNER JOIN appointments ON patient.patient_contact = appointments.app_patient_contact";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            int i = 0;
            boolean foundPatients = false; // Flag to check if any patients with appointments are found

            while (result.next()) {
                foundPatients = true;

                // Retrieve data from the ResultSet and create a PatientAppointmentData object
                PatientAppointmentData patientAppointment = new PatientAppointmentData();
                patientAppointment.setPatientName(result.getString("patient_name"));
                patientAppointment.setPatientGender(result.getString("patient_gender"));
                patientAppointment.setPatientAge(result.getInt("patient_age"));
                patientAppointment.setPatientContact(result.getString("patient_contact"));
                patientAppointment.setPatientBloodGroup(result.getString("patient_blood_group"));
                patientAppointment.setAppointmentTime(result.getString("appointment_time"));
                patientAppointment.setDoctorName(result.getString("doctor_name"));

                // Add the PatientAppointmentData object to the list
                patientAppointmentList.add(patientAppointment);

                System.out.println("Patient " + (i + 1) + ":");
                System.out.println("--------------------");
                System.out.format("%-20s: %s%n", "Patient Name", patientAppointment.getPatientName());
                System.out.format("%-20s: %s%n", "Patient Gender", patientAppointment.getPatientGender());
                System.out.format("%-20s: %s%n", "Patient Age", patientAppointment.getPatientAge());
                System.out.format("%-20s: %s%n", "Patient Contact", patientAppointment.getPatientContact());
                System.out.format("%-20s: %s%n", "Patient Blood Group", patientAppointment.getPatientBloodGroup());

                System.out.println("Appointments:");
                System.out.format("%-20s: %s%n", "Appointment Time", patientAppointment.getAppointmentTime());
                System.out.format("%-20s: %s%n", "Doctor Name", patientAppointment.getDoctorName());

                System.out.println("-----------------------------------------------------"); // Empty line between
                                                                                             // patients
                i++;
            }

            if (foundPatients) {
                System.out.println("\n Do you want to download this report?");
                System.out.println("1. Yes");
                System.out.println("2. No\n");
                System.out.println("Enter your choice: ");
                Scanner scan = new Scanner(System.in);
                int choice = scan.nextInt();
                switch (choice) {
                    case 1:
                        Download download = new Download();
                        System.out.println("Downloading...\n");
                        download.generateAndSaveContent(patientAppointmentList); // Pass the list to the Download class
                        break;
                    case 2:
                        System.out.println("\nDownload not requested");
                        break;
                    default:
                        System.out.println("\nInvalid choice");
                        break;
                }
            }

            if (!foundPatients) {
                System.out.println("No patients with appointments found.");
            }

        } catch (Exception e) {
            System.out.println("An error occurred while retrieving patients: " + e.getMessage());
        }
    }
}
