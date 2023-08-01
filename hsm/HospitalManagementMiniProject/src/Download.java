import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Download {
    public void generateAndSaveContent(List<PatientAppointmentData> patientAppointmentList) throws Exception {
        StringBuilder content = new StringBuilder();

        for (int i = 0; i < patientAppointmentList.size(); i++) {
            PatientAppointmentData patientAppointment = patientAppointmentList.get(i);
            content.append("Patient ").append(i + 1).append(":\n");
            content.append("--------------------\n");
            content.append(String.format("%-20s: %s%n", "Patient Name", patientAppointment.getPatientName()));
            content.append(String.format("%-20s: %s%n", "Patient Gender", patientAppointment.getPatientGender()));
            content.append(String.format("%-20s: %s%n", "Patient Age", patientAppointment.getPatientAge()));
            content.append(String.format("%-20s: %s%n", "Patient Contact", patientAppointment.getPatientContact()));
            content.append(
                    String.format("%-20s: %s%n", "Patient Blood Group", patientAppointment.getPatientBloodGroup()));
            content.append("\n");

            content.append("Appointments:\n");
            content.append(String.format("%-20s: %s%n", "Appointment Time", patientAppointment.getAppointmentTime()));
            content.append(String.format("%-20s: %s%n", "Doctor Name", patientAppointment.getDoctorName()));

            content.append("-----------------------------------------------------\n");
        }

        try {
            // Replace "path/to/your/file.txt" with the desired file path and name.
            String filePath = "C:/Users/APQA/Desktop/Hi/SKCET FILES/miniprojects/hsm/HospitalManagementMiniProject/report.txt";

            // Create a FileWriter object.
            FileWriter fileWriter = new FileWriter(filePath);

            // Write the content to the file.
            fileWriter.write(content.toString());

            // Close the FileWriter.
            fileWriter.close();

            System.out.println("File generated and saved successfully!");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("An error occurred while generating or saving the file.");
        }
    }
}
