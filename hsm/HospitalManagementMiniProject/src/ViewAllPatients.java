import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ViewAllPatients {
    public void viewAllPatients() {
        System.out.println("All patients");
        System.out.println("----------------------------------------------------------------------------------");
        // System.out.println("Patient Name\tPatient Gender\tPatient Age\tPatient
        // Contact\tPatient Blood Group");
        // System.out.println("------------\t--------------\t-----------\t---------------\t------------------");
        try {
            JdbcConnection jdbc = new JdbcConnection();
            Connection connection = jdbc.getJdbcConnection();
            String sql = "SELECT * FROM patient";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            int i = 1;
            while (result.next()) {
                System.out.print(i + "|  Patient Name: " + result.getString("patient_name"));
                System.out.print("\t| Patient Gender : " + result.getString("patient_gender"));
                System.out.print("\t| Patient Age: " + result.getString("patient_age"));
                System.out.print("\t| Patient Contact: " + result.getString("patient_contact"));
                System.out.print("\t| Patient Blood Group: " + result.getString("patient_blood_group"));
                System.out.println();
                i++;
            }
        } catch (Exception e) {
            System.out.println("An error occurred while retrieving patients");
        }
    }
}
