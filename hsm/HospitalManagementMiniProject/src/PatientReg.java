import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PatientReg extends Patient {
    Patient p = new Patient();

    public void newPatientReg() {
        // System.out.println("New Patient Registration");
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter patient name: ");
        String patient_name = scan.nextLine();
        System.out.println("Enter patient contact: ");
        String patient_contact = scan.nextLine();
        System.out.println("Enter patient age: ");
        int patient_age = scan.nextInt();
        scan.nextLine();
        System.out.println("Enter patient gender: ");
        String patient_gender = scan.nextLine();
        System.out.println("Enter patient blood group: ");
        String patient_blood_group = scan.nextLine();

        newPatientRegDb(patient_name, patient_contact, patient_age, patient_gender, patient_blood_group);

    }

    public void newPatientRegDb(String patient_name, String patient_contact, int patient_age,
            String patient_gender, String patient_blood_group) {

        // First, check if the patient with the provided contact already exists
        try {
            // Get the JDBC connection from JdbcConnection class
            JdbcConnection jdbc = new JdbcConnection();
            Connection connection = jdbc.getJdbcConnection();

            // Prepare the SQL statement with placeholders (?)
            String selectQuery = "SELECT COUNT(*) FROM patient WHERE patient_contact = ?";
            PreparedStatement selectStmt = connection.prepareStatement(selectQuery);
            selectStmt.setString(1, patient_contact);
            ResultSet result = selectStmt.executeQuery();

            // Get the count of rows that match the patient_contact
            result.next();
            int rowCount = result.getInt(1);

            if (rowCount > 0) {
                // If the patient already exists, return a message indicating so
                System.out.println("Patient with the provided contact already exists.");
                return;
            }

            // If the patient does not exist, proceed with the registration
            String insertQuery = "INSERT INTO patient (patient_name, patient_contact, patient_age, patient_gender, patient_blood_group) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);

            // Set the values for the placeholders
            preparedStatement.setString(1, patient_name);
            preparedStatement.setString(2, patient_contact);
            preparedStatement.setInt(3, patient_age);
            preparedStatement.setString(4, patient_gender);
            preparedStatement.setString(5, patient_blood_group);

            // Execute the insert query
            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                // If the insert was successful
                System.out.println(
                        "\n--------------------------------\nPatient Registered Successfully !!!\n--------------------------------\n");
                return;
            } else {
                // If the insert failed
                System.out.println("Failed to register the patient.");
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error while registering the patient.");
            return;
        }
    }
}
